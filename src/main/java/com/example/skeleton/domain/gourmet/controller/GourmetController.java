package com.example.skeleton.domain.gourmet.controller;

import com.example.skeleton.domain.gourmet.dto.GourmetResponseDto;
import com.example.skeleton.domain.gourmet.mapper.GourmetMapper;
import com.example.skeleton.domain.gourmet.service.GourmetService;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/gourmets")
@RequiredArgsConstructor
public class GourmetController {
    private final GourmetService gourmetService;
    private final GourmetMapper gourmetMapper;

    @GetMapping("/{gourmetId}")
    public ResponseEntity getGourmet(
            @Positive @PathVariable Long gourmetId) {
        GourmetResponseDto gourmetResponseDto = gourmetMapper.gourmetToGourmetResponseDto(gourmetService.getGourmet(gourmetId));
        return ResponseEntity.status(HttpStatus.OK).body(gourmetResponseDto);
    }

}
