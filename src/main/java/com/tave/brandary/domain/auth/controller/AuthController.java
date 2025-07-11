package com.tave.brandary.domain.auth.controller;

import com.tave.brandary.domain.auth.client.KakaoApiClient;
import com.tave.brandary.domain.auth.client.KakaoTokenClient;
import com.tave.brandary.domain.auth.dto.KakaoUserInfo;
import com.tave.brandary.domain.user.dto.UserSignUpReqDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/oauth")
public class AuthController {

    private final KakaoTokenClient kakaoTokenClient;
    private final KakaoApiClient kakaoApiClient;

    @PostMapping("/kakao/callback")
    public ResponseEntity<UserSignUpReqDto> kakaoCallback(@RequestParam("code") String code) {
        String accessToken = kakaoTokenClient.requestAccessToken(code);
        System.out.println("accessToken = " + accessToken);

        KakaoUserInfo userInfo = kakaoApiClient.getUserInfo(accessToken);
        System.out.println("kakaoUserInfo = " + userInfo);

        return ResponseEntity.ok(
                new UserSignUpReqDto(
                        userInfo.oauthId(),
                        userInfo.kakaoAccount().email(),
                        userInfo.kakaoAccount().profile().nickname(),
                        userInfo.kakaoAccount().profile().profileImageUrl()
                )
        );
    }
}