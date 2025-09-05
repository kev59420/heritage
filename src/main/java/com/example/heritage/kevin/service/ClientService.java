package com.example.heritage.kevin.service;

import com.example.heritage.kevin.model.Client;
import com.example.heritage.kevin.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    public Optional<Client> findById(Long id) {
        return clientRepository.findById(id);
    }

    public Optional<Client> findByNumeroClient(String numeroClient) {
        return clientRepository.findByNumeroClient(numeroClient);
    }

    public Client save(Client client) {
        return clientRepository.save(client);
    }

    public void deleteById(Long id) {
        clientRepository.deleteById(id);
    }

    public boolean existsById(Long id) {
        return clientRepository.existsById(id);
    }
}
