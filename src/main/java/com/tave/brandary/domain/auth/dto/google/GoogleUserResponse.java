package com.tave.brandary.domain.auth.dto.google;

public record GoogleUserResponse(
        String id,
        String email,
        String name,
        String picture
) {
}
