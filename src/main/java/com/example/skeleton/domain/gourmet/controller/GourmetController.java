package com.example.skeleton.domain.gourmet.controller;

import com.example.skeleton.domain.gourmet.dto.GourmetResponseDto;
import com.example.skeleton.domain.gourmet.mapper.GourmetMapper;
import com.example.skeleton.domain.gourmet.service.GourmetService;
import com.example.skeleton.domain.rating.entity.Rating;
import com.example.skeleton.domain.rating.mapper.RatingMapper;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/gourmets")
@RequiredArgsConstructor
public class GourmetController {
    private final GourmetService gourmetService;
    private final GourmetMapper gourmetMapper;
    private final RatingMapper ratingMapper;

    @GetMapping("/{gourmetId}")
    public ResponseEntity getGourmet(
            @Positive @PathVariable Long gourmetId) {

        // 맛집 상세보기
        GourmetResponseDto gourmetResponseDto = gourmetMapper.gourmetToGourmetResponseDto(gourmetService.getGourmet(gourmetId));

        // 총 평점, 평점 목록
        List<Rating> ratings = gourmetService.getRatingList(gourmetId);
        gourmetResponseDto.setRating(ratings.stream().mapToDouble(Rating::getScore).sum() / ratings.size());
        gourmetResponseDto.setRatingList(ratingMapper.ratingsToRatingInfos(ratings));
        return ResponseEntity.status(HttpStatus.OK).body(gourmetResponseDto);
    }

}
