package com.example.heritage.ludo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "manager_ludo", schema = "heritage")
@Getter
@Setter
@NoArgsConstructor
public class ManagerLudo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nom", nullable = false, length = 100)
    private String nom;

    @Column(name = "date_naissance", nullable = false)
    private LocalDate dateNaissance;

    @Column(name = "numero_employe", nullable = false, length = 20)
    private String numeroEmploye;

    @Column(name = "date_embauche", nullable = false)
    private LocalDate dateEmbauche;

    @Column(name = "departement", nullable = false, length = 50)
    private String departement;

    @Column(name = "budget", precision = 12, scale = 2)
    private BigDecimal budget;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "personne_ludo_id", nullable = false)
    private PersonneLudo personneLudo;

    public ManagerLudo(String nom, LocalDate dateNaissance, String numeroEmploye, 
                       LocalDate dateEmbauche, String departement, BigDecimal budget, 
                       PersonneLudo personneLudo) {
        this.nom = nom;
        this.dateNaissance = dateNaissance;
        this.numeroEmploye = numeroEmploye;
        this.dateEmbauche = dateEmbauche;
        this.departement = departement;
        this.budget = budget;
        this.personneLudo = personneLudo;
    }

    @Override
    public String toString() {
        return "ManagerLudo{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", dateNaissance=" + dateNaissance +
                ", numeroEmploye='" + numeroEmploye + '\'' +
                ", dateEmbauche=" + dateEmbauche +
                ", departement='" + departement + '\'' +
                ", budget=" + budget +
                ", personneLudo=" + (personneLudo != null ? personneLudo.getType() : null) +
                '}';
    }
}
