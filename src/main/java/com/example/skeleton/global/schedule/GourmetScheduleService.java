package com.example.skeleton.global.schedule;

import com.example.skeleton.domain.gourmet.entity.Gourmet;
import com.example.skeleton.domain.gourmet.repository.GourmetRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@RequiredArgsConstructor
@Service
public class GourmetScheduleService {

    private final GourmetRepository gourmetRepository;

    @Async
    @Transactional
    public void saveGourmets(List<List<Gourmet>> gourmets) {
        AtomicInteger updateCount = new AtomicInteger();

        gourmets.parallelStream()
                .forEach(gourmet ->
                    gourmet.parallelStream()
                            .forEach(g -> saveNotExistGourmet(g, updateCount)));

        log.info("Gourmet information updated completed::{}", updateCount.get());
    }

    private void saveNotExistGourmet(Gourmet g, AtomicInteger count) {
        if (!gourmetRepository.existsByGourmetCode(g.getGourmetCode())) {
            gourmetRepository.save(g);
            count.getAndIncrement();
        }
    }

}