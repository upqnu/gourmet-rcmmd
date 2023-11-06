package com.example.skeleton.domain.gourmet.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GourmetDistanceResponseDto {
    private Double distance;
    private GourmetResponseDto gourmet;
}
