package com.tave.brandary.global.oauth;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "oauth.kakao")
@Data
public class KakaoOauthProperties {
    private String clientId;
    private String redirectUri;
    private String tokenUri;
    private String userInfoUri;
}
