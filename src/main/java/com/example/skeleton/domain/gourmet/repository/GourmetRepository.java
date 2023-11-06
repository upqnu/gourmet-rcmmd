package com.example.skeleton.domain.gourmet.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.skeleton.domain.gourmet.entity.Gourmet;

public interface GourmetRepository extends JpaRepository<Gourmet, Long> {
    boolean existsByGourmetCode(String gourmetCode);

    Optional<Gourmet> findByGourmetCode(String gourmetCode);
}