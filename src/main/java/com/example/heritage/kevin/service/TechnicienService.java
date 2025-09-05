package com.example.heritage.kevin.service;

import com.example.heritage.kevin.model.Technicien;
import com.example.heritage.kevin.repository.TechnicienRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TechnicienService {

    @Autowired
    private TechnicienRepository technicienRepository;

    public List<Technicien> findAll() {
        return technicienRepository.findAll();
    }

    public Optional<Technicien> findById(Long id) {
        return technicienRepository.findById(id);
    }

    public List<Technicien> findBySpecialite(String specialite) {
        return technicienRepository.findBySpecialite(specialite);
    }

    public List<Technicien> findByNiveau(Integer niveau) {
        return technicienRepository.findByNiveau(niveau);
    }

    public Technicien save(Technicien technicien) {
        return technicienRepository.save(technicien);
    }

    public void deleteById(Long id) {
        technicienRepository.deleteById(id);
    }

    public boolean existsById(Long id) {
        return technicienRepository.existsById(id);
    }
}
