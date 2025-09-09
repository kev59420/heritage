package com.example.heritage.ludo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;

@Entity
@Table(name = "produit_ludo", schema = "heritage")
@Getter
@Setter
@NoArgsConstructor
public class ProduitLudo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nom", nullable = false, length = 100)
    private String nom;

    @Column(name = "description")
    private String description;

    @Column(name = "prix", nullable = false, precision = 10, scale = 2)
    private BigDecimal prix;

    @Column(name = "stock", nullable = false)
    private Integer stock;

    @Column(name = "categorie", length = 50)
    private String categorie;

    public ProduitLudo(String nom, String description, BigDecimal prix, Integer stock, 
                        String categorie) {
        this.nom = nom;
        this.description = description;
        this.prix = prix;
        this.stock = stock;
        this.categorie = categorie;
    }

    @Override
    public String toString() {
        return "ProduitLudo{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", description='" + description + '\'' +
                ", prix=" + prix +
                ", stock=" + stock +
                ", categorie='" + categorie + '\'' +
                '}';
    }
}
