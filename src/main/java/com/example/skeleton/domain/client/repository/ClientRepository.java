package com.example.skeleton.domain.client.repository;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.skeleton.domain.client.entity.Client;
import com.example.skeleton.domain.client.entity.Permission;

public interface ClientRepository extends JpaRepository<Client, Long> {
    public boolean existsByClientId(String clientId);

    public Optional<Client> findByClientId(String clientId);

    public ArrayList<Client> findByPermission(Permission permission);
}
