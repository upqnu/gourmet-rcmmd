package com.example.skeleton.domain.client.dto;

import com.example.skeleton.domain.client.entity.Permission;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientRequestDto {
    @NotBlank(message = "아이디를 입력해 주세요.")
    private String clientId;
    private String password;
    private String latitude;
    private String longitude;
    private Permission permission;

    public static ClientRequestDto of(String clientId, String password) {
        ClientRequestDto clientRequestDto = new ClientRequestDto();
        clientRequestDto.setClientId(clientId);
        clientRequestDto.setPassword(password);
        return clientRequestDto;
    }
}
