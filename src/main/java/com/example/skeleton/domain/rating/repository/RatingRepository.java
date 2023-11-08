package com.example.skeleton.domain.rating.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.skeleton.domain.gourmet.entity.Gourmet;
import com.example.skeleton.domain.rating.entity.Rating;

import java.util.List;

public interface RatingRepository extends JpaRepository<Rating, Long> {
    public Long countByGourmet(Gourmet gourmet);
    public List<Rating> findAllByGourmet(Gourmet gourmet);
}
