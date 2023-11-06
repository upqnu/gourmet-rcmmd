package com.example.skeleton.domain.jwt.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class AccessTokenCreationResponseDto {

    private String accessToken;

    public static AccessTokenCreationResponseDto of(String newAccessToken) {
        return new AccessTokenCreationResponseDto(newAccessToken);
    }
}
