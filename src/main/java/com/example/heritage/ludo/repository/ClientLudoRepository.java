package com.example.heritage.ludo.repository;

import com.example.heritage.ludo.model.ClientLudo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientLudoRepository extends JpaRepository<ClientLudo, Long> {
    
    Optional<ClientLudo> findByNumeroClient(String numeroClient);
}
