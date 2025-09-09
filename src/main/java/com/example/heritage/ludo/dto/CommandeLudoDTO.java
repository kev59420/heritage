package com.example.heritage.ludo.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class CommandeLudoDTO {
    
    private LocalDate dateCommande;
    private Integer quantite;
    private BigDecimal prixUnitaire;
    private Long clientLudoId;
    private Long produitId;

    public CommandeLudoDTO(LocalDate dateCommande, Integer quantite, BigDecimal prixUnitaire, 
                           Long clientLudoId, Long produitId) {
        this.dateCommande = dateCommande;
        this.quantite = quantite;
        this.prixUnitaire = prixUnitaire;
        this.clientLudoId = clientLudoId;
        this.produitId = produitId;
    }
}
