package com.example.heritage.kevin.service;

import com.example.heritage.kevin.model.Employe;
import com.example.heritage.kevin.repository.EmployeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeService {

    @Autowired
    private EmployeRepository employeRepository;

    public List<Employe> findAll() {
        return employeRepository.findAll();
    }

    public Optional<Employe> findById(Long id) {
        return employeRepository.findById(id);
    }

    public Optional<Employe> findByNumeroEmploye(String numeroEmploye) {
        return employeRepository.findByNumeroEmploye(numeroEmploye);
    }

    public Employe save(Employe employe) {
        return employeRepository.save(employe);
    }

    public void deleteById(Long id) {
        employeRepository.deleteById(id);
    }

    public boolean existsById(Long id) {
        return employeRepository.existsById(id);
    }
}
