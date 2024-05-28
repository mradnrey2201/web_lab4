package com.example.sprang_back.Repository;

import com.example.sprang_back.Model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
    Client findByUsername(String username);
}
