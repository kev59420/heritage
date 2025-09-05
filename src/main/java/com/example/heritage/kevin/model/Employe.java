package com.example.heritage.kevin.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;

@Entity
@Table(name = "employe", schema = "heritage")
@PrimaryKeyJoinColumn(name = "id")
@Getter
@Setter
@NoArgsConstructor
public class Employe extends Personne {

    @Column(name = "numero_employe", nullable = false, length = 20)
    private String numeroEmploye;

    @Column(name = "date_embauche", nullable = false)
    private LocalDate dateEmbauche;

    public Employe(String nom, LocalDate dateNaissance, String numeroEmploye, LocalDate dateEmbauche) {
        super(nom, dateNaissance);
        this.numeroEmploye = numeroEmploye;
        this.dateEmbauche = dateEmbauche;
    }

    @Override
    public String toString() {
        return "Employe{" +
                "id=" + getId() +
                ", nom='" + getNom() + '\'' +
                ", dateNaissance=" + getDateNaissance() +
                ", numeroEmploye='" + numeroEmploye + '\'' +
                ", dateEmbauche=" + dateEmbauche +
                '}';
    }
}
