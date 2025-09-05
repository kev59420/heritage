package com.example.heritage.ludo.service;

import com.example.heritage.ludo.model.FournisseurLudo;
import com.example.heritage.ludo.repository.FournisseurLudoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FournisseurLudoService {

    @Autowired
    private FournisseurLudoRepository fournisseurLudoRepository;

    public List<FournisseurLudo> findAll() {
        return fournisseurLudoRepository.findAll();
    }

    public Optional<FournisseurLudo> findById(Long id) {
        return fournisseurLudoRepository.findById(id);
    }

    public List<FournisseurLudo> findBySociete(String societe) {
        return fournisseurLudoRepository.findBySocieteContainingIgnoreCase(societe);
    }

    public FournisseurLudo save(FournisseurLudo fournisseurLudo) {
        return fournisseurLudoRepository.save(fournisseurLudo);
    }

    public void deleteById(Long id) {
        fournisseurLudoRepository.deleteById(id);
    }

    public boolean existsById(Long id) {
        return fournisseurLudoRepository.existsById(id);
    }
}
