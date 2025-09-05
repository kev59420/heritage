package com.example.heritage.kevin.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;

@Entity
@Table(name = "client", schema = "heritage")
@PrimaryKeyJoinColumn(name = "id")
@Getter
@Setter
@NoArgsConstructor
public class Client extends Personne {

    @Column(name = "numero_client", nullable = false, length = 20)
    private String numeroClient;

    @Column(name = "date_inscription", nullable = false)
    private LocalDate dateInscription;

    public Client(String nom, LocalDate dateNaissance, String numeroClient, LocalDate dateInscription) {
        super(nom, dateNaissance);
        this.numeroClient = numeroClient;
        this.dateInscription = dateInscription;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + getId() +
                ", nom='" + getNom() + '\'' +
                ", dateNaissance=" + getDateNaissance() +
                ", numeroClient='" + numeroClient + '\'' +
                ", dateInscription=" + dateInscription +
                '}';
    }
}
