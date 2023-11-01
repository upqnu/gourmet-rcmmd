package com.example.skeleton.domain.district.controller;

import com.example.skeleton.domain.district.entity.District;
import com.example.skeleton.domain.district.service.DistrictService;
import lombok.RequiredArgsConstructor;
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
     * 시군구 위도, 경도 데이터를 담은 csv 업로드
     * @param file - MultipartFile : csv file
     * */
    @PostMapping("/upload")
    public ResponseEntity<?> uploadSggCsv(
            @RequestParam("file")
            MultipartFile file) throws IOException {

        districtService.uploadSggCsv(file);
        return ResponseEntity.ok("");
    }
}
