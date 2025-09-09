package com.example.heritage.kevin.service;

import com.example.heritage.kevin.model.Commande;
import com.example.heritage.kevin.model.Client;
import com.example.heritage.kevin.model.Produit;
import com.example.heritage.kevin.repository.CommandeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class CommandeService {

    @Autowired
    private CommandeRepository commandeRepository;

    public List<Commande> findAll() {
        return commandeRepository.findAll();
    }

    public Optional<Commande> findById(Long id) {
        return commandeRepository.findById(id);
    }

    public List<Commande> findByClient(Client client) {
        return commandeRepository.findByClient(client);
    }

    public List<Commande> findByClientId(Long clientId) {
        return commandeRepository.findByClientId(clientId);
    }

    public List<Commande> findByProduit(Produit produit) {
        return commandeRepository.findByProduit(produit);
    }

    public List<Commande> findByDateCommande(LocalDate dateCommande) {
        return commandeRepository.findByDateCommande(dateCommande);
    }

    public List<Commande> findByDateCommandeBetween(LocalDate startDate, LocalDate endDate) {
        return commandeRepository.findByDateCommandeBetween(startDate, endDate);
    }

    public Commande save(Commande commande) {
        return commandeRepository.save(commande);
    }

    public void deleteById(Long id) {
        commandeRepository.deleteById(id);
    }

    public boolean existsById(Long id) {
        return commandeRepository.existsById(id);
    }
}
