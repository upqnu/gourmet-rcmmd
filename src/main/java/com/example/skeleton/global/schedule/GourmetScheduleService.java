package com.example.skeleton.global.schedule;

import com.example.skeleton.domain.gourmet.entity.Gourmet;
import com.example.skeleton.domain.gourmet.repository.GourmetRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Queue;

@Slf4j
@RequiredArgsConstructor
@Service
public class GourmetScheduleService {

    private final GourmetRepository gourmetRepository;

    @Transactional
    public void saveGourmets(Queue<List<Gourmet>> gourmets) {
        gourmets.parallelStream()
                .forEach(gourmet ->
                    gourmet.parallelStream()
                            .peek(g -> {
                                if (!gourmetRepository.existsByGourmetCode(g.getGourmetCode()))
                                    gourmetRepository.save(g);
                            }).toList());
    }

}