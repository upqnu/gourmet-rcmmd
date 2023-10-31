package com.example.skeleton.domain.client.entity;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Embeddable
public class Location {
    private String latitude;
    private String longitude;
}
