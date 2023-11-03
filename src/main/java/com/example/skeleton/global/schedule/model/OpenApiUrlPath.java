package com.example.skeleton.global.schedule.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OpenApiUrlPath {
    CHINA("Genrestrtchifood"),
    JAPAN("Genrestrtjpnfood"),
    FASTFOOD("Genrestrtfastfood");

    private String path;
}