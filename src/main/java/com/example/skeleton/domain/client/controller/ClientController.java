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

    /**
     * 회원 가입
     * @param resqDto
     */
    @PostMapping("/sign-up")
    public ResponseEntity<ClientResponseDto> signUp(@RequestBody ClientRequestDto reqDto) {
        ClientResponseDto respDto = clientService.signUp(reqDto);
        return ResponseEntity.status(respDto.getStatus()).body(respDto);
    }

    /**
     * 로그인(동시에 액세스 토큰 발급)
     * @param clientSignInRequestDto
     */
    @PostMapping("/sign-in")
    public ResponseEntity<AccessTokenCreationResponseDto> signIn(
            @RequestBody
            @Valid
            ClientSignInRequestDto clientSignInRequestDto) {
        return ResponseEntity.ok(clientSignInService.signIn(clientSignInRequestDto));
    }

    /**
     * 회원정보 수정
     * @param resqDto
     */
    @PutMapping
    public ResponseEntity<ClientResponseDto> setClientInfo(@RequestBody ClientRequestDto reqDto) {
        ClientResponseDto respDto = clientService.setClientInfo(reqDto);
        return ResponseEntity.status(respDto.getStatus()).body(respDto);
    }

    /**
     * 회원정보 조회
     * @param clientId
     */
    @GetMapping("/{clientId}")
    public ResponseEntity<ClientResponseDto> getClientInfo(@PathVariable String clientId) {
        ClientResponseDto respDto = clientService.getClientInfo(clientId);
        return ResponseEntity.status(respDto.getStatus()).body(respDto);
    }
}
