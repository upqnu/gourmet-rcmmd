package com.example.skeleton.domain.client.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "client_id", length = 20, nullable = false, unique = true)
    private String clientId;

    @Column(name = "password", nullable = false)
    private String password;

    @Embedded
    private Location location;

    @Enumerated(value = EnumType.STRING)
    private Permission permission;

    @Builder
    public Client(String clientId, String password) {
        this.clientId = clientId;
        this.password = password;
        this.location = new Location();
        this.permission = Permission.UNALLOWED;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public void allowRecommendation() {
        this.permission = Permission.ALLOWED;
    }
}