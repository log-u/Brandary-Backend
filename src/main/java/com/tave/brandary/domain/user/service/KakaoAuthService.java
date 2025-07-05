package com.tave.brandary.domain.user.service;

import com.tave.brandary.domain.user.dto.KakaoTokenResDto;
import com.tave.brandary.domain.user.dto.KakaoUserInfo;
import com.tave.brandary.domain.user.dto.LoginResDto;
import com.tave.brandary.domain.user.entity.OAuthProvider;
import com.tave.brandary.domain.user.entity.User;
import com.tave.brandary.domain.user.repository.UserRepository;
import com.tave.brandary.global.jwt.JwtService;
import com.tave.brandary.global.oauth.KakaoOauthProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class KakaoAuthService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final KakaoOauthProperties kakaoOauthProperties;

    public LoginResDto kakaoLogin(String code) {
        String accessToken = getAccessToken(code);
        KakaoUserInfo kakaoUser = getUserInfo(accessToken);

        User user = userRepository.findByOauthProviderAndOauthId(OAuthProvider.KAKAO, kakaoUser.id())
                .orElseGet(() -> userRepository.save(
                        new User(OAuthProvider.KAKAO, kakaoUser.id(), kakaoUser.nickname())
                ));

        return jwtService.issueTokens(user); // access + refresh token 발급
    }

    private String getAccessToken(String code) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("grant_type", "authorization_code");
        body.add("client_id", kakaoOauthProperties.getClientId());
        body.add("redirect_uri", kakaoOauthProperties.getRedirectUri());
        body.add("code", code);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(body, headers);

        ResponseEntity<KakaoTokenResDto> response = restTemplate.postForEntity(
                kakaoOauthProperties.getTokenUri(),
                request,
                KakaoTokenResDto.class
        );

        return response.getBody().accessToken();
    }

    private KakaoUserInfo getUserInfo(String accessToken) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(accessToken);
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Void> request = new HttpEntity<>(headers);

        ResponseEntity<KakaoUserInfo> response = restTemplate.exchange(
                kakaoOauthProperties.getUserInfoUri(),
                HttpMethod.GET,
                request,
                KakaoUserInfo.class
        );

        return response.getBody();
    }
}
