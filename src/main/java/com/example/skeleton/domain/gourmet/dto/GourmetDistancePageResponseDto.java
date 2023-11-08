package com.example.skeleton.domain.gourmet.dto;

import com.example.skeleton.global.model.PageInfo;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GourmetDistancePageResponseDto {
    public List<GourmetDistanceResponseDto> gourmetList;
    public PageInfo pageInfo;
}
