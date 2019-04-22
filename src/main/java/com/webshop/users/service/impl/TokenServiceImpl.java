package com.webshop.users.service.impl;

import com.webshop.users.domain.User;
import com.webshop.users.dto.TokenResponseDto;
import com.webshop.users.service.TokenService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class TokenServiceImpl implements TokenService {

    @Value("${oauth.jwt.secret}")
    private String jwtSecret;

    @Override
    public TokenResponseDto generate(User user) {
        Claims claims = Jwts.claims();
        claims.put("id", user.getId());
        claims.put("username", user.getUsername());
        claims.put("email", user.getEmail());
        claims.put("role", user.getRole().getName());

        return new TokenResponseDto(Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact());
    }

    @Override
    public Claims parseToken(String jwt) {
        Claims claims;
        try {
            claims = Jwts.parser()
                    .setSigningKey(jwtSecret)
                    .parseClaimsJws(jwt)
                    .getBody();
        } catch (Exception e) {
            return null;
        }
        return claims;
    }

}
