package com.example.skeleton.domain.jwt.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RenewAccessTokenRequestDto {
    private String refreshToken;
}
