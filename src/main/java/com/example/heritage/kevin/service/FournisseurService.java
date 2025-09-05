package com.example.heritage.kevin.service;

import com.example.heritage.kevin.model.Fournisseur;
import com.example.heritage.kevin.repository.FournisseurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FournisseurService {

    @Autowired
    private FournisseurRepository fournisseurRepository;

    public List<Fournisseur> findAll() {
        return fournisseurRepository.findAll();
    }

    public Optional<Fournisseur> findById(Long id) {
        return fournisseurRepository.findById(id);
    }

    public List<Fournisseur> findBySociete(String societe) {
        return fournisseurRepository.findBySocieteContainingIgnoreCase(societe);
    }

    public Fournisseur save(Fournisseur fournisseur) {
        return fournisseurRepository.save(fournisseur);
    }

    public void deleteById(Long id) {
        fournisseurRepository.deleteById(id);
    }

    public boolean existsById(Long id) {
        return fournisseurRepository.existsById(id);
    }
}
