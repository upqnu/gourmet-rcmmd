package com.example.skeleton.domain.jwt.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class RenewAccessTokenResponseDto {
    private String accessToken;

    public static RenewAccessTokenResponseDto of(String renewedAccessToken) {
        return new RenewAccessTokenResponseDto(renewedAccessToken);
    }
}
