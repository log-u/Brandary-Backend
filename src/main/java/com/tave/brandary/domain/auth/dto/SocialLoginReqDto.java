package com.tave.brandary.domain.auth.dto;

import com.tave.brandary.domain.user.entity.SocialProvider;

public record SocialLoginReqDto(
        String code,
        SocialProvider provider
) {
}
