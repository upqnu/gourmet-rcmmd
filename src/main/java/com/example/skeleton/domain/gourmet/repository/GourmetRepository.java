package com.example.skeleton.domain.gourmet.repository;

import com.example.skeleton.domain.gourmet.entity.Gourmet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GourmetRepository extends JpaRepository<Gourmet, Long> {
    boolean existsByGourmetCode(String gourmetCode);
}