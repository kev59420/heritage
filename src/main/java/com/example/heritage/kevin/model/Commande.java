package com.example.heritage.kevin.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "commande", schema = "heritage")
@Getter
@Setter
@NoArgsConstructor
public class Commande {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date_commande", nullable = false)
    private LocalDate dateCommande;

    @Column(name = "quantite", nullable = false)
    private Integer quantite;

    @Column(name = "prix_unitaire", nullable = false, precision = 10, scale = 2)
    private BigDecimal prixUnitaire;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "produit_id", nullable = false)
    private Produit produit;

    public Commande(LocalDate dateCommande, Integer quantite, BigDecimal prixUnitaire, 
                     Client client, Produit produit) {
        this.dateCommande = dateCommande;
        this.quantite = quantite;
        this.prixUnitaire = prixUnitaire;
        this.client = client;
        this.produit = produit;
    }

    @Override
    public String toString() {
        return "Commande{" +
                "id=" + id +
                ", dateCommande=" + dateCommande +
                ", quantite=" + quantite +
                ", prixUnitaire=" + prixUnitaire +
                ", client=" + (client != null ? client.getNom() : null) +
                ", produit=" + (produit != null ? produit.getNom() : null) +
                '}';
    }
}
