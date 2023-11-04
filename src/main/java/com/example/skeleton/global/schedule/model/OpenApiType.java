package com.example.skeleton.global.schedule.model;

public enum OpenApiType {
    JSON, XML;

    public String getType() {
        return this.name().toLowerCase();
    }

}
