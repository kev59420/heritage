package com.example.heritage.kevin.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;

import com.example.heritage.kevin.enums.CategorieProduit;

@Getter
@Setter
@NoArgsConstructor
public class ProduitDTO {
    
    private String nom;
    private String description;
    private BigDecimal prix;
    private Integer stock;
    private CategorieProduit categorie;

    public ProduitDTO(String nom, String description, BigDecimal prix, Integer stock, CategorieProduit categorie) {
        this.nom = nom;
        this.description = description;
        this.prix = prix;
        this.stock = stock;
        this.categorie = categorie;
    }
}
