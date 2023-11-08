package com.example.skeleton.domain.district.entity;

import com.example.skeleton.global.model.Point;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;

@Getter
@Entity
public class District {

    @Id
    private Long id;

    // 행정구역 : 도
    @Column(name = "dosi")
    private String dosi;

    // 행정구역 : 시, 군, 구
    @Column(name = "si_gun_gu")
    private String sgg;

    @Embedded
    private Point point;

    protected District() {}

    private District(Long id, String dosi, String sgg, Point point) {
        this.id = id;
        this.dosi = dosi;
        this.sgg = sgg;
        this.point = point;
    }

    public static District of(Long id, String dosi, String sgg, Point point) {
        return new District(id, dosi, sgg, point);
    }
}
