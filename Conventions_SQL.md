# Conventions SQL - Projet Heritage

## Règles de Nommage

### Important : Comportement PostgreSQL
PostgreSQL convertit automatiquement tous les identifiants en **minuscules** sauf s'ils sont entourés de guillemets doubles. Pour éviter les complications, nous utilisons des noms en minuscules avec snake_case.

### Nommage des schémas et tables : snake_case

Le nommage des schémas et tables utilise des minuscules avec des underscores pour séparer les mots (snake_case).
Le nommage est au singulier pas de pluriels !

**Exemples :**

```sql
CREATE SCHEMA heritage;
CREATE TABLE heritage.personne
CREATE TABLE heritage.client
CREATE TABLE heritage.commande
```

### Nommage des attributs : snake_case

Le nommage des attributs utilise des minuscules avec des underscores pour séparer les mots (snake_case).

**Règles spécifiques :**
- Il n'est pas nécessaire de répéter le nom de la table dans le nom de l'attribut : `personne_id` → `id`
- **Exception :** Pour les clés étrangères, utilisez le format `nom_table_id` → `client_id`, `produit_id`

**Exemples :**

```sql
-- Attributs standards
nom VARCHAR(100) NOT NULL,
date_naissance DATE NOT NULL,
numero_client VARCHAR(20) NOT NULL,
date_inscription DATE NOT NULL,
numero_employe VARCHAR(20) NOT NULL,
date_embauche DATE NOT NULL,
prix_unitaire DECIMAL(10,2) NOT NULL

-- Clés étrangères
client_id INT NOT NULL,
produit_id INT NOT NULL
```

## Règles de Contraintes (snake_case)

Le nom des contraintes contient le diminutif de la contrainte, le nom du schéma en PascalCase, le nom de la table en PascalCase et le ou les attributs concernés en snake_case, séparés par un underscore (_).

### PRIMARY KEY : `pk_nomSchema_nomTable_attribut`

```sql
CONSTRAINT pk_heritage_personne_id PRIMARY KEY (id)
CONSTRAINT pk_heritage_client_id PRIMARY KEY (id)
CONSTRAINT pk_heritage_produit_ludo_id PRIMARY KEY (id)
```

### UNIQUE : `ak_nomSchema_nomTable_attribut`

```sql
CONSTRAINT ak_heritage_employe_numero_employe UNIQUE(numero_employe)
```

### DEFAULT : `df_nomSchema_nomTable_attribut`

```sql
date_inscription DATE NOT NULL CONSTRAINT df_heritage_client_date_inscription DEFAULT CURRENT_DATE
date_commande DATE NOT NULL CONSTRAINT df_heritage_commande_date_commande DEFAULT CURRENT_DATE
```

### CHECK : `ch_nomSchema_nomTable_attribut`

```sql
CONSTRAINT ch_heritage_technicien_niveau CHECK (niveau IN (1, 2, 3, 4, 5))
CONSTRAINT ch_heritage_produit_prix CHECK (prix >= 0)
CONSTRAINT ch_heritage_commande_quantite CHECK (quantite > 0)
CONSTRAINT ch_heritage_commande_prix_unitaire CHECK (prix_unitaire >= 0)
```

### FOREIGN KEY : `fk_nomSchema_nomTable_tableReference`

```sql
CONSTRAINT fk_heritage_client_personne FOREIGN KEY (id) REFERENCES heritage.personne(id)
CONSTRAINT fk_heritage_commande_client FOREIGN KEY (client_id) REFERENCES heritage.client(id)
```

## Règles d'Indexation

### Indexation automatique :
- Les identifiants **PRIMARY KEY** sont indexés par défaut

### Indexation manuelle requise :
Un index doit être créé pour chaque **FOREIGN KEY** qui n'est **pas** aussi une **PRIMARY KEY**.

**Nommage :** `ix_nomSchema_nomTable_attribut`

**Exemples :**

```sql
-- Index requis (FK ≠ PK)
CREATE INDEX ix_heritage_commande_client_id ON heritage.commande(client_id);
CREATE INDEX ix_heritage_commande_produit_id ON heritage.commande(produit_id);
```