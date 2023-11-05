package com.example.skeleton.domain.gourmet.dto;

import com.example.skeleton.global.model.Address;
import com.example.skeleton.global.model.Point;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GourmetResponseDto {

    private Long id;
    private String gourmetCode;
    private String name;
    private String category;
    private Point point;
    private Address address;
    private String isOpen;
    private Double rating;

}
