package com.example.skeleton.global.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.Duration;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum RedisCacheType {

    DEFAULT("default", Duration.ofDays(365)),

    GOURMET("gourmet", Duration.ofHours(3)),
    CLIENT("client", Duration.ofMinutes(10));

    private final String keyName;
    private final Duration duration;
}
