package com.example.heritage.ludo.service;

import com.example.heritage.ludo.model.ManagerLudo;
import com.example.heritage.ludo.repository.ManagerLudoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ManagerLudoService {

    @Autowired
    private ManagerLudoRepository managerLudoRepository;

    public List<ManagerLudo> findAll() {
        return managerLudoRepository.findAll();
    }

    public Optional<ManagerLudo> findById(Long id) {
        return managerLudoRepository.findById(id);
    }

    public List<ManagerLudo> findByDepartement(String departement) {
        return managerLudoRepository.findByDepartement(departement);
    }

    public ManagerLudo save(ManagerLudo managerLudo) {
        return managerLudoRepository.save(managerLudo);
    }

    public void deleteById(Long id) {
        managerLudoRepository.deleteById(id);
    }

    public boolean existsById(Long id) {
        return managerLudoRepository.existsById(id);
    }
}
