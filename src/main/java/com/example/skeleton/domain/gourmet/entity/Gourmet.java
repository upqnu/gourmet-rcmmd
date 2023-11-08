package com.example.skeleton.domain.gourmet.entity;

import com.example.skeleton.domain.gourmet.model.DistrictInfo;
import com.example.skeleton.domain.gourmet.model.Employee;
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
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @Embedded
    private Employee employee;

    @Embedded
    private DistrictInfo districtInfo;

    @Column(name = "is_open")
    private String isOpen;

    @Column(name = "rating")
    private Double rating;

    @Builder
    public Gourmet(String name,
            String category,
            Point point,
            Address address,
            Employee employee,
            DistrictInfo districtInfo,
            String isOpen) {
        this.gourmetCode = makeGourmetCode(name, address);
        this.name = name;
        this.category = category;
        this.point = point;
        this.address = address;
        this.employee = employee;
        this.districtInfo = districtInfo;
        this.isOpen = isOpen;
        this.rating = 0.0;
    }

    public void updateRating(double rating) {
        this.rating = rating;
    }

    private String makeGourmetCode(String name, Address address) {
        StringBuilder builder = new StringBuilder();

        builder.append(name)
                .append("-")
                .append(address.getLotNumber() == null ?
                        "주소없음" : address.getLotNumber())
                .append("-")
                .append(address.getZipCode() == null ?
                        "우편번호없음" : address.getZipCode());

        return builder.toString();
    }
}
