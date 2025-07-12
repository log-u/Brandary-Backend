package com.tave.brandary.domain.auth.dto.google;

import com.fasterxml.jackson.annotation.JsonProperty;

public record GoogleTokenResponse(
        @JsonProperty("access_token")
                String accessToken
) {
}
