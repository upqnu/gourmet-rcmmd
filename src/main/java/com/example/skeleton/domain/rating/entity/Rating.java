package com.example.skeleton.domain.rating.entity;

import com.example.skeleton.domain.client.entity.Client;
import com.example.skeleton.domain.gourmet.entity.Gourmet;

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

    @ManyToOne(optional = false, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "gourmet_id", nullable = false)
    private Gourmet gourmet;

    private Integer score;

    private String content;

    @Builder
    public Rating(Client client, Gourmet gourmet, Integer score, String content) {
        this.client = client;
        this.gourmet = gourmet;
        this.score = score;
        this.content = content;
    }
}
