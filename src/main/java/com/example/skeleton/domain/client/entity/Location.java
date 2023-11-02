package com.example.skeleton.domain.client.entity;

import jakarta.persistence.Embeddable;
import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Embeddable
public class Location {
    private String latitude;
    private String longitude;

    /**
     * 각각에 해당하는 값을 가진 Location 객체 생성
     * 
     * @param latitude  String - 위도
     * @param longitude String - 경도
     */
    public static Location of(String latitude, String longitude) {
        return new Location(latitude, longitude);
    }
}
