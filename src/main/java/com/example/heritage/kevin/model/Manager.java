package com.example.heritage.kevin.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;

@Entity
@Table(name = "manager", schema = "heritage")
@PrimaryKeyJoinColumn(name = "id")
@Getter
@Setter
@NoArgsConstructor
public class Manager extends Employe {

    @Column(name = "departement", nullable = false, length = 50)
    private String departement;

    @Column(name = "budget", precision = 12, scale = 2)
    private BigDecimal budget;

    public Manager(String nom, java.time.LocalDate dateNaissance, String numeroEmploye, 
                   java.time.LocalDate dateEmbauche, String departement, BigDecimal budget) {
        super(nom, dateNaissance, numeroEmploye, dateEmbauche);
        this.departement = departement;
        this.budget = budget;
    }

    @Override
    public String toString() {
        return "Manager{" +
                "id=" + getId() +
                ", nom='" + getNom() + '\'' +
                ", dateNaissance=" + getDateNaissance() +
                ", numeroEmploye='" + getNumeroEmploye() + '\'' +
                ", dateEmbauche=" + getDateEmbauche() +
                ", departement='" + departement + '\'' +
                ", budget=" + budget +
                '}';
    }
}
