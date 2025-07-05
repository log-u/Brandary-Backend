package com.tave.brandary.domain.user.controller;

import com.tave.brandary.domain.user.dto.KakaoLoginReqDto;
import com.tave.brandary.domain.user.dto.LoginResDto;
import com.tave.brandary.domain.user.service.KakaoAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final KakaoAuthService kakaoAuthService;

    @PostMapping("/kakao")
    public ResponseEntity<LoginResDto> loginWithKakao(@RequestBody KakaoLoginReqDto request) {
        LoginResDto response = kakaoAuthService.kakaoLogin(request.code());
        return ResponseEntity.ok(response);
    }
}
