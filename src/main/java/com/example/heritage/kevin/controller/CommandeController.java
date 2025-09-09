package com.example.heritage.kevin.controller;

import com.example.heritage.kevin.dto.CommandeDTO;
import com.example.heritage.kevin.model.Commande;
import com.example.heritage.kevin.model.Client;
import com.example.heritage.kevin.model.Produit;
import com.example.heritage.kevin.service.CommandeService;
import com.example.heritage.kevin.service.ClientService;
import com.example.heritage.kevin.service.ProduitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/kevin/commande")
public class CommandeController {

    @Autowired
    private CommandeService commandeService;

    @Autowired
    private ClientService clientService;

    @Autowired
    private ProduitService produitService;

    @GetMapping
    public ResponseEntity<List<Commande>> getAllCommandes() {
        List<Commande> commandes = commandeService.findAll();
        return ResponseEntity.ok(commandes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Commande> getCommandesById(@PathVariable Long id) {
        Optional<Commande> commandes = commandeService.findById(id);
        return commandes.map(ResponseEntity::ok)
                        .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/client/{clientId}")
    public ResponseEntity<List<Commande>> getCommandesByClient(@PathVariable Long clientId) {
        List<Commande> commandes = commandeService.findByClientId(clientId);
        return ResponseEntity.ok(commandes);
    }

    @GetMapping("/date/{date}")
    public ResponseEntity<List<Commande>> getCommandesByDate(@PathVariable String date) {
        LocalDate dateCommande = LocalDate.parse(date);
        List<Commande> commandes = commandeService.findByDateCommande(dateCommande);
        return ResponseEntity.ok(commandes);
    }

    @GetMapping("/period")
    public ResponseEntity<List<Commande>> getCommandesByPeriod(
            @RequestParam String startDate, 
            @RequestParam String endDate) {
        LocalDate start = LocalDate.parse(startDate);
        LocalDate end = LocalDate.parse(endDate);
        List<Commande> commandes = commandeService.findByDateCommandeBetween(start, end);
        return ResponseEntity.ok(commandes);
    }

    @PostMapping
    public ResponseEntity<Commande> createCommande(@RequestBody CommandeDTO commandeDTO) {
        Optional<Client> client = clientService.findById(commandeDTO.getClientId());
        Optional<Produit> produit = produitService.findById(commandeDTO.getProduitId());
        
        if (client.isPresent() && produit.isPresent()) {
            Commande commande = new Commande();
            commande.setDateCommande(commandeDTO.getDateCommande() != null ? 
                                    commandeDTO.getDateCommande() : LocalDate.now());
            commande.setQuantite(commandeDTO.getQuantite());
            commande.setPrixUnitaire(commandeDTO.getPrixUnitaire());
            commande.setClient(client.get());
            commande.setProduit(produit.get());

            Commande savedCommande = commandeService.save(commande);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedCommande);
        }
        
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Commande> updateCommande(@PathVariable Long id, @RequestBody CommandeDTO commandeDTO) {
        Optional<Commande> existingCommande = commandeService.findById(id);

        if (existingCommande.isPresent()) {
            Optional<Client> client = clientService.findById(commandeDTO.getClientId());
            Optional<Produit> produit = produitService.findById(commandeDTO.getProduitId());

            if (client.isPresent() && produit.isPresent()) {
                Commande commande = existingCommande.get();
                commande.setDateCommande(commandeDTO.getDateCommande());
                commande.setQuantite(commandeDTO.getQuantite());
                commande.setPrixUnitaire(commandeDTO.getPrixUnitaire());
                commande.setClient(client.get());
                commande.setProduit(produit.get());

                Commande updatedCommande = commandeService.save(commande);
                return ResponseEntity.ok(updatedCommande);
            }
            
            return ResponseEntity.badRequest().build();
        }
        
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCommande(@PathVariable Long id) {
        if (commandeService.existsById(id)) {
            commandeService.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
