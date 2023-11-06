package com.example.skeleton.global.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.util.Map;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Embeddable
public class Point {

    @Column(name = "latitude")
    private String latitude;

    @Column(name = "longitude")
    private String longitude;

    public static Point of(String latitude, String longitude) {
        return new Point(latitude, longitude);
    }

    public Map<String, String> toMap() {
        return Map.of("latitude", this.latitude, "longitude", this.longitude);
    }

}