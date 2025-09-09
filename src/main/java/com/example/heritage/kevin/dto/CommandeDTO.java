package com.example.heritage.kevin.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class CommandeDTO {
    
    private LocalDate dateCommande;
    private Integer quantite;
    private BigDecimal prixUnitaire;
    private Long clientId;
    private Long produitId;

    public CommandeDTO(LocalDate dateCommande, Integer quantite, BigDecimal prixUnitaire, 
                        Long clientId, Long produitId) {
        this.dateCommande = dateCommande;
        this.quantite = quantite;
        this.prixUnitaire = prixUnitaire;
        this.clientId = clientId;
        this.produitId = produitId;
    }
}
