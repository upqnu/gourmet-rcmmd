package com.example.skeleton.domain.client.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.skeleton.domain.client.entity.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
