package com.example.skeleton.domain.jwt.repository;

import com.example.skeleton.domain.jwt.entity.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
}
