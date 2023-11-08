package com.example.skeleton.domain.gourmet.controller;

import com.example.skeleton.domain.gourmet.dto.GourmetDetailResponseDto;
import com.example.skeleton.domain.gourmet.dto.GourmetDistanceResponseDto;
import com.example.skeleton.domain.gourmet.mapper.GourmetMapper;
import com.example.skeleton.domain.gourmet.service.GourmetService;
import com.example.skeleton.domain.rating.entity.Rating;
import com.example.skeleton.domain.rating.mapper.RatingMapper;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        GourmetDetailResponseDto gourmetDetailResponseDto = gourmetMapper.gourmetToGourmetDetailResponseDto(gourmetService.getGourmet(gourmetId));

        // 총 평점, 평점 목록
        List<Rating> ratings = gourmetService.getRatingList(gourmetId);
        gourmetDetailResponseDto.setRating(ratings.stream().mapToDouble(Rating::getScore).sum() / ratings.size());
        gourmetDetailResponseDto.setRatingList(ratingMapper.ratingsToRatingInfos(ratings));
        return ResponseEntity.status(HttpStatus.OK).body(gourmetDetailResponseDto);
    }

    @GetMapping
    public ResponseEntity getGourmet( // todo : 필수 값 입력 안되면 에러 핸들링하기
            @RequestParam String lat,
            @RequestParam String lon,
            @RequestParam Double range,
            @RequestParam(required = false) String sort // default 값 거리순(distance) / 평점순(rating)
    ) {
        List<GourmetDistanceResponseDto> gourmetDistanceResponseDtoList = gourmetService.getGourmetDtoByLocation(lat, lon, range, sort);
        return ResponseEntity.status(HttpStatus.OK).body(gourmetDistanceResponseDtoList);
    }

}
