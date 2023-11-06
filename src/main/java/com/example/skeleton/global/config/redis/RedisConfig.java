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
       -- Redis 설정 참고 사이트 --
       Spring Boot 에서 Redis 사용하기 : https://bcp0109.tistory.com/328
       Redis의 데이터 타입, 명령어와 활용 사례 : https://dkswnkk.tistory.com/713

       Spring Boot와 Redis로 캐싱 구현하기 : https://yozm.wishket.com/magazine/detail/2296/
       Spring Boot Cache with Redis : https://www.baeldung.com/spring-boot-redis-cache
       GitHub | minsoozz : https://github.com/minsoozz/todo-java-springboot-example/blob/main/src/main/java/com/example/notice/config/CacheConfig.java

       Spring Boot에서 Redis를 사용하는 방법은 세가지 Spring Data Cache, RedisRepository와 RedisTemplate.
    */

    @Value("${spring.cache.redis.host}")
    private String host;

    @Value("${spring.cache.redis.port}")
    private int port;

    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        return new LettuceConnectionFactory(host, port);
    }

    @Bean
    public RedisTemplate<?, ?> redisTemplate() {
        RedisTemplate<?, ?> redisTemplate = new RedisTemplate<>();

        // RedisTemplete에 LettuceConnectionFactory를 적용해주기 위해 설정해줍니다.
        redisTemplate.setConnectionFactory(redisConnectionFactory());

        return redisTemplate;
    }
}
