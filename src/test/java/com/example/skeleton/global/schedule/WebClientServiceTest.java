package com.example.skeleton.global.schedule;

import com.example.skeleton.IntegrationTest;
import com.example.skeleton.domain.gourmet.repository.GourmetRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;


class WebClientServiceTest extends IntegrationTest {

    @Autowired
    GourmetRepository gourmetRepository;
    @Autowired
    WebClientService webClientService;

    //todo: test 코드 추가 예정
    @Test
    void post() {
//        Queue<List<Gourmet>> gourmets = new LinkedList();
//        List<Gourmet> chineseFood = webClientService.post("https://openapi.gg.go.kr", "Genrestrtchifood");
//        List<Gourmet> japanFood = webClientService.post("https://openapi.gg.go.kr", "Genrestrtjpnfood");
//        List<Gourmet> fastFood = webClientService.post("https://openapi.gg.go.kr", "Genrestrtfastfood");
//
//        List<Gourmet> collect = Stream.concat(chineseFood.stream(), japanFood.stream()).collect(Collectors.toList());
//        collect = Stream.concat(collect.stream(), fastFood.stream()).collect(Collectors.toList());
//
//        gourmetRepository.saveAll(collect);
    }

}