package com.tave.brandary.domain.user.dto;

public record UserResDto(
        Long userId,
        String nickname,
        String email,
        String profileImageUrl,
        String thumbnailImageUrl
) {}
