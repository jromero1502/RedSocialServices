package com.jromero.redsocial.service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.jromero.redsocial.iservice.IJwtService;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JwtService implements IJwtService {

    private int expirationMins = 60;
    private final String KEY = "KEY";

    @Override
    public String createToken(UserDetails user) {
        String nick = user.getUsername();
        List<GrantedAuthority> authorities = (List<GrantedAuthority>) user.getAuthorities();
        String token = Jwts
            .builder()
            .setSubject(nick)
            .claim("authorities", 
                authorities.stream().map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList())
            )
            .setExpiration(new Date(System.currentTimeMillis() + (expirationMins * 1000 * 60)))
            .setIssuedAt(new Date(System.currentTimeMillis()))
            .signWith(SignatureAlgorithm.HS512, KEY.getBytes())
            .compact();
        return token;
    }
    
    @Override
    public Claims validateToken(String token) {
        return Jwts
            .parser().setSigningKey(KEY.getBytes())
            .parseClaimsJws(token).getBody();
    }
}
