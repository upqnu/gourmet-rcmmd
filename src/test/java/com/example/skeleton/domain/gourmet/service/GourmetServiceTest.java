package com.example.skeleton.domain.gourmet.service;

import com.example.skeleton.domain.gourmet.entity.Gourmet;
import com.example.skeleton.domain.gourmet.repository.GourmetRepository;
import com.example.skeleton.global.model.Address;
import com.example.skeleton.global.model.Point;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;



@SpringBootTest
class GourmetServiceTest {

    @Autowired
    RedisService redisService;

    @Autowired
    GourmetRepository gourmetRepository;

    @Autowired
    GourmetService gourmetService;

}