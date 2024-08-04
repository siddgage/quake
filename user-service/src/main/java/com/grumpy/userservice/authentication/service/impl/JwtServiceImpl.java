package com.grumpy.userservice.authentication.service.impl;

import com.grumpy.userservice.authentication.service.JwtService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
@Slf4j
public class JwtServiceImpl implements JwtService {

    @Value("${user.jwt.secret-key}")
    private String secretKey;

    @Override
    public String generateToken(final UserDetails userDetails) {
        return this.generateToken(new HashMap<>(), userDetails);
    }

    @Override
    public String generateToken(final Map<String, Object> claims, final UserDetails userDetails) {
        return Jwts.builder()
                .claims(claims)
                .subject(userDetails.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60))
                .signWith(getSignKey(), Jwts.SIG.HS384)
                .compact();
    }

    @Override
    public String extractUsername(final String token) {
        return extractClaim(token, Claims::getSubject);
    }

    @Override
    public boolean isTokenValid(final String token, final UserDetails userDetails) {
        final String username = extractUsername(token);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractTime(token).before(new Date());
    }

    private Date extractTime(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private <T> T extractClaim(final String token, final Function<Claims, T> resolver) {
        final Claims claims = extractClaims(token);
        return resolver.apply(claims);
    }

    private Claims extractClaims(final String token) {
        return Jwts.parser().verifyWith(getSignKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    private SecretKey getSignKey() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey));
    }
}
