package com.example.skeleton.domain.client.service;

import com.example.skeleton.domain.client.dto.ClientSignInRequestDto;
import com.example.skeleton.domain.client.entity.Client;
import com.example.skeleton.domain.client.repository.ClientRepository;
import com.example.skeleton.domain.jwt.dto.AccessTokenCreationResponseDto;
import com.example.skeleton.global.config.jwt.JwtTokenProvider;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ClientSignInService {

    private final ClientRepository clientRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    /**
     * 로그인 & 동시에 액세스 토큰 발급
     * @param clientSignInRequestDto
     */
    public AccessTokenCreationResponseDto signIn(ClientSignInRequestDto clientSignInRequestDto) {
        Client client = clientRepository.findByClientId(clientSignInRequestDto.getClientId())
                .orElseThrow(() -> new EntityNotFoundException("클라이언트를 찾을 수 없습니다."));

        if (!passwordEncoder.matches(clientSignInRequestDto.getPassword(), client.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        // access token  생성 후 반환
        return AccessTokenCreationResponseDto.of(jwtTokenProvider.issueToken(client, "access"));
    }
}
