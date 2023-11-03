package com.example.skeleton.domain.client.dto;

import com.example.skeleton.domain.client.entity.Permission;

import lombok.Getter;

@Getter
public class ClientRequestDto {
    private String clientId;
    private String password;
    private String latitude;
    private String longitude;
    private Permission permission;
}
