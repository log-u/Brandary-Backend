package com.tave.brandary.domain.auth.controller;

import com.tave.brandary.domain.auth.dto.LoginResDto;
import com.tave.brandary.domain.auth.dto.SocialLoginReqDto;
import com.tave.brandary.domain.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/oauth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<LoginResDto> login(@RequestBody SocialLoginReqDto request) {
        LoginResDto response = authService.loginOrSignUp(request);
        return ResponseEntity.ok(response);
    }
}