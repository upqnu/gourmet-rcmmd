package com.example.skeleton.domain.district.controller;

import com.example.skeleton.domain.district.entity.District;
import com.example.skeleton.domain.district.service.DistrictService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RequestMapping("/api/sgg")
@RestController
@RequiredArgsConstructor
public class DistrictController {

    private final DistrictService districtService;

    /**
     * caching을 이용하지 않고 DB에서 시군구 조회
     * */
    @GetMapping("/all-without-redis")
    public ResponseEntity<List<District>> getAllDistrictsWithoutRedis() {
        return ResponseEntity.ok(districtService.getAllDistrictsWithoutRedis());
    }

    /**
     * caching을 통한 시군구 조회
     * */
    @GetMapping("/all")
    public ResponseEntity<List<District>> getAllDistricts() {
        return ResponseEntity.ok(districtService.getAllDistricts());
    }

    /**
     * 시군구 위도, 경도 데이터를 담은 csv 업로드
     * @param file - MultipartFile : csv file
     * */
    @PostMapping("/upload")
    public ResponseEntity<Void> uploadSggCsv(
            @RequestParam("file")
            MultipartFile file) throws IOException {

        districtService.uploadSggCsv(file);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
