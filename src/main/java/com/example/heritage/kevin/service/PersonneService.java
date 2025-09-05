package com.example.heritage.kevin.service;

import com.example.heritage.kevin.model.Personne;
import com.example.heritage.kevin.repository.PersonneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonneService {

    @Autowired
    private PersonneRepository personneRepository;

    public List<Personne> findAll() {
        return personneRepository.findAll();
    }

    public Optional<Personne> findById(Long id) {
        return personneRepository.findById(id);
    }

    public Personne save(Personne personne) {
        return personneRepository.save(personne);
    }

    public void deleteById(Long id) {
        personneRepository.deleteById(id);
    }

    public boolean existsById(Long id) {
        return personneRepository.existsById(id);
    }
}
