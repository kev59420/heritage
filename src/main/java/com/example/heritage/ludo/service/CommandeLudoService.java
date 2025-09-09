package com.example.heritage.ludo.service;

import com.example.heritage.ludo.model.CommandeLudo;
import com.example.heritage.ludo.model.ClientLudo;
import com.example.heritage.ludo.model.ProduitLudo;
import com.example.heritage.ludo.repository.CommandesLudoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class CommandeLudoService {

    @Autowired
    private CommandesLudoRepository commandeLudoRepository;

    public List<CommandeLudo> findAll() {
        return commandeLudoRepository.findAll();
    }

    public Optional<CommandeLudo> findById(Long id) {
        return commandeLudoRepository.findById(id);
    }

    public List<CommandeLudo> findByClientLudo(ClientLudo clientLudo) {
        return commandeLudoRepository.findByClientLudo(clientLudo);
    }

    public List<CommandeLudo> findByClientLudoId(Long clientLudoId) {
        return commandeLudoRepository.findByClientLudoId(clientLudoId);
    }

    public List<CommandeLudo> findByProduit(ProduitLudo produit) {
        return commandeLudoRepository.findByProduit(produit);
    }

    public List<CommandeLudo> findByDateCommande(LocalDate dateCommande) {
        return commandeLudoRepository.findByDateCommande(dateCommande);
    }

    public List<CommandeLudo> findByDateCommandeBetween(LocalDate startDate, LocalDate endDate) {
        return commandeLudoRepository.findByDateCommandeBetween(startDate, endDate);
    }

    public CommandeLudo save(CommandeLudo commandeLudo) {
        return commandeLudoRepository.save(commandeLudo);
    }

    public void deleteById(Long id) {
        commandeLudoRepository.deleteById(id);
    }

    public boolean existsById(Long id) {
        return commandeLudoRepository.existsById(id);
    }
}
