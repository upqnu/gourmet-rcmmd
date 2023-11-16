package com.example.skeleton.domain.gourmet.dto;

import com.example.skeleton.domain.rating.dto.RatingInfo;
import com.example.skeleton.global.model.Address;
import com.example.skeleton.global.model.Point;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GourmetDetailResponseDto {

    private Long id;
    private String gourmetCode;
    private String name;
    private String category;
    private Point point;
    private Address address;
    private String isOpen;
    private Double rating;
    private List<RatingInfo> ratingList;

}
