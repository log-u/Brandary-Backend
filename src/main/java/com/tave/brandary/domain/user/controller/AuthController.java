package com.tave.brandary.domain.user.controller;

import com.tave.brandary.domain.user.dto.KakaoLoginReqDto;
import com.tave.brandary.domain.user.dto.LoginResDto;
import com.tave.brandary.domain.user.service.KakaoAuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/oauth")
@RequiredArgsConstructor
@Slf4j
public class AuthController {
    private final KakaoAuthService kakaoAuthService;

    @PostMapping("/kakao/callback")
    public ResponseEntity<LoginResDto> loginWithKakao(@RequestBody KakaoLoginReqDto request) {
        log.info("code : " + request.code());
        LoginResDto response = kakaoAuthService.kakaoLogin(request.code());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/kakao/callback")
    public ResponseEntity<LoginResDto> kakaoCallback(@RequestParam("code") String code) {
        log.info("code : " + code);
        LoginResDto response = kakaoAuthService.kakaoLogin(code);
        return ResponseEntity.ok(response);
    }
}
