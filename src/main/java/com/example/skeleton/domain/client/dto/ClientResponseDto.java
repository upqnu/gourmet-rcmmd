package com.example.skeleton.domain.client.dto;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.StreamingHttpOutputMessage.Body;

import jakarta.persistence.Embedded;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ClientResponseDto {

    private ClientInfo data;
    private Integer status;
    private String message;

}
