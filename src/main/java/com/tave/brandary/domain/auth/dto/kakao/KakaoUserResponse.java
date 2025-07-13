package com.tave.brandary.domain.auth.dto.kakao;

import com.fasterxml.jackson.annotation.JsonProperty;

public record KakaoUserResponse(
        String id,
        @JsonProperty("kakao_account") KakaoAccount kakaoAccount
) {
    public record KakaoAccount(
            String email,
            KakaoProfile profile
    ) {}

    public record KakaoProfile(
            String nickname,
            @JsonProperty("profile_image_url") String profileImageUrl
    ) {}
}