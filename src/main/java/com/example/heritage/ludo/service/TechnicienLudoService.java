package com.example.heritage.ludo.service;

import com.example.heritage.ludo.model.TechnicienLudo;
import com.example.heritage.ludo.repository.TechnicienLudoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TechnicienLudoService {

    @Autowired
    private TechnicienLudoRepository technicienLudoRepository;

    public List<TechnicienLudo> findAll() {
        return technicienLudoRepository.findAll();
    }

    public Optional<TechnicienLudo> findById(Long id) {
        return technicienLudoRepository.findById(id);
    }

    public List<TechnicienLudo> findBySpecialite(String specialite) {
        return technicienLudoRepository.findBySpecialite(specialite);
    }

    public List<TechnicienLudo> findByNiveau(Integer niveau) {
        return technicienLudoRepository.findByNiveau(niveau);
    }

    public TechnicienLudo save(TechnicienLudo technicienLudo) {
        return technicienLudoRepository.save(technicienLudo);
    }

    public void deleteById(Long id) {
        technicienLudoRepository.deleteById(id);
    }

    public boolean existsById(Long id) {
        return technicienLudoRepository.existsById(id);
    }
}
