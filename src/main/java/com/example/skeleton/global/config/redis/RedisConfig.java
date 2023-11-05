package com.example.skeleton.global.config.redis;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
public class RedisConfig {

    /*
       Redis 설정 참고 : https://bcp0109.tistory.com/328
       Spring Boot Redis를 사용하는 방법은 두가지 RedisRepository와 RedisTemplate.
    */

    @Value("${spring.redis.host}")
    private String host;

    @Value("${spring.redis.port}")
    private int port;

    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        return new LettuceConnectionFactory(host, port);
    }

    @Bean
    public RedisTemplate<?, ?> redisTemplate() {
        RedisTemplate<?, ?> redisTemplate = new RedisTemplate<>();

        // RedisTemplete에 LettuceConnectionFactory을 적용해주기 위해 설정해줍니다.
        redisTemplate.setConnectionFactory(redisConnectionFactory());
        return redisTemplate;
    }
}
