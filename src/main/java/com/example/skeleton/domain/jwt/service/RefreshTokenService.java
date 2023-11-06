package com.example.skeleton.domain.jwt.service;

import com.example.skeleton.domain.client.entity.Client;
import com.example.skeleton.domain.client.repository.ClientRepository;
import com.example.skeleton.domain.jwt.entity.RefreshToken;
import com.example.skeleton.domain.jwt.repository.RefreshTokenRepository;
import com.example.skeleton.global.config.jwt.JwtTokenProvider;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final ClientRepository clientRepository;

    /**
     * 리프레시 토큰을 통해 액세스 토큰 만료기한 갱신
     * @param refreshToken
     */
    public String renewAccessTokenByRefreshToken(String refreshToken) {

        // 1. 현재 사용자의 clientId 찾기
        String clientId = jwtTokenProvider.getClientIdFromToken();

        // 2. 주어진 refreshToken 유효성 검사
        if (!jwtTokenProvider.validateToken(refreshToken)) {
            throw new IllegalArgumentException("유효하지 않은 refreshToken입니다.");
        }

        // 3. 리프레시 토큰 생성 및 저장
        RefreshToken refreshTokenForRenewal = new RefreshToken(refreshToken, clientId);
        refreshTokenRepository.save(refreshTokenForRenewal);

        // 4. 새로운 액세스 토큰 생성
        Client client = clientRepository.findByClientId(clientId)
                .orElseThrow(() -> new EntityNotFoundException("Client를 찾을 수 없습니다: " + clientId));
        String newAccessToken = jwtTokenProvider.issueToken(client, "access");

        return newAccessToken;
    }
}

