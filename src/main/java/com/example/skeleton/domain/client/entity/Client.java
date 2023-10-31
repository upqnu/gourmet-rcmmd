package com.example.skeleton.domain.client.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "client_id", columnDefinition = "varchar(255) comment '사용자 아이디'", unique = true)
    private String clientId;

    @Column(name = "password", length = 500, nullable = false)
    private String password;

    // 기본값 null
    @Column(name = "latitude", length = 20)
    private String latitude;

    // 기본값 null
    @Column(name = "longitude", length = 20)
    private String longitude;

    @Enumerated(value = EnumType.STRING)
    private Permission isAllowed;

}
