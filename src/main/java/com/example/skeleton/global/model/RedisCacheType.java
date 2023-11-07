package com.example.skeleton.global.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.Duration;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum RedisCacheType {

    DISTRICT("district", Duration.ofDays(365L)),
    GOURMET("gourmet", Duration.ofHours(3L)),
    CLIENT("client", Duration.ofMinutes(10L));

    private final String keyName;
    private final Duration duration;
}
