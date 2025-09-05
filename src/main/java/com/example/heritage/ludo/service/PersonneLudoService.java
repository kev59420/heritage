package com.example.heritage.ludo.service;

import com.example.heritage.ludo.model.PersonneLudo;
import com.example.heritage.ludo.repository.PersonneLudoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonneLudoService {

    @Autowired
    private PersonneLudoRepository personneLudoRepository;

    public List<PersonneLudo> findAll() {
        return personneLudoRepository.findAll();
    }

    public Optional<PersonneLudo> findById(Long id) {
        return personneLudoRepository.findById(id);
    }

    public Optional<PersonneLudo> findByType(String type) {
        return personneLudoRepository.findByType(type);
    }

    public PersonneLudo save(PersonneLudo personneLudo) {
        return personneLudoRepository.save(personneLudo);
    }

    public void deleteById(Long id) {
        personneLudoRepository.deleteById(id);
    }

    public boolean existsById(Long id) {
        return personneLudoRepository.existsById(id);
    }
}
