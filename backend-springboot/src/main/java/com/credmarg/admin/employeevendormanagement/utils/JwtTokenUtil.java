package com.credmarg.admin.employeevendormanagement.utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public final class JwtTokenUtil implements Serializable {

    public static final long JWT_TOKEN_VALIDITY = 60L * 60 * 1000;
    private static final long serialVersionUID = -2550185165626007488L;

    public static String getJwtToken(Map<String, Object> payload, String secret) {
        Map<String, Object> headers = new HashMap<>();
        headers.put("type", "JWT");
        return doGenerateToken(headers, payload, secret);
    }


    private static String doGenerateToken(Map<String, Object> headers, Map<String, Object> claims, String secret) {
        return Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, secret.getBytes(StandardCharsets.UTF_8))
                .setHeaderParams(headers)
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY))
                .setNotBefore(new Date(System.currentTimeMillis()))
                .compact();
    }

}