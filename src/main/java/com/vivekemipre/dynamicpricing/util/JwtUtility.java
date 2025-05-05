package com.vivekemipre.dynamicpricing.util;


import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.time.Duration;
import java.util.Date;

@Component
public class JwtUtility {

    @Autowired
    private ObjectMapper objectMapper;

    private static final String key="vivekempirevnrrishaara123456789012345678";
    private static final Key signingKey= Keys.hmacShaKeyFor(key.getBytes(StandardCharsets.UTF_8));
    private static final long expiryTime= Duration.ofDays(30).toMillis();

    public String generateToken(String id){
        return Jwts.builder()
                .setSubject(id)
                .setExpiration(new Date(System.currentTimeMillis()+expiryTime))
                .setIssuedAt(new Date())
                .signWith(signingKey, SignatureAlgorithm.HS256)
                .compact();
    }


}
