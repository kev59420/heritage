package com.example.heritage.kevin.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "fournisseur", schema = "heritage")
@PrimaryKeyJoinColumn(name = "id")
@Getter
@Setter
@NoArgsConstructor
public class Fournisseur extends Personne {

    @Column(name = "societe", nullable = false, length = 100)
    private String societe;

    @Column(name = "contact", length = 100)
    private String contact;

    public Fournisseur(String nom, java.time.LocalDate dateNaissance, String societe, String contact) {
        super(nom, dateNaissance);
        this.societe = societe;
        this.contact = contact;
    }

    @Override
    public String toString() {
        return "Fournisseur{" +
                "id=" + getId() +
                ", nom='" + getNom() + '\'' +
                ", dateNaissance=" + getDateNaissance() +
                ", societe='" + societe + '\'' +
                ", contact='" + contact + '\'' +
                '}';
    }
}
