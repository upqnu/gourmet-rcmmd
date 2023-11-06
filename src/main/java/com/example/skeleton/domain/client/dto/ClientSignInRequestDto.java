package com.example.skeleton.domain.client.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ClientSignInRequestDto {

    @NotNull
    private String clientId;

    @NotNull
    private String password;

    public static ClientSignInRequestDto of(String clientId, String password) {
        return new ClientSignInRequestDto(clientId, password);
    }
}
