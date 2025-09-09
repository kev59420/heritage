package com.example.heritage.ludo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;

@Entity
@Table(name = "fournisseur_ludo", schema = "heritage")
@Getter
@Setter
@NoArgsConstructor
public class FournisseurLudo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nom", nullable = false, length = 100)
    private String nom;

    @Column(name = "date_naissance", nullable = false)
    private LocalDate dateNaissance;

    @Column(name = "societe", nullable = false, length = 100)
    private String societe;

    @Column(name = "contact", length = 100)
    private String contact;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "personne_ludo_id", nullable = false)
    private PersonneLudo personneLudo;

    public FournisseurLudo(String nom, LocalDate dateNaissance, String societe, 
                           String contact, PersonneLudo personneLudo) {
        this.nom = nom;
        this.dateNaissance = dateNaissance;
        this.societe = societe;
        this.contact = contact;
        this.personneLudo = personneLudo;
    }

    @Override
    public String toString() {
        return "FournisseurLudo{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", dateNaissance=" + dateNaissance +
                ", societe='" + societe + '\'' +
                ", contact='" + contact + '\'' +
                ", personneLudo=" + (personneLudo != null ? personneLudo.getType() : null) +
                '}';
    }
}
