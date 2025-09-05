package com.example.heritage.kevin.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "technicien", schema = "heritage")
@PrimaryKeyJoinColumn(name = "id")
@Getter
@Setter
@NoArgsConstructor
public class Technicien extends Employe {

    @Column(name = "specialite", nullable = false, length = 50)
    private String specialite;

    @Column(name = "niveau")
    private Integer niveau;

    public Technicien(String nom, java.time.LocalDate dateNaissance, String numeroEmploye, 
                      java.time.LocalDate dateEmbauche, String specialite, Integer niveau) {
        super(nom, dateNaissance, numeroEmploye, dateEmbauche);
        this.specialite = specialite;
        this.niveau = niveau;
    }

    @Override
    public String toString() {
        return "Technicien{" +
                "id=" + getId() +
                ", nom='" + getNom() + '\'' +
                ", dateNaissance=" + getDateNaissance() +
                ", numeroEmploye='" + getNumeroEmploye() + '\'' +
                ", dateEmbauche=" + getDateEmbauche() +
                ", specialite='" + specialite + '\'' +
                ", niveau=" + niveau +
                '}';
    }
}
