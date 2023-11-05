package com.example.skeleton.domain.client.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.skeleton.domain.client.entity.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {
    public boolean existsByClientId(String clientId);

    public Optional<Client> findByClientId(String clientId);
}
