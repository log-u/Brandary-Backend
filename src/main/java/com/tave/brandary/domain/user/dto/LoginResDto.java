package com.tave.brandary.domain.user.dto;

public record LoginResDto(
        String accessToken,
        String refreshToken
) {}