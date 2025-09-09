package com.example.heritage.ludo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "commande_ludo", schema = "heritage")
@Getter
@Setter
@NoArgsConstructor
public class CommandeLudo {

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
    @JoinColumn(name = "client_ludo_id", nullable = false)
    private ClientLudo clientLudo;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "produit_id", nullable = false)
    private ProduitLudo produit;

    public CommandeLudo(LocalDate dateCommande, Integer quantite, BigDecimal prixUnitaire, 
                         ClientLudo clientLudo, ProduitLudo produit) {
        this.dateCommande = dateCommande;
        this.quantite = quantite;
        this.prixUnitaire = prixUnitaire;
        this.clientLudo = clientLudo;
        this.produit = produit;
    }

    @Override
    public String toString() {
        return "CommandesLudo{" +
                "id=" + id +
                ", dateCommande=" + dateCommande +
                ", quantite=" + quantite +
                ", prixUnitaire=" + prixUnitaire +
                ", clientLudo=" + (clientLudo != null ? clientLudo.getNom() : null) +
                ", produit=" + (produit != null ? produit.getNom() : null) +
                '}';
    }
}
