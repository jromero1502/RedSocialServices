package com.jromero.redsocial.filters;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jromero.redsocial.iservice.IJwtService;
import com.jromero.redsocial.service.JwtService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    private final String headerPrefix = "AUTHORIZATION";
    private final String authPrefix = "Bearer ";

    private Logger logger = LoggerFactory.getLogger(JwtAuthFilter.class);

    @Autowired
    private IJwtService jwtService;

    private boolean headerHasBearer(HttpServletRequest request) {
        String header = request.getHeader(headerPrefix);
        return header != null && header.startsWith(authPrefix);
    }

    private String getToken(HttpServletRequest request) {
        return request.getHeader(headerPrefix).replace(authPrefix, "");
    }

    private void handleJwtClaims(Claims claims) {
        if (claims.get("authorities") != null) {
            List<String> authorities = (List<String>) claims.get("authorities");
            UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                claims.getSubject(), 
                null,
                authorities.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList())
            );
            SecurityContextHolder.getContext().setAuthentication(auth);
        }
    }

    private void initJwtService(HttpServletRequest request) {
        if (jwtService == null) {
            ServletContext context = request.getServletContext();
            WebApplicationContext webContext = WebApplicationContextUtils.getWebApplicationContext(context);
            this.jwtService = webContext.getBean(JwtService.class);
        }
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try {
            initJwtService(request);
            if (headerHasBearer(request)) {
                String token = getToken(request);
                Claims claims = jwtService.validateToken(token);
                handleJwtClaims(claims);
            }
            filterChain.doFilter(request, response);
        } catch(Exception e) {
            logger.error(e.getMessage());
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            response.sendError(HttpStatus.BAD_REQUEST.value(), "Autenticacion fallida");
            return;
        }
        
    }
    
}
