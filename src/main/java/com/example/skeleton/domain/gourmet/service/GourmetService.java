package com.example.skeleton.domain.gourmet.service;

import com.example.skeleton.domain.gourmet.entity.Gourmet;
import com.example.skeleton.domain.gourmet.repository.GourmetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

// todo : Interface 구현
@Service
@Transactional
@RequiredArgsConstructor
public class GourmetService {

    private final GourmetRepository gourmetRepository;

    public Gourmet getGourmet(Long id) {
        return verifiedGourmet(id);
    }

    private Gourmet verifiedGourmet(Long id) {
        return gourmetRepository.findById(id).orElseThrow(() -> new RuntimeException("음식점 정보가 없습니다.")); // todo : Error code 분리하기
    }
}
