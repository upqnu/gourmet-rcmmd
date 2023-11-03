package com.example.skeleton.global.schedule;

import com.example.skeleton.domain.gourmet.entity.Gourmet;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.hateoas.MediaTypes;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

@Slf4j
@RequiredArgsConstructor
@Component
public class ScheduledTasks {
    private static final String URL = "https://openapi.gg.go.kr";
    private final GourmetScheduleService gourmetScheduleService;
    private final WebClientService webClientService;
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    //todo: Refactoring 할 예정
    @Scheduled(fixedRate = 60 * 60 * 1000)
    public void reportCurrentTime() {
        Queue<List<Gourmet>> gourmets = new LinkedList<>();

        long chinaTotal = webClientService.getOpenApiTotalPage(URL, "Genrestrtchifood");
        long japanTotal = webClientService.getOpenApiTotalPage(URL, "Genrestrtjpnfood");
        long fastTotal = webClientService.getOpenApiTotalPage(URL, "Genrestrtfastfood");

        log.info("c:{} j:{} f:{}", chinaTotal, japanTotal, fastTotal);


        for(int i = 1; i < chinaTotal + 1; i++) {
            gourmets.add(webClientService.post(URL, OpenApiUrlPath.CHINA.getUrl(), i));
            log.info("China QUEUE: {}", i);
        }

        for(int i = 1; i < japanTotal + 1; i++) {
            gourmets.add(webClientService.post(URL, OpenApiUrlPath.JAPAN.getUrl(), i));
            log.info("Japan QUEUE: {}", i);
        }

        for(int i = 1; i < fastTotal + 1; i++) {
            gourmets.add(webClientService.post(URL, OpenApiUrlPath.FASTFOOD.getUrl(), i));
            log.info("Fast QUEUE: {}", i);
        }

        log.info("gourmets: {}", gourmets.size());

        //데이터 저장
        gourmetScheduleService.saveGourmets(gourmets);

        log.info("The time is now {}", dateFormat.format(new Date()));
    }
}
