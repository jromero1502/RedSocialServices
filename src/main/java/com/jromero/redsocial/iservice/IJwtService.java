package com.jromero.redsocial.iservice;

import org.springframework.security.core.userdetails.UserDetails;

import io.jsonwebtoken.Claims;

public interface IJwtService {
    public String createToken(UserDetails user);
    public Claims validateToken(String token);
}
