package com.example.skeleton.domain.rating.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class RatingResponseDto {
    private Integer status;
    private String message;
}
