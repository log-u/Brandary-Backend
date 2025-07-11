package com.tave.brandary.domain.auth.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record KakaoUserInfo(
        @JsonProperty("id")
        String oauthId,

        @JsonProperty("kakao_account")
        KakaoAccount kakaoAccount
) {
    public record KakaoAccount(
            Profile profile,
            String email
    ) {
        public record Profile(
                String nickname,

                @JsonProperty("profile_image_url")
                String profileImageUrl,

                @JsonProperty("thumbnail_image_url")
                String thumbnailImageUrl
        ) {
        }
    }
}