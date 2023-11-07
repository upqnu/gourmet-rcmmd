package com.example.skeleton.global.schedule;

import com.example.skeleton.domain.gourmet.entity.Gourmet;
import com.example.skeleton.global.schedule.model.OpenApiUrlPath;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.*;

@Slf4j
@RequiredArgsConstructor
@Component
public class ScheduledTasks {
    private final GourmetScheduleService gourmetScheduleService;
    private final WebClientService webClientService;
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    //매주 월요일 새벽 4시에 실행
    @Scheduled(cron = "0 0 4 ? * MON", zone = "Asia/Seoul")
    public void reportCurrentTime() {
        log.info("Scheduling process start::{}", dateFormat.format(new Date()));
        List<List<Gourmet>> gourmets = new ArrayList<>();

        long chinaTotal = webClientService.getOpenApiTotalPage(OpenApiUrlPath.CHINA.getPath());
        long japanTotal = webClientService.getOpenApiTotalPage(OpenApiUrlPath.JAPAN.getPath());
        long fastTotal = webClientService.getOpenApiTotalPage(OpenApiUrlPath.FASTFOOD.getPath());

        log.info("china:{} japan:{} Fastfood:{} total:{}",
                chinaTotal,
                japanTotal,
                fastTotal,
                chinaTotal + japanTotal + fastTotal);

        for(int i = 1; i < chinaTotal + 1; i++) {
            gourmets.add(webClientService.post(OpenApiUrlPath.CHINA.getPath(), i));
        }

        for(int i = 1; i < japanTotal + 1; i++) {
            gourmets.add(webClientService.post(OpenApiUrlPath.JAPAN.getPath(), i));
        }

        for(int i = 1; i < fastTotal + 1; i++) {
            gourmets.add(webClientService.post(OpenApiUrlPath.FASTFOOD.getPath(), i));
        }

        //불러온 데이터 저장
        gourmetScheduleService.saveGourmets(gourmets);
        log.info("Scheduling process done::{}", dateFormat.format(new Date()));
    }
}
