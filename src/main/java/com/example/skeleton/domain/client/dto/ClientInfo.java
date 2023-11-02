package com.example.skeleton.domain.client.dto;

import com.example.skeleton.domain.client.entity.Location;
import com.example.skeleton.domain.client.entity.Permission;

import jakarta.persistence.Embeddable;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ClientInfo {
    private String clientId;
    private Location location;
    private Permission permission;
}
