package com.example.skeleton.domain.client.service;

import com.example.skeleton.domain.client.dto.ClientResponseDto;
import com.example.skeleton.domain.client.dto.ClientRequestDto;

public interface ClientService {
    public ClientResponseDto signUp(ClientRequestDto dto);

    public ClientResponseDto setClientInfo(ClientRequestDto dto);

    public ClientResponseDto getClientInfo(String clientId);
}
