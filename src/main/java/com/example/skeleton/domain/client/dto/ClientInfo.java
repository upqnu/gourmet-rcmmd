package com.example.skeleton.domain.client.dto;

import com.example.skeleton.domain.client.entity.Permission;
import com.example.skeleton.global.model.Point;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ClientInfo {
    private String clientId;
    private Point point;
    private Permission permission;
}
