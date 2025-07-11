package com.tave.brandary.domain.auth.dto;

public record KakaoLoginResultDto(
        Long memberId,
        boolean isRegistered,
        String email,
        String nickname
) {}