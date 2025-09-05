package com.example.heritage.ludo.service;

import com.example.heritage.ludo.model.ClientLudo;
import com.example.heritage.ludo.repository.ClientLudoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientLudoService {

    @Autowired
    private ClientLudoRepository clientLudoRepository;

    public List<ClientLudo> findAll() {
        return clientLudoRepository.findAll();
    }

    public Optional<ClientLudo> findById(Long id) {
        return clientLudoRepository.findById(id);
    }

    public Optional<ClientLudo> findByNumeroClient(String numeroClient) {
        return clientLudoRepository.findByNumeroClient(numeroClient);
    }

    public ClientLudo save(ClientLudo clientLudo) {
        return clientLudoRepository.save(clientLudo);
    }

    public void deleteById(Long id) {
        clientLudoRepository.deleteById(id);
    }

    public boolean existsById(Long id) {
        return clientLudoRepository.existsById(id);
    }
}
