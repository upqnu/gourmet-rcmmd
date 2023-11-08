package com.example.skeleton.domain.gourmet.repository;

import com.example.skeleton.domain.gourmet.dto.GourmetDistanceResponseDto;

import java.awt.print.Pageable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public interface GourmetRepositoryCustom {

    List<GourmetDistanceResponseDto> getGourmetDtoByLocation(String lat, String lon, Double range, String search);
}
