package com.example.skeleton.domain.jwt.controller;

import com.example.skeleton.domain.jwt.dto.RenewAccessTokenRequestDto;
import com.example.skeleton.domain.jwt.dto.RenewAccessTokenResponseDto;
import com.example.skeleton.domain.jwt.service.RefreshTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/token")
@RequiredArgsConstructor
public class RefreshTokenController {

    private final RefreshTokenService refreshTokenService;

    /**
     * 리프레시 토큰을 통해 액세스 토큰 만료기한 갱신
     * @param requestDto
     */
    @PostMapping
    public ResponseEntity<RenewAccessTokenResponseDto> renewAccessTokenByRefreshToken(
            @RequestBody RenewAccessTokenRequestDto requestDto
    ) {
        String renewedAccessToken = refreshTokenService.renewAccessTokenByRefreshToken(requestDto.getRefreshToken());
        return ResponseEntity.ok(RenewAccessTokenResponseDto.of(renewedAccessToken));
    }

}
