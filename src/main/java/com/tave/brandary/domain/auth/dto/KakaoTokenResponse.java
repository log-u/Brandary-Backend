package com.tave.brandary.domain.auth.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record KakaoTokenResponse(
        @JsonProperty("access_token")
        String accessToken
) {
}
