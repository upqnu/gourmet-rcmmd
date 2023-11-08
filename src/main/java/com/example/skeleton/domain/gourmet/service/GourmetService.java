package com.example.skeleton.domain.gourmet.service;

import com.example.skeleton.domain.gourmet.dto.GourmetDistanceResponseDto;
import com.example.skeleton.domain.gourmet.entity.Gourmet;
import com.example.skeleton.domain.gourmet.repository.GourmetRepository;
import com.example.skeleton.domain.rating.entity.Rating;
import com.example.skeleton.domain.rating.repository.RatingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

// todo : Interface 구현
@Service
@Transactional
@RequiredArgsConstructor
public class GourmetService {

    private final GourmetRepository gourmetRepository;
    private final RatingRepository ratingRepository;

    public Gourmet getGourmet(Long id) {
        return verifiedGourmet(id);
    }

    public List<Rating> getRatingList(Long id) {
        return ratingRepository.findAllByGourmet(verifiedGourmet(id));
    }

    public List<GourmetDistanceResponseDto> getGourmetDtoByLocation(String lat, String lon, Double range, String sort) {
        List<GourmetDistanceResponseDto> gourmetDistanceResponseDtoList = gourmetRepository.getGourmetDtoByLocation(lat, lon, range, sort);
        if (!Objects.equals(sort, "rating")) {
            return gourmetDistanceResponseDtoList.stream().sorted(Comparator.comparing(GourmetDistanceResponseDto::getDistance)).collect(Collectors.toList());
        }
        else {
            return gourmetDistanceResponseDtoList;
        }
    }

    private Gourmet verifiedGourmet(Long id) {
        return gourmetRepository.findById(id).orElseThrow(() -> new RuntimeException("음식점 정보가 없습니다.")); // todo : Error code 분리하기
    }
}
