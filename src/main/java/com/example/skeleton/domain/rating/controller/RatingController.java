package com.example.skeleton.domain.rating.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.skeleton.domain.rating.dto.RatingRequestDto;
import com.example.skeleton.domain.rating.dto.RatingResponseDto;
import com.example.skeleton.domain.rating.service.RatingService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/api/ratings")
@RestController
public class RatingController {

    private final RatingService ratingService;

    @PostMapping
    public ResponseEntity<RatingResponseDto> createRating(@RequestBody RatingRequestDto reqDto) {
        RatingResponseDto respDto = ratingService.createRating(reqDto);
        return ResponseEntity.status(respDto.getStatus()).body(respDto);
    }
}
