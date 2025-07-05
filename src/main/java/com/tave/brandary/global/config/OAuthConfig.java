package com.tave.brandary.global.config;

import com.tave.brandary.global.oauth.KakaoOauthProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(KakaoOauthProperties.class)
public class OAuthConfig {
}
