package com.example.skeleton.domain.client.controller;

import com.example.skeleton.domain.client.dto.ClientSignInRequestDto;
import com.example.skeleton.domain.client.service.ClientSignInService;
import com.example.skeleton.domain.jwt.dto.AccessTokenCreationResponseDto;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.skeleton.domain.client.dto.ClientRequestDto;
import com.example.skeleton.domain.client.dto.ClientResponseDto;
import com.example.skeleton.domain.client.service.ClientService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/api/clients")
@RestController
public class ClientController {

    private final ClientService clientService;
    private final ClientSignInService clientSignInService;

    @PostMapping("/sign-up")
    public ResponseEntity<ClientResponseDto> signUp(@RequestBody ClientRequestDto resqDto) {
        ClientResponseDto respDto = clientService.signUp(resqDto);
        return ResponseEntity.status(respDto.getStatus()).body(respDto);
    }

    @PostMapping("/sign-in")
    public ResponseEntity<AccessTokenCreationResponseDto> signIn(
            @RequestBody
            @Valid
            ClientSignInRequestDto clientSignInRequestDto) {
        return ResponseEntity.ok(clientSignInService.signIn(clientSignInRequestDto));
    }

    @PutMapping
    public ResponseEntity<ClientResponseDto> setClientInfo(@RequestBody ClientRequestDto resqDto) {
        ClientResponseDto respDto = clientService.setClientInfo(resqDto);
        return ResponseEntity.status(respDto.getStatus()).body(respDto);
    }

    @GetMapping("/{clientId}")
    public ResponseEntity<ClientResponseDto> getClientInfo(@PathVariable String clientId) {
        ClientResponseDto respDto = clientService.getClientInfo(clientId);
        return ResponseEntity.status(respDto.getStatus()).body(respDto);
    }
}
