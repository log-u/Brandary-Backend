package com.tave.brandary.domain.auth.client;

import com.tave.brandary.domain.auth.dto.KakaoTokenResponse;
import com.tave.brandary.domain.auth.dto.KakaoUserResponse;
import com.tave.brandary.domain.auth.dto.SocialUserInfo;
import com.tave.brandary.global.oauth.KakaoOauthProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
@RequiredArgsConstructor
public class KakaoApiClient implements SocialApiClient {
    private final WebClient webClient;
    private final KakaoOauthProperties kakaoOauthProperties;

    public SocialUserInfo getUserInfoByCode(String code) {
        String accessToken = getAccessToken(code);
        return getUserInfo(accessToken);
    }

    private String getAccessToken(String code) {
        return webClient.post()
                .uri("https://kauth.kakao.com/oauth/token")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .bodyValue(
                        "grant_type=authorization_code" +
                                "&client_id=" + kakaoOauthProperties.getClientId() +
                                "&redirect_uri=" + kakaoOauthProperties.getRedirectUri() +
                                "&code=" + code
                )
                .retrieve()
                .bodyToMono(KakaoTokenResponse.class)
                .map(KakaoTokenResponse::accessToken)
                .block(); // 단일 호출이므로 동기 처리
    }

    private SocialUserInfo getUserInfo(String accessToken) {
        KakaoUserResponse kakaoUser = webClient.get()
                .uri("https://kapi.kakao.com/v2/user/me")
                .headers(headers -> {
                    headers.setBearerAuth(accessToken);
                    headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
                })
                .retrieve()
                .bodyToMono(KakaoUserResponse.class)
                .block();

        return new SocialUserInfo(
                String.valueOf(kakaoUser.id()),
                kakaoUser.kakaoAccount().email(),
                kakaoUser.kakaoAccount().profile().nickname(),
                kakaoUser.kakaoAccount().profile().profileImageUrl()
        );
    }
}