package com.example.skeleton.domain.gourmet.entity;

import com.example.skeleton.global.model.Address;
import com.example.skeleton.global.model.Point;
import jakarta.persistence.*;
import lombok.*;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "Gourmet")
@Entity
public class Gourmet {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "gourmet_code", nullable = false, length = 250)
    private String gourmetCode;

    @Column(name = "name")
    private String name;

    @Column(name = "category")
    private String category;

    @Embedded
    private Point point;

    @Embedded
    private Address address;

    @Column(name = "is_open")
    private String isOpen;

    @Column(name = "rating")
    private Double rating;

    //todo 시군구 엔티티와 연관관계 설정돼야 함

    @Builder
    public Gourmet(String name,
                   String category,
                   Point point,
                   Address address,
                   String isOpen) {
        this.gourmetCode = makeGourmetCode(name, address);
        this.name = name;
        this.category = category;
        this.point = point;
        this.address = address;
        this.isOpen = isOpen;
        this.rating = 0.0;
    }

    private String makeGourmetCode(String name, Address address) {
        StringBuilder builder = new StringBuilder();
        builder.append(name)
                .append("-")
                .append(address.getLotNumber())
                .append("-")
                .append(address.getZipCode());
        return builder.toString();
    }
}
