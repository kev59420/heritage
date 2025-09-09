package com.example.heritage.ludo.repository;

import com.example.heritage.ludo.model.CommandeLudo;
import com.example.heritage.ludo.model.ClientLudo;
import com.example.heritage.ludo.model.ProduitLudo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface CommandesLudoRepository extends JpaRepository<CommandeLudo, Long> {
    
    List<CommandeLudo> findByClientLudo(ClientLudo clientLudo);
    
    List<CommandeLudo> findByProduit(ProduitLudo produit);
    
    List<CommandeLudo> findByDateCommande(LocalDate dateCommande);
    
    List<CommandeLudo> findByDateCommandeBetween(LocalDate startDate, LocalDate endDate);
    
    List<CommandeLudo> findByClientLudoId(Long clientLudoId);
}
