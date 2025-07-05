package com.tave.brandary.domain.user.dto;


import com.fasterxml.jackson.annotation.JsonProperty;


public record KakaoUserInfo(
        @JsonProperty("id") String id,
        @JsonProperty("properties") Properties properties
) {
    public String nickname() {
        return properties.nickname();
    }

    public record Properties(@JsonProperty("nickname") String nickname) {}
}