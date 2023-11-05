package com.example.skeleton.domain.district.service;

import com.example.skeleton.IntegrationTest;
import com.example.skeleton.domain.client.entity.Location;
import com.example.skeleton.domain.district.entity.District;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Map;

class DistrictServiceTest extends IntegrationTest {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Test
    void cachingSggInRedis() {
        // given
        District testDistrict1 = District.of(1L, "강원", "강릉시", Location.of("128.8784972",	"37.74913611"));
        HashOperations<String, String, String> hashDistricts = redisTemplate.opsForHash();
        String key = "District::" + testDistrict1.getDosi() + "::" + testDistrict1.getSgg();

        // when
        hashDistricts.putAll(key, testDistrict1.getLocation().toMap());

        // then
        Map<String, String> locationMap = hashDistricts.entries(key);
        Assertions.assertThat(locationMap.keySet()).containsExactly("latitude", "longitude");
        Assertions.assertThat(locationMap.values()).containsExactly(testDistrict1.getLocation().getLatitude(), testDistrict1.getLocation().getLongitude());

        Long size = hashDistricts.size(key);
        Assertions.assertThat(size).isEqualTo(locationMap.size());

        redisTemplate.delete(key);
    }
}