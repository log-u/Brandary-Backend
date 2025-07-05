package com.tave.brandary.global.jwt;

import com.tave.brandary.domain.user.dto.LoginResDto;
import com.tave.brandary.domain.user.entity.User;
import org.springframework.stereotype.Service;

@Service
public class JwtService {
    public LoginResDto issueTokens(User user) {
        String accessToken = ... // JWT 생성
        String refreshToken = ... // Refresh 토큰 생성
        return new LoginResDto(accessToken, refreshToken);
    }
}
