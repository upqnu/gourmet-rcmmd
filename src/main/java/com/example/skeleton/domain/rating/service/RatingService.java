package com.example.skeleton.domain.rating.service;

import com.example.skeleton.domain.rating.dto.RatingRequestDto;
import com.example.skeleton.domain.rating.dto.RatingResponseDto;

public interface RatingService {
    public RatingResponseDto createRating(RatingRequestDto dto);
}
