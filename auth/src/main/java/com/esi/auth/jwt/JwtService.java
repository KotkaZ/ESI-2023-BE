package com.esi.auth.jwt;

import com.esi.auth.config.MyUserDetailsService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtService {

    @Value("${esi.auth.secret}")
    private String SECRET;

    private final MyUserDetailsService userDetailsService;

    public String generateToken(String userName) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, userName);
    }


    private String createToken(Map<String, Object> claims, String userName) {
        val userDetails = userDetailsService.loadUserByUsername(userName);
        val roles = userDetails.getAuthorities();
        String rolesClaim = roles.toString();
        log.info("rolesClaim  {} ", rolesClaim);

        int start = rolesClaim.indexOf("[");
        int end = rolesClaim.indexOf("]");
        rolesClaim = rolesClaim.substring(start + 1, end);
        log.info("claims  {} ", rolesClaim);

        claims.put("roles", rolesClaim);

        return Jwts.builder()
            .setClaims(claims)
            .setSubject(userName)
            .setIssuedAt(new Date(System.currentTimeMillis()))
            .setExpiration(new Date(System.currentTimeMillis() + 1000L * 60 * 60 * 1440))
            .signWith(signingKey(), SignatureAlgorithm.HS256).compact();
    }


    private Key signingKey() {
        byte[] keyDecoder = Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(keyDecoder);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts
            .parserBuilder()
            .setSigningKey(signingKey())
            .build()
            .parseClaimsJws(token)
            .getBody();
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(signingKey()).build().parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }
}
