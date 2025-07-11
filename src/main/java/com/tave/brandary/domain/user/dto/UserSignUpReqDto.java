package com.tave.brandary.domain.user.dto;

public record UserSignUpReqDto(
        String oauthId,
        String email,
        String nickname,
        String profileImageUrl,
        String thumbnailImageUrl
) {}