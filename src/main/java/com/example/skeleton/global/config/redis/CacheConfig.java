package com.example.skeleton.global.config.redis;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.util.Map;

import static com.example.skeleton.global.model.RedisCacheType.*;
import static org.springframework.data.redis.serializer.RedisSerializationContext.SerializationPair;

@Configuration
public class CacheConfig {

    private final RedisConnectionFactory redisConnectionFactory;

    public CacheConfig(RedisConnectionFactory cf) {
        this.redisConnectionFactory = cf;
    }

    @Bean
    public RedisCacheManager redisCacheManager() {

        return RedisCacheManager.RedisCacheManagerBuilder
                .fromConnectionFactory(redisConnectionFactory)
                // 기본 RedisCache 설정입니다.
                .cacheDefaults(defaultConfiguration())
                // CacheManager가 초기화 되기 전에 "name/RedisCacheConfiguration" 쌍으로 된 맵을 추가합니다.
                .withInitialCacheConfigurations(customizedConfigurationMap())
                .build();
    }

    /**
     * RedisCache의 기본 설정 메소드 입니다.
     * */
    private RedisCacheConfiguration defaultConfiguration() {

        return RedisCacheConfiguration.defaultCacheConfig()
                .serializeKeysWith(SerializationPair.fromSerializer(new StringRedisSerializer()))
                .serializeValuesWith(SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer()))
                .entryTtl(DEFAULT.getDuration());
    }

    /**
     * 추가적인 RedisCache 설정을 위한 메소드입니다.
     * */
    private Map<String, RedisCacheConfiguration> customizedConfigurationMap() {

        return Map.of(
                // ENUM을 통해 추가적인 설정값을 관리합니다.
                CLIENT.getKeyName(), defaultConfiguration().entryTtl(CLIENT.getDuration()),
                GOURMET.getKeyName(), defaultConfiguration().entryTtl(GOURMET.getDuration())
        );
    }
}
