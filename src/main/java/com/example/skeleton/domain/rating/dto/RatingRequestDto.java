package com.example.skeleton.domain.rating.dto;

import lombok.Getter;

@Getter
public class RatingRequestDto {
    private String clientId;
    private String gourmetCode;
    private Integer score;
    private String content;
}
