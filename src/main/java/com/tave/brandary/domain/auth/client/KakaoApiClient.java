package com.tave.brandary.domain.auth.client;

import com.tave.brandary.domain.auth.dto.KakaoUserInfo;
import com.tave.brandary.global.oauth.KakaoOauthProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class KakaoApiClient {
    private final RestTemplate restTemplate;
    private final KakaoOauthProperties properties;

    public KakaoUserInfo getUserInfo(String accessToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(accessToken);
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        HttpEntity<Void> request = new HttpEntity<>(headers);
        ResponseEntity<KakaoUserInfo> response = restTemplate.exchange(
                properties.getUserInfoUri(), HttpMethod.GET, request, KakaoUserInfo.class);

        return response.getBody();
    }
}