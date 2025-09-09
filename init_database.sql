-- Script d'initialisation de la base de données Heritage
-- Création du schéma heritage
CREATE SCHEMA IF NOT EXISTS heritage;

-- Table de base : personne
CREATE TABLE heritage.personne (
    id SERIAL,
    nom VARCHAR(100) NOT NULL,
    date_naissance DATE NOT NULL,
    CONSTRAINT pk_heritage_personne_id PRIMARY KEY (id)
);

-- Tables héritant de Personne
CREATE TABLE heritage.client (
    id INT,
    numero_client VARCHAR(20) NOT NULL,
    date_inscription DATE NOT NULL CONSTRAINT df_heritage_client_date_inscription DEFAULT CURRENT_DATE,
    CONSTRAINT pk_heritage_client_id PRIMARY KEY (id),
    CONSTRAINT fk_heritage_client_personne FOREIGN KEY (id) REFERENCES heritage.personne(id)
);

-- Table Produits
CREATE TABLE heritage.produit (
    id SERIAL,
    nom VARCHAR(100) NOT NULL,
    description TEXT,
    prix DECIMAL(10,2) NOT NULL,
    stock INT NOT NULL,
    categorie VARCHAR(50),
    CONSTRAINT pk_heritage_produit_id PRIMARY KEY (id),
    CONSTRAINT ch_heritage_produit_prix CHECK (prix >= 0),
    CONSTRAINT ch_heritage_produit_stock CHECK (stock >= 0)
);

-- Table Commandes (méthode Kevin - héritage)
CREATE TABLE heritage.commande (
    id SERIAL,
    date_commande DATE NOT NULL CONSTRAINT df_heritage_commande_date_commande DEFAULT CURRENT_DATE,
    client_id INT NOT NULL,
    produit_id INT NOT NULL,
    quantite INT NOT NULL,
    prix_unitaire DECIMAL(10,2) NOT NULL,
    CONSTRAINT pk_heritage_commande_id PRIMARY KEY (id),
    CONSTRAINT ch_heritage_commande_quantite CHECK (quantite > 0),
    CONSTRAINT ch_heritage_commande_prix_unitaire CHECK (prix_unitaire >= 0),
    CONSTRAINT fk_heritage_commande_client FOREIGN KEY (client_id) REFERENCES heritage.client(id),
    CONSTRAINT fk_heritage_commande_produit FOREIGN KEY (produit_id) REFERENCES heritage.produit(id)
);

CREATE TABLE heritage.fournisseur (
    id INT,
    societe VARCHAR(100) NOT NULL,
    contact VARCHAR(100),
    CONSTRAINT pk_heritage_fournisseur_id PRIMARY KEY (id),
    CONSTRAINT fk_heritage_fournisseur_personne FOREIGN KEY (id) REFERENCES heritage.personne(id)
);

CREATE TABLE heritage.employe (
    id INT,
    numero_employe VARCHAR(20) NOT NULL,
    date_embauche DATE NOT NULL,
    CONSTRAINT pk_heritage_employe_id PRIMARY KEY (id),
    CONSTRAINT ak_heritage_employe_numero_employe UNIQUE (numero_employe),
    CONSTRAINT fk_heritage_employe_personne FOREIGN KEY (id) REFERENCES heritage.personne(id)
);

-- Tables héritant d'Employe
CREATE TABLE heritage.manager (
    id INT,
    departement VARCHAR(50) NOT NULL,
    budget DECIMAL(12,2),
    CONSTRAINT pk_heritage_manager_id PRIMARY KEY (id),
    CONSTRAINT fk_heritage_manager_employe FOREIGN KEY (id) REFERENCES heritage.employe(id)
);

CREATE TABLE heritage.technicien (
    id INT,
    specialite VARCHAR(50) NOT NULL,
    niveau INT,
    CONSTRAINT pk_heritage_technicien_id PRIMARY KEY (id),
    CONSTRAINT ch_heritage_technicien_niveau CHECK (niveau IN (1, 2, 3, 4, 5)),
    CONSTRAINT fk_heritage_technicien_employe FOREIGN KEY (id) REFERENCES heritage.employe(id)
);

-- Insertion de données de test
-- Personnes
INSERT INTO heritage.personne (nom, date_naissance) VALUES 
    ('Alice Dupont', '1980-05-12'),
    ('Jean Martin', '1975-03-22'),
    ('Sophie Bernard', '1990-11-05'),
    ('Pierre Durand', '1985-08-15'),
    ('Marie Leroy', '1992-02-28'),
    ('Antoine Moreau', '1978-12-10');

-- Clients
INSERT INTO heritage.client (id, numero_client, date_inscription) VALUES 
    (4, 'CLI001', '2020-03-15'),
    (5, 'CLI002', '2021-07-22');

-- Produits
INSERT INTO heritage.produit (nom, description, prix, stock, categorie) VALUES 
    ('Ordinateur Portable', 'PC portable 15 pouces, 8GB RAM, SSD 256GB', 899.99, 25, 'Informatique'),
    ('Souris Sans Fil', 'Souris optique sans fil ergonomique', 29.99, 150, 'Peripheriques'),
    ('Clavier Mécanique', 'Clavier mécanique rétroéclairé gaming', 79.99, 75, 'Peripheriques'),
    ('Écran 24 pouces', 'Moniteur LED 24 pouces Full HD', 189.99, 40, 'Ecrans'),
    ('Casque Audio', 'Casque audio Bluetooth avec réduction de bruit', 149.99, 60, 'Audio');

-- Commandes (méthode Kevin)
INSERT INTO heritage.commande (date_commande, client_id, produit_id, quantite, prix_unitaire) VALUES 
    ('2024-01-15', 4, 1, 2, 899.99),
    ('2024-02-20', 4, 2, 3, 29.99),
    ('2024-03-10', 5, 3, 1, 79.99),
    ('2024-03-25', 5, 4, 2, 189.99);

-- Fournisseurs
INSERT INTO heritage.fournisseur (id, societe, contact) VALUES 
    (6, 'TechCorp SARL', 'contact@techcorp.com');

-- Employés
INSERT INTO heritage.employe (id, numero_employe, date_embauche) VALUES 
    (1, 'EMP001', '2010-09-01'),
    (2, 'EMP002', '2015-06-15'),
    (3, 'EMP003', '2018-01-20');

-- Managers
INSERT INTO heritage.manager (id, departement, budget) VALUES 
    (1, 'Informatique', 150000.00);

-- Techniciens
INSERT INTO heritage.technicien (id, specialite, niveau) VALUES 
    (2, 'Développement Web', 3),
    (3, 'Réseaux', 2);

-- METHODE DE LUDO

CREATE TABLE heritage.personne_ludo (
    id SERIAL,
    type VARCHAR(100) NOT NULL,
    CONSTRAINT pk_heritage_personne_ludo_id PRIMARY KEY (id)
);

CREATE TABLE heritage.client_ludo (
    id SERIAL,
    nom VARCHAR(100) NOT NULL,
    date_naissance DATE NOT NULL,
    numero_client VARCHAR(20) NOT NULL,
    date_inscription DATE NOT NULL,
    personne_ludo_id INT NOT NULL,
    CONSTRAINT pk_heritage_client_ludo_id PRIMARY KEY (id),
    CONSTRAINT fk_heritage_client_ludo_personne_ludo FOREIGN KEY(personne_ludo_id) REFERENCES heritage.personne_ludo(id)
);

-- Table ProduitsLudo (méthode Ludo - composition)
CREATE TABLE heritage.produit_ludo (
    id SERIAL,
    nom VARCHAR(100) NOT NULL,
    description TEXT,
    prix DECIMAL(10,2) NOT NULL,
    stock INT NOT NULL,
    categorie VARCHAR(50),
    CONSTRAINT pk_heritage_produitLudo_id PRIMARY KEY (id),
    CONSTRAINT ch_heritage_produitLudo_prix CHECK (prix >= 0),
    CONSTRAINT ch_heritage_produitLudo_stock CHECK (stock >= 0)
);

-- Table CommandesLudo (méthode Ludo - composition)
CREATE TABLE heritage.commande_ludo (
    id SERIAL,
    date_commande DATE NOT NULL CONSTRAINT df_heritage_CommandesLudo_date_commande DEFAULT CURRENT_DATE,
    client_ludo_id INT NOT NULL,
    produit_id INT NOT NULL,
    quantite INT NOT NULL,
    prix_unitaire DECIMAL(10,2) NOT NULL,
    CONSTRAINT pk_heritage_commande_ludo_id PRIMARY KEY (id),
    CONSTRAINT ch_heritage_commande_ludo_quantite CHECK (quantite > 0),
    CONSTRAINT ch_heritage_commande_ludo_prix_unitaire CHECK (prix_unitaire >= 0),
    CONSTRAINT fk_heritage_commande_ludo_client_ludo FOREIGN KEY (client_ludo_id) REFERENCES heritage.client_ludo(id),
    CONSTRAINT fk_heritage_commande_ludo_produit_ludo FOREIGN KEY (produit_id) REFERENCES heritage.produit_ludo(id)
);

CREATE TABLE heritage.fournisseur_ludo (
    id SERIAL,
    nom VARCHAR(100) NOT NULL,
    date_naissance DATE NOT NULL,
    societe VARCHAR(100) NOT NULL,
    contact VARCHAR(100),
    personne_ludo_id INT NOT NULL,
    CONSTRAINT pk_heritage_fournisseur_ludo_id PRIMARY KEY (id),
    CONSTRAINT fk_heritage_fournisseur_ludo_personne_ludo FOREIGN KEY(personne_ludo_id) REFERENCES heritage.personne_ludo(id)
);

CREATE TABLE heritage.employe_ludo (
    id SERIAL,
    nom VARCHAR(100) NOT NULL,
    date_naissance DATE NOT NULL,
    numero_employe VARCHAR(20) NOT NULL,
    date_embauche DATE NOT NULL,
    personne_ludo_id INT NOT NULL,
    CONSTRAINT pk_heritage_employe_ludo_id PRIMARY KEY (id),
    CONSTRAINT fk_heritage_employe_ludo_personne_ludo FOREIGN KEY(personne_ludo_id) REFERENCES heritage.personne_ludo(id)
);

CREATE TABLE heritage.manager_ludo (
    id SERIAL,
    nom VARCHAR(100) NOT NULL,
    date_naissance DATE NOT NULL,
    numero_employe VARCHAR(20) NOT NULL,
    date_embauche DATE NOT NULL,
    departement VARCHAR(50) NOT NULL,
    budget DECIMAL(12,2),
    personne_ludo_id INT NOT NULL,
    CONSTRAINT pk_heritage_ManagerLudo_id PRIMARY KEY (id),
    CONSTRAINT fk_heritage_manager_ludo_personne_ludo FOREIGN KEY (personne_ludo_id) REFERENCES heritage.personne_ludo(id)
);

CREATE TABLE heritage.technicien_ludo (
    id SERIAL,
    nom VARCHAR(100) NOT NULL,
    date_naissance DATE NOT NULL,
    numero_employe VARCHAR(20) NOT NULL,
    date_embauche DATE NOT NULL,
    specialite VARCHAR(50) NOT NULL,
    niveau INT,
    personne_ludo_id INT NOT NULL,
    CONSTRAINT pk_heritage_technicien_ludo_id PRIMARY KEY (id),
    CONSTRAINT fk_heritage_technicien_ludo_personne_ludo FOREIGN KEY (personne_ludo_id) REFERENCES heritage.personne_ludo(id)
);

INSERT INTO heritage.personne_ludo ("type") VALUES
    ('Client'),
    ('Fournisseur'),
    ('Employe'),
    ('Manager'),
    ('Technicien');

INSERT INTO heritage.client_ludo (nom,date_naissance, numero_client, date_inscription,personne_ludo_id) VALUES 
    ('Pierre Durand','1985-08-15', 'CLI001', '2020-03-15',1),
    ('Marie Leroy','1992-02-28', 'CLI002', '2021-07-22',1);

INSERT INTO heritage.fournisseur_ludo (nom,date_naissance, societe, contact,personne_ludo_id) VALUES 
        ('Antoine Moreau', '1978-12-10', 'TechCorp SARL', 'contact@techcorp.com',2);

INSERT INTO heritage.employe_ludo (nom,date_naissance, numero_employe, date_embauche,personne_ludo_id) VALUES 
        ('Alice Dupont', '1980-05-12', 'EMP001', '2010-09-01',3),
        ('Jean Martin', '1975-03-22', 'EMP002', '2015-06-15',3),
        ('Sophie Bernard', '1990-11-05', 'EMP003', '2018-01-20',3);

INSERT INTO heritage.manager_ludo (nom,date_naissance, numero_employe, date_embauche, departement, budget,personne_ludo_id) VALUES 
        ('Alice Dupont', '1980-05-12', 'EMP001', '2010-09-01', 'Informatique', 150000.00,4);

INSERT INTO heritage.technicien_ludo (nom,date_naissance, numero_employe, date_embauche, specialite, niveau,personne_ludo_id) VALUES 
        ('Jean Martin', '1975-03-22', 'EMP002', '2015-06-15', 'Développement Web', 3,5),
        ('Sophie Bernard', '1990-11-05', 'EMP003', '2018-01-20', 'Réseaux', 2,5);

-- ProduitsLudo (méthode Ludo)
INSERT INTO heritage.produit_ludo (nom, description, prix, stock, categorie) VALUES 
    ('Ordinateur Portable Ludo', 'PC portable 15 pouces, 8GB RAM, SSD 256GB - Version Ludo', 899.99, 25, 'Informatique'),
    ('Souris Sans Fil Ludo', 'Souris optique sans fil ergonomique - Version Ludo', 29.99, 150, 'Périphériques'),
    ('Clavier Mécanique Ludo', 'Clavier mécanique rétroéclairé gaming - Version Ludo', 79.99, 75, 'Périphériques'),
    ('Écran 24 pouces Ludo', 'Moniteur LED 24 pouces Full HD - Version Ludo', 189.99, 40, 'Écrans'),
    ('Casque Audio Ludo', 'Casque audio Bluetooth avec réduction de bruit - Version Ludo', 149.99, 60, 'Audio');

-- CommandesLudo (méthode Ludo)
INSERT INTO heritage.commande_ludo (date_commande, client_ludo_id, produit_id, quantite, prix_unitaire) VALUES 
    ('2024-01-18', 1, 1, 1, 899.99),
    ('2024-02-22', 1, 5, 2, 149.99),
    ('2024-03-12', 2, 2, 4, 29.99),
    ('2024-03-28', 2, 3, 1, 79.99);

-- Création d'index pour les Foreign Keys qui ne sont pas des Primary Keys
-- Les FK qui sont aussi PK (héritage) n'ont pas besoin d'index car PK sont indexées par défaut

-- Index pour la table Commandes (méthode Kevin)
CREATE INDEX ix_heritage_commande_client_id ON heritage.commande(client_id);
CREATE INDEX ix_heritage_commande_produit_id ON heritage.commande(produit_id);

-- Index pour la table ClientLudo (méthode Ludo)
CREATE INDEX ix_heritage_client_ludo_personne_ludo_id ON heritage.client_ludo(personne_ludo_id);

-- Index pour la table CommandesLudo (méthode Ludo)
CREATE INDEX ix_heritage_commande_ludo_client_ludo_id ON heritage.commande_ludo(client_ludo_id);
CREATE INDEX ix_heritage_commande_ludo_produit_id ON heritage.commande_ludo(produit_id);

-- Index pour la table FournisseurLudo (méthode Ludo)
CREATE INDEX ix_heritage_fournisseur_ludo_personne_ludo_id ON heritage.fournisseur_ludo(personne_ludo_id);

-- Index pour la table EmployeLudo (méthode Ludo)
CREATE INDEX ix_heritage_employe_ludo_personne_ludo_id ON heritage.employe_ludo(personne_ludo_id);

-- Index pour la table ManagerLudo (méthode Ludo)
CREATE INDEX ix_heritage_manager_ludo_personne_ludo_id ON heritage.manager_ludo(personne_ludo_id);

-- Index pour la table TechnicienLudo (méthode Ludo)
CREATE INDEX ix_heritage_technicien_ludo_personne_ludo_id ON heritage.technicien_ludo(personne_ludo_id);
