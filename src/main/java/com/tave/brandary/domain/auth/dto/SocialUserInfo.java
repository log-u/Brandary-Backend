package com.tave.brandary.domain.auth.dto;

public record SocialUserInfo(
        String socialId,
        String email,
        String nickname,
        String profileImageUrl
) {
}
