package com.example.skeleton.global.webhook.scheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RestController;

import com.example.skeleton.global.webhook.service.DiscordWebhookService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
public class DiscordWebhookScheduler {

    private final DiscordWebhookService discordWebhookService;

    @Scheduled(cron = "0 30 11 * * ?")
    public void executeDiscordWebhook() {

        try {
            discordWebhookService.executeDiscordWebhook();
            log.info("전송 성공");
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }
}