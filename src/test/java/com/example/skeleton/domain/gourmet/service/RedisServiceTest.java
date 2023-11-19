package com.example.skeleton.domain.gourmet.service;

import com.example.skeleton.domain.gourmet.entity.Gourmet;
import com.example.skeleton.domain.gourmet.repository.GourmetRepository;
import com.example.skeleton.global.model.Address;
import com.example.skeleton.global.model.Point;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
@SpringBootTest
class RedisServiceTest {

    @Autowired
    RedisService redisService;

    @Autowired
    GourmetRepository gourmetRepository;

    @Autowired
    GourmetService gourmetService;

    @Test
    void getGourmet(){
        // given
        Gourmet gourmet = Gourmet.builder()
                .name("음식점 1")
                .category("카테고리1")
                .point(Point.of(35.154455001236144, 129.01519285571078))
                .address(Address.of("도로명1", "16", "16639"))
                .isOpen("open").build();

        gourmet.updateRating(1.0);
        gourmetRepository.save(gourmet);

        // when
        Gourmet verifiedGourmet = gourmetService.verifiedGourmet(gourmet.getId());
        redisService.putRedis("gourmet::" + gourmet.getId().toString(), verifiedGourmet);

        // then
        assertThat(redisService.getRedisValue("gourmet::" + gourmet.getId().toString(), Gourmet.class).getId()).isEqualTo(verifiedGourmet.getId());
    }

}