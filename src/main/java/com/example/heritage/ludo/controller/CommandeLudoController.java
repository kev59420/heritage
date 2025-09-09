package com.example.heritage.ludo.controller;

import com.example.heritage.ludo.dto.CommandeLudoDTO;
import com.example.heritage.ludo.model.CommandeLudo;
import com.example.heritage.ludo.model.ClientLudo;
import com.example.heritage.ludo.model.ProduitLudo;
import com.example.heritage.ludo.service.CommandeLudoService;
import com.example.heritage.ludo.service.ClientLudoService;
import com.example.heritage.ludo.service.ProduitLudoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/ludo/commande")
public class CommandeLudoController {

    @Autowired
    private CommandeLudoService commandeLudoService;

    @Autowired
    private ClientLudoService clientLudoService;

    @Autowired
    private ProduitLudoService produitLudoService;

    @GetMapping
    public ResponseEntity<List<CommandeLudo>> getAllCommandesLudo() {
        List<CommandeLudo> commandes = commandeLudoService.findAll();
        return ResponseEntity.ok(commandes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommandeLudo> getCommandesLudoById(@PathVariable Long id) {
        Optional<CommandeLudo> commandes = commandeLudoService.findById(id);
        return commandes.map(ResponseEntity::ok)
                        .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/client/{clientLudoId}")
    public ResponseEntity<List<CommandeLudo>> getCommandesLudoByClient(@PathVariable Long clientLudoId) {
        List<CommandeLudo> commandes = commandeLudoService.findByClientLudoId(clientLudoId);
        return ResponseEntity.ok(commandes);
    }

    @GetMapping("/date/{date}")
    public ResponseEntity<List<CommandeLudo>> getCommandesLudoByDate(@PathVariable String date) {
        LocalDate dateCommande = LocalDate.parse(date);
        List<CommandeLudo> commandes = commandeLudoService.findByDateCommande(dateCommande);
        return ResponseEntity.ok(commandes);
    }

    @GetMapping("/period")
    public ResponseEntity<List<CommandeLudo>> getCommandesLudoByPeriod(
            @RequestParam String startDate, 
            @RequestParam String endDate) {
        LocalDate start = LocalDate.parse(startDate);
        LocalDate end = LocalDate.parse(endDate);
        List<CommandeLudo> commandes = commandeLudoService.findByDateCommandeBetween(start, end);
        return ResponseEntity.ok(commandes);
    }

    @PostMapping
    public ResponseEntity<CommandeLudo> createCommandesLudo(@RequestBody CommandeLudoDTO commandeLudoDTO) {
        Optional<ClientLudo> client = clientLudoService.findById(commandeLudoDTO.getClientLudoId());
        Optional<ProduitLudo> produits = produitLudoService.findById(commandeLudoDTO.getProduitId());
        
        if (client.isPresent() && produits.isPresent()) {
            CommandeLudo commande = new CommandeLudo();
            commande.setDateCommande(commandeLudoDTO.getDateCommande() != null ? 
                                     commandeLudoDTO.getDateCommande() : LocalDate.now());
            commande.setQuantite(commandeLudoDTO.getQuantite());
            commande.setPrixUnitaire(commandeLudoDTO.getPrixUnitaire());
            commande.setClientLudo(client.get());
            commande.setProduit(produits.get());
            
            CommandeLudo savedCommande = commandeLudoService.save(commande);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedCommande);
        }
        
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<CommandeLudo> updateCommandesLudo(@PathVariable Long id, @RequestBody CommandeLudoDTO commandeLudoDTO) {
        Optional<CommandeLudo> existingCommandes = commandeLudoService.findById(id);
        
        if (existingCommandes.isPresent()) {
            Optional<ClientLudo> client = clientLudoService.findById(commandeLudoDTO.getClientLudoId());
            Optional<ProduitLudo> produits = produitLudoService.findById(commandeLudoDTO.getProduitId());
            
            if (client.isPresent() && produits.isPresent()) {
                CommandeLudo commande = existingCommandes.get();
                commande.setDateCommande(commandeLudoDTO.getDateCommande());
                commande.setQuantite(commandeLudoDTO.getQuantite());
                commande.setPrixUnitaire(commandeLudoDTO.getPrixUnitaire());
                commande.setClientLudo(client.get());
                commande.setProduit(produits.get());
                
                CommandeLudo updatedCommande = commandeLudoService.save(commande);
                return ResponseEntity.ok(updatedCommande);
            }
            
            return ResponseEntity.badRequest().build();
        }
        
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCommandesLudo(@PathVariable Long id) {
        if (commandeLudoService.existsById(id)) {
            commandeLudoService.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
