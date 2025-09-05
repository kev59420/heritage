#!/bin/bash
set -e

psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" --dbname "$POSTGRES_DB" <<-EOSQL
    -- Création du schéma heritage
    CREATE SCHEMA IF NOT EXISTS heritage;
    
    -- Table de base : Personne
    CREATE TABLE heritage.Personne (
        id SERIAL PRIMARY KEY,
        nom VARCHAR(100) NOT NULL,
        date_naissance DATE NOT NULL
    );
    
    -- Tables héritant de Personne
    CREATE TABLE heritage.Client (
        id INT PRIMARY KEY,
        numero_client VARCHAR(20) NOT NULL,
        date_inscription DATE NOT NULL,
        FOREIGN KEY (id) REFERENCES heritage.Personne(id)
    );
    
    CREATE TABLE heritage.Fournisseur (
        id INT PRIMARY KEY,
        societe VARCHAR(100) NOT NULL,
        contact VARCHAR(100),
        FOREIGN KEY (id) REFERENCES heritage.Personne(id)
    );
    
    CREATE TABLE heritage.Employe (
        id INT PRIMARY KEY,
        numero_employe VARCHAR(20) NOT NULL,
        date_embauche DATE NOT NULL,
        FOREIGN KEY (id) REFERENCES heritage.Personne(id)
    );
    
    -- Tables héritant d'Employe
    CREATE TABLE heritage.Manager (
        id INT PRIMARY KEY,
        departement VARCHAR(50) NOT NULL,
        budget DECIMAL(12,2),
        FOREIGN KEY (id) REFERENCES heritage.Employe(id)
    );
    
    CREATE TABLE heritage.Technicien (
        id INT PRIMARY KEY,
        specialite VARCHAR(50) NOT NULL,
        niveau INT,
        FOREIGN KEY (id) REFERENCES heritage.Employe(id)
    );
    
    -- Insertion de données de test
    -- Personnes
    INSERT INTO heritage.Personne (nom, date_naissance) VALUES 
        ('Alice Dupont', '1980-05-12'),
        ('Jean Martin', '1975-03-22'),
        ('Sophie Bernard', '1990-11-05'),
        ('Pierre Durand', '1985-08-15'),
        ('Marie Leroy', '1992-02-28'),
        ('Antoine Moreau', '1978-12-10');
    
    -- Clients
    INSERT INTO heritage.Client (id, numero_client, date_inscription) VALUES 
        (4, 'CLI001', '2020-03-15'),
        (5, 'CLI002', '2021-07-22');
    
    -- Fournisseurs
    INSERT INTO heritage.Fournisseur (id, societe, contact) VALUES 
        (6, 'TechCorp SARL', 'contact@techcorp.com');
    
    -- Employés
    INSERT INTO heritage.Employe (id, numero_employe, date_embauche) VALUES 
        (1, 'EMP001', '2010-09-01'),
        (2, 'EMP002', '2015-06-15'),
        (3, 'EMP003', '2018-01-20');
    
    -- Managers
    INSERT INTO heritage.Manager (id, departement, budget) VALUES 
        (1, 'Informatique', 150000.00);
    
    -- Techniciens
    INSERT INTO heritage.Technicien (id, specialite, niveau) VALUES 
        (2, 'Développement Web', 3),
        (3, 'Réseaux', 2);
    
    -- METHODE DE LUDO

    CREATE TABLE heritage.PersonneLudo (
        id SERIAL PRIMARY KEY,
        type VARCHAR(100) NOT NULL
    );

    CREATE TABLE heritage.ClientLudo (
        id SERIAL PRIMARY KEY,
        nom VARCHAR(100) NOT NULL,
        date_naissance DATE NOT NULL,
        numero_client VARCHAR(20) NOT NULL,
        date_inscription DATE NOT NULL,
        personneLudoId INT NOT NULL,
        FOREIGN KEY(personneLudoId) REFERENCES heritage.PersonneLudo(id) 
    );

    CREATE TABLE heritage.FournisseurLudo (
        id SERIAL PRIMARY KEY,
        nom VARCHAR(100) NOT NULL,
        date_naissance DATE NOT NULL,
        societe VARCHAR(100) NOT NULL,
        contact VARCHAR(100),
        personneLudoId INT NOT NULL,
        FOREIGN KEY(personneLudoId) REFERENCES heritage.PersonneLudo(id) 
    );

    CREATE TABLE heritage.EmployeLudo (
        id SERIAL PRIMARY KEY,
        nom VARCHAR(100) NOT NULL,
        date_naissance DATE NOT NULL,
        numero_employe VARCHAR(20) NOT NULL,
        date_embauche DATE NOT NULL,
        personneLudoId INT NOT NULL,
        FOREIGN KEY(personneLudoId) REFERENCES heritage.PersonneLudo(id) 
    );

    CREATE TABLE heritage.ManagerLudo (
        id SERIAL PRIMARY KEY,
        nom VARCHAR(100) NOT NULL,
        date_naissance DATE NOT NULL,
        numero_employe VARCHAR(20) NOT NULL,
        date_embauche DATE NOT NULL,
        departement VARCHAR(50) NOT NULL,
        budget DECIMAL(12,2),
        personneLudoId INT NOT NULL,
        FOREIGN KEY (personneLudoId) REFERENCES heritage.PersonneLudo(id)
    );

    CREATE TABLE heritage.TechnicienLudo (
        id SERIAL PRIMARY KEY,
        nom VARCHAR(100) NOT NULL,
        date_naissance DATE NOT NULL,
        numero_employe VARCHAR(20) NOT NULL,
        date_embauche DATE NOT NULL,
        specialite VARCHAR(50) NOT NULL,
        niveau INT,
        personneLudoId INT NOT NULL,
        FOREIGN KEY (personneLudoId) REFERENCES heritage.PersonneLudo(id)
    );

    INSERT INTO heritage.personneludo ("type") VALUES
        ('Client'),
        ('Fournisseur'),
        ('Employe'),
        ('Manager'),
        ('Technicien');
        
    INSERT INTO heritage.ClientLudo (nom,date_naissance, numero_client, date_inscription,personneludoid) VALUES 
        ('Pierre Durand','1985-08-15', 'CLI001', '2020-03-15',1),
        ('Marie Leroy','1992-02-28', 'CLI002', '2021-07-22',1);
        
    INSERT INTO heritage.FournisseurLudo (nom,date_naissance, societe, contact,personneludoid) VALUES 
            ('Antoine Moreau', '1978-12-10', 'TechCorp SARL', 'contact@techcorp.com',2);

    INSERT INTO heritage.EmployeLudo (nom,date_naissance, numero_employe, date_embauche,personneludoid) VALUES 
            ('Alice Dupont', '1980-05-12', 'EMP001', '2010-09-01',3),
            ('Jean Martin', '1975-03-22', 'EMP002', '2015-06-15',3),
            ('Sophie Bernard', '1990-11-05', 'EMP003', '2018-01-20',3);
        
    INSERT INTO heritage.ManagerLudo (nom,date_naissance, numero_employe, date_embauche, departement, budget,personneLudoId) VALUES 
            ('Alice Dupont', '1980-05-12', 'EMP001', '2010-09-01', 'Informatique', 150000.00,4);

    INSERT INTO heritage.TechnicienLudo (nom,date_naissance, numero_employe, date_embauche, specialite, niveau,personneLudoId) VALUES 
            ('Jean Martin', '1975-03-22', 'EMP002', '2015-06-15', 'Développement Web', 3,5),
            ('Sophie Bernard', '1990-11-05', 'EMP003', '2018-01-20', 'Réseaux', 2,5);
EOSQL

echo "Base de données initialisée avec succès !"
