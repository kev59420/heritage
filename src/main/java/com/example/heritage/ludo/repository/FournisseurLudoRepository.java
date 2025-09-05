package com.example.heritage.ludo.repository;

import com.example.heritage.ludo.model.FournisseurLudo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FournisseurLudoRepository extends JpaRepository<FournisseurLudo, Long> {
    
    List<FournisseurLudo> findBySocieteContainingIgnoreCase(String societe);
}
