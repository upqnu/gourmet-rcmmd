package com.example.skeleton.global.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Embeddable
public class Address {
    @Column(name = "road_name_address", length = 200)
    private String roadName;
    @Column(name = "lot_number_address", length = 200)
    private String lotNumber;
    @Column(name = "zip_code")
    private String zipCode;

    public static Address of(String roadName, String lotNumber, String zipCode) {
        return new Address(roadName, lotNumber, zipCode);
    }
}
