package com.example.skeleton.global.webhook.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class DiscordWebhookConfig {
    @Bean
    public WebClient discordWebhookWebClient() {
        /*
         * 현재는 각 클라이언트의 discord url 정보가 없기 때문에 한 곳에 일괄적으로 보내진다.
         * 따라서 실제 서비스에 들어가려면 수정이 필요하다.
         */
        return WebClient.builder()
                .baseUrl(
                        "https://discord.com/api/webhooks/1171480929039028366/oNdHFvHouOfvbfTqTeLuHXYV-oJzO6_UThi3Cc5F5Jrs0sRNYs0M8lI0yvdAncEUqxJv")
                .build();

    }
}
