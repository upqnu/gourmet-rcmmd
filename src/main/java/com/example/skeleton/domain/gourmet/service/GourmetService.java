package com.example.skeleton.domain.gourmet.service;

import com.example.skeleton.domain.gourmet.dto.GourmetDistancePageResponseDto;
import com.example.skeleton.domain.gourmet.dto.GourmetDistanceResponseDto;
import com.example.skeleton.domain.gourmet.entity.Gourmet;
import com.example.skeleton.domain.gourmet.repository.GourmetRepository;
import com.example.skeleton.domain.rating.entity.Rating;
import com.example.skeleton.domain.rating.repository.RatingRepository;
import com.example.skeleton.global.model.PageInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
        List<GourmetDistanceResponseDto> gourmetDistanceResponseDtoList = gourmetRepository.getGourmetDtoByLocation(lat, lon, range);
        if (!Objects.equals(sort, "rating")) {
            return gourmetDistanceResponseDtoList.stream().sorted(Comparator.comparing(GourmetDistanceResponseDto::getDistance)).collect(Collectors.toList());
        } else {
            return gourmetDistanceResponseDtoList;
        }
    }

    public GourmetDistancePageResponseDto getGourmetDistancePageResponseDto(int page, int size, List<GourmetDistanceResponseDto> gourmetDistanceResponseDtoList) {

        int totalElements = gourmetDistanceResponseDtoList.size();
        int totalPages = totalElements < size ? 1 : (totalElements / size) + (totalElements % size != 0 ? 1 : 0);

        GourmetDistancePageResponseDto gourmetDistancePageResponseDto = GourmetDistancePageResponseDto.builder().pageInfo(new PageInfo(page, size, totalElements, totalPages)).build();

        int start = size * page - size;
        int end = Math.min(size * (page), totalElements);

        gourmetDistancePageResponseDto.setGourmetList(new ArrayList<>());
        for (int i = start; i < end ; i++) {
            gourmetDistancePageResponseDto.getGourmetList().add(gourmetDistanceResponseDtoList.get(i));
        }

        return gourmetDistancePageResponseDto;
    }

    private Gourmet verifiedGourmet(Long id) {
        return gourmetRepository.findById(id).orElseThrow(() -> new RuntimeException("음식점 정보가 없습니다.")); // todo : Error code 분리하기
    }
}
