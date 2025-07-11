package com.tave.brandary.domain.user.controller;

import com.tave.brandary.domain.user.dto.KakaoLoginReqDto;
import com.tave.brandary.domain.user.dto.LoginResDto;
import com.tave.brandary.domain.user.dto.UserResDto;
import com.tave.brandary.domain.user.dto.UserSignUpReqDto;
import com.tave.brandary.domain.user.entity.User;
import com.tave.brandary.domain.user.service.UserService;
import com.tave.brandary.global.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    private final JwtService jwtService;

    @PostMapping("/signup")
    public ResponseEntity<UserResDto> signUp(@RequestBody UserSignUpReqDto request) {
        return ResponseEntity.ok(userService.signUp(request));
    }

    @PostMapping("/login/kakao")
    public ResponseEntity<LoginResDto> login(@RequestBody KakaoLoginReqDto request) {
        User user = userService.findByOauthId(request.oauthId())
                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 사용자입니다."));

        LoginResDto tokens = jwtService.issueTokens(user);
        return ResponseEntity.ok(tokens);
    }
}