package com.tave.brandary.domain.auth.dto;

public record LoginResDto(
        String accessToken,
        boolean isNewUser
) {
}
