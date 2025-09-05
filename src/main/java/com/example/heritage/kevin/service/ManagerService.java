package com.example.heritage.kevin.service;

import com.example.heritage.kevin.model.Manager;
import com.example.heritage.kevin.repository.ManagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ManagerService {

    @Autowired
    private ManagerRepository managerRepository;

    public List<Manager> findAll() {
        return managerRepository.findAll();
    }

    public Optional<Manager> findById(Long id) {
        return managerRepository.findById(id);
    }

    public List<Manager> findByDepartement(String departement) {
        return managerRepository.findByDepartement(departement);
    }

    public Manager save(Manager manager) {
        return managerRepository.save(manager);
    }

    public void deleteById(Long id) {
        managerRepository.deleteById(id);
    }

    public boolean existsById(Long id) {
        return managerRepository.existsById(id);
    }
}
