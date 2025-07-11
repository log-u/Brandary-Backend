package com.tave.brandary.global.oauth;

import lombok.Data;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties(prefix = "oauth.kakao")
@Data
public class KakaoOauthProperties {
    private String clientId;
    private String redirectUri;
    private String tokenUri;
    private String userInfoUri;
}
