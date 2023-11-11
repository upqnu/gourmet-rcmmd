package com.example.skeleton.domain.rating.dto;

import com.example.skeleton.domain.client.dto.ClientInfo;
import com.example.skeleton.domain.client.dto.ClientSimpleInfo;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RatingInfo {
    private Long id;
    private Integer score;
    private String content;
    private ClientSimpleInfo client;
}
