package com.example.skeleton.domain.rating.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.skeleton.domain.rating.entity.Rating;

public interface RatingRepository extends JpaRepository<Rating, Long> {
}
