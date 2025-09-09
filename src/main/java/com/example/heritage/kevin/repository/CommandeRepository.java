package com.example.heritage.kevin.repository;

import com.example.heritage.kevin.model.Client;
import com.example.heritage.kevin.model.Commande;
import com.example.heritage.kevin.model.Produit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface CommandeRepository extends JpaRepository<Commande, Long> {

    List<Commande> findByClient(Client client);

    List<Commande> findByProduit(Produit produit);

    List<Commande> findByDateCommande(LocalDate dateCommande);

    List<Commande> findByDateCommandeBetween(LocalDate startDate, LocalDate endDate);

    List<Commande> findByClientId(Long clientId);
}
