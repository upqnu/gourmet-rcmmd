package com.example.skeleton.domain.jwt.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RefreshToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String refreshToken;

    private String clientId;

    public RefreshToken(String refreshToken, String clientId) {
        this.refreshToken = refreshToken;
        this.clientId = clientId;
    }
}
