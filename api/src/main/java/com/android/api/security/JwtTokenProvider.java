package com.android.api.security;

import java.security.Key;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.android.api.repository.CustomerRepository;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class JwtTokenProvider {
    //private final String JWT_SECRET = "theluantheluantheluantheluantheluantheluan";
    @Autowired
    CustomerRepository customerRepository;

    private final Long JWT_EXPIRATION = 604800000L;

    private final SecretKey secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS512);
    
    private Key getSigningKey() {
        // byte[] keyBytes = this.JWT_SECRET.getBytes();
        // return Keys.hmacShaKeyFor(keyBytes);
        return secretKey;
    }

    public String generateToken(AccountDetails accountDetails) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + JWT_EXPIRATION);

        return Jwts.builder()
                    .setSubject(Long.toString(accountDetails.getAccount().getAccountId()))
                    .claim("customerId", Long.toString(customerRepository.findByAccount(accountDetails.getAccount()).get().getCustomerId()))
                    .claim("role", accountDetails.getAccount().getRole())
                    .setIssuedAt(now)
                    .setExpiration(expiryDate)
                    .signWith(getSigningKey(), SignatureAlgorithm.HS512)
                    .compact();
    }

    public Long getUserIdFromJwt(String token) {
        Claims claims = Jwts.parserBuilder()
                            .setSigningKey(getSigningKey())
                            .build()
                            .parseClaimsJws(token)
                            .getBody();
        
        return Long.parseLong(claims.getSubject());
    }

    // public String getUsernameFromJwt(String token) {
    //     Claims claims = Jwts.parserBuilder()
    //                         .setSigningKey(getSigningKey())
    //                         .build()
    //                         .parseClaimsJws(token)
    //                         .getBody();

    //     return claims.getSubject();
    // }

    public Long getCustomerIdFromJwt(String token) {
        Claims claims = Jwts.parserBuilder()
                            .setSigningKey(getSigningKey())
                            .build()
                            .parseClaimsJws(token)
                            .getBody();
        return Long.parseLong((String) claims.get("customerId"));
    }

    public boolean validateToken(String authToken) {
        try {
            Jwts.parserBuilder().setSigningKey(getSigningKey().getEncoded()).build().parseClaimsJws(authToken);
            return true;
        } catch (MalformedJwtException ex) {
            log.error("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            log.error("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            log.error("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            log.error("JWT claims string is empty.");
        }
        return false;
    }
}
