package com.tave.brandary.global.jwt;

import com.tave.brandary.domain.auth.dto.LoginResDto;
import com.tave.brandary.domain.user.entity.User;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.Jwts;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class JwtService {

    @Value("${jwt.secret}")
    private String secretKey;

    @Value("${jwt.access-token-expiration}")
    private long accessTokenExpiration; // ms 단위

    @Value("${jwt.refresh-token-expiration}")
    private long refreshTokenExpiration;

    public String issueToken(User user) {
        Date now = new Date();
        Date expiry = new Date(now.getTime() + accessTokenExpiration);

        return Jwts.builder()
                .setSubject(String.valueOf(user.getId()))
                .claim("nickname", user.getNickname())
                .claim("provider", user.getSocialProvider().name())
                .setIssuedAt(now)
                .setExpiration(expiry)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }
}