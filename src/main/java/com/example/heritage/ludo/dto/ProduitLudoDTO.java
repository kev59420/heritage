package com.example.heritage.ludo.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class ProduitLudoDTO {
    
    private String nom;
    private String description;
    private BigDecimal prix;
    private Integer stock;
    private String categorie;
    private Long personneLudoId;

    public ProduitLudoDTO(String nom, String description, BigDecimal prix, Integer stock, 
                          String categorie, Long personneLudoId) {
        this.nom = nom;
        this.description = description;
        this.prix = prix;
        this.stock = stock;
        this.categorie = categorie;
        this.personneLudoId = personneLudoId;
    }
}
