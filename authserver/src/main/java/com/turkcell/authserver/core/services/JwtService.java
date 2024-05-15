package com.turkcell.authserver.core.services;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class JwtService {
    // 9W6ch2K/rgdYy88op9r+eUu+88BOifC1etCkUtXa3sQQ+p9/oCc/7Pkra0NLzKeAwg91u7+Guhms2sm75oUYNXNFHWvkExFTlM3hzOrRQukzyOwsP0Hcr7kC2Z6c7w9blaWblOyLEaVr7l1H9ib/Ig5t+3xOB4avL4B/0c/PWua1ZB0ykbz9J+Pz+twKYWk4VCfW3AoCZTlCgbOBaw979E14iV91gY0bju7Hti+3wctW1f9JzFi+V7oR0w632Zg9RAve/plSD/KAQ3OY5aserGmXfXzb8hvb2qBusvl76L3LY5p/D2FxZEUzM3t8SnBjDxxJZOS3J+JaqN3pW9YSqKpr51MjAY+6r5oPYonEt60=

    @Value("${jwt.expiration}")
    private long EXPIRATION;
    @Value("${jwt.secret}")
    private String SECRET_KEY;

    public String generateToken(String username, Map<String, Object> extraClaims)
    {
        String token = Jwts
                .builder()
                .subject(username)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION))
                .claims(extraClaims)
                .signWith(getSigningKey())
                .compact();
        return token;
    }

    public Boolean validateToken(String token)
    {
        return getTokenClaims(token).getExpiration().after(new Date());
    }

    public String extractUsername(String token)
    {
        return getTokenClaims(token).getSubject();
    }

    public List<String> extractRoles(String token)
    {
        return getTokenClaims(token).get("roles", List.class);
    }

    private Claims getTokenClaims(String token)
    {
        return Jwts
                .parser()
                .verifyWith((SecretKey) getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    private Key getSigningKey()
    {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
