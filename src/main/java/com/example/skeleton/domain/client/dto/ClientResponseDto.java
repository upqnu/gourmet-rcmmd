package com.example.skeleton.domain.client.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ClientResponseDto {

    private ClientInfo data;
    private Integer status;
    private String message;

}
