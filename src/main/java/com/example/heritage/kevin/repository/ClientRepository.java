package com.example.heritage.kevin.repository;

import com.example.heritage.kevin.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    
    Optional<Client> findByNumeroClient(String numeroClient);
}
