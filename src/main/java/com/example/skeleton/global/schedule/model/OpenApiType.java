package com.example.skeleton.global.schedule.model;

public enum OpenApiType {
    JSON, XML;

    public String toLowerCase() {
        return this.name().toLowerCase();
    }

}
