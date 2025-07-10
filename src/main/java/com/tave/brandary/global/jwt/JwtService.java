package com.tave.brandary.global.jwt;

import com.tave.brandary.domain.user.dto.LoginResDto;
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

    public LoginResDto issueTokens(User user) {
        String accessToken = createToken(user.getId(), accessTokenExpiration);
        String refreshToken = createToken(user.getId(), refreshTokenExpiration);

        // 필요 시 Redis 등에 refreshToken 저장

        return new LoginResDto(accessToken, refreshToken);
    }

    private String createToken(Long userId, long expiration) {
        return Jwts.builder()
                .setSubject(String.valueOf(userId))
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }
}