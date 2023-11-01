package com.example.skeleton.domain.rating.entity;

import com.example.skeleton.domain.client.entity.Client;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    // to do : Gourmet FK 추가

    private int point;

    private String content;

    @Builder
    public Rating(Client client, int point, String content) {
        this.client = client;
        this.point = point;
        this.content = content;
    }
}
