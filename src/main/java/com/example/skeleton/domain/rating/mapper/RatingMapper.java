package com.example.skeleton.domain.rating.mapper;

import com.example.skeleton.domain.rating.dto.RatingInfo;
import com.example.skeleton.domain.rating.dto.RatingResponseDto;
import com.example.skeleton.domain.rating.entity.Rating;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RatingMapper {

    RatingResponseDto ratingToRatingResponseDto(Rating rating);
    List<RatingInfo> ratingsToRatingInfos(List<Rating> rating);
}
