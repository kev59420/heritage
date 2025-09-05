package com.example.heritage.ludo.service;

import com.example.heritage.ludo.model.EmployeLudo;
import com.example.heritage.ludo.repository.EmployeLudoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeLudoService {

    @Autowired
    private EmployeLudoRepository employeLudoRepository;

    public List<EmployeLudo> findAll() {
        return employeLudoRepository.findAll();
    }

    public Optional<EmployeLudo> findById(Long id) {
        return employeLudoRepository.findById(id);
    }

    public Optional<EmployeLudo> findByNumeroEmploye(String numeroEmploye) {
        return employeLudoRepository.findByNumeroEmploye(numeroEmploye);
    }

    public EmployeLudo save(EmployeLudo employeLudo) {
        return employeLudoRepository.save(employeLudo);
    }

    public void deleteById(Long id) {
        employeLudoRepository.deleteById(id);
    }

    public boolean existsById(Long id) {
        return employeLudoRepository.existsById(id);
    }
}
