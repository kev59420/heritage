# TP : Comparaison des approches d'héritage - ManyToOne vs HeritagePure SQL

## 📋 Objectifs du TP
Ce TP vise à démontrer les avantages de l'héritage JPA (approche Kevin) par rapport aux relations ManyToOne (approche Ludo) à travers des requêtes SQL et REST concrètes.

## 🏗️ Architecture des deux systèmes

### Système Kevin (Héritage JPA)
- **Stratégie** : `@Inheritance(strategy = InheritanceType.JOINED)`
- **Tables** : Tables liées par clé primaire commune
- **Endpoints** : `/api/kevin/*`

### Système Ludo (Relations ManyToOne)
- **Stratégie** : Relations `@ManyToOne` vers `PersonneLudo`
- **Tables** : Tables séparées avec clés étrangères
- **Endpoints** : `/api/ludo/*`

## Initialisation du project

1 - Lancer la commande afin d'initialiser la bdd postgres:
```
docker compose up -d
```

2 - Lancer postman et importer les fichiers via import -> files -> fichiers json:
- Heritage_Kevin_API_Collection.json
- Heritage_Ludo_API_Collection.json

3 Se connceter a la base via pgAdmin (Optionnel):
- Vous avec un récap des insertions dans les tables dans le fichier excel "Heritage.xlsx"
- Ouvrez pgAdmin pour intéragir avec la bdd : 
  - creer un nouveaux server group
  - creer un nouveau server avec :
    - hostname: localhost
    - Port: 4530
    - Maintenance DB: postgres
    - Username: postgres

4 - Lancer le programme !
  

## 🔍 Exercice 1 : Polymorphisme et requêtes sur la classe parente

### ❌ **Problème avec l'approche OneToMany**

**Objectif** : Récupérer toutes les personnes (tous types confondus)

#### Requête SQL Ludo (COMPLEXE) :
```sql
-- Il faut faire une UNION de toutes les tables !
SELECT 'Client' as type, nom, date_naissance FROM heritage.ClientLudo
UNION ALL
SELECT 'Fournisseur' as type, nom, date_naissance FROM heritage.FournisseurLudo  
UNION ALL
SELECT 'Employe' as type, nom, date_naissance FROM heritage.EmployeLudo
UNION ALL
SELECT 'Manager' as type, nom, date_naissance FROM heritage.ManagerLudo
UNION ALL
SELECT 'Technicien' as type, nom, date_naissance FROM heritage.TechnicienLudo;
```

Via postman il est nécessaire de faire 5 requêtes afin de récupérer l'ensemble des Personnes de tout type.

### ✅ **Solution élégante avec l'approche Heritage Pure**

#### Requête SQL Kevin (SIMPLE) :
```sql
-- Une seule requête suffit !
SELECT * FROM heritage.Personne;
```

Via postman on peut récupérer la liste des personnes facilement via :

```
GET localhost:8082/api/kevin/personnes
```
On peut remarquer qu'on a l'ensemble des informations avec les divers attribut pour chaque Type de Personne. Ce qui n'est pas possible avec l'autre approche sans bien evidemment faire une multitude de requête or native de l'orm Hibernate.

---

## 🔍 Exercice 2 : Recherche par attribut commun

### ❌ **Problème avec l'approche OneToMany**

**Objectif** : Trouver toutes les personnes nées avant 1980

#### Requête SQL Ludo (RÉPÉTITIVE) :
```sql
SELECT 'Client' as type, nom, date_naissance FROM heritage.ClientLudo 
WHERE date_naissance < '1980-01-01'
UNION ALL
SELECT 'Fournisseur' as type, nom, date_naissance FROM heritage.FournisseurLudo  
WHERE date_naissance < '1980-01-01'
UNION ALL
SELECT 'Employe' as type, nom, date_naissance FROM heritage.EmployeLudo
WHERE date_naissance < '1980-01-01'
UNION ALL
SELECT 'Manager' as type, nom, date_naissance FROM heritage.ManagerLudo
WHERE date_naissance < '1980-01-01'
UNION ALL
SELECT 'Technicien' as type, nom, date_naissance FROM heritage.TechnicienLudo
WHERE date_naissance < '1980-01-01';
```

### ✅ **Solution simple avec l'approche Heritage Pure**

#### Requête SQL Kevin (CONCISE) :
```sql
SELECT * FROM heritage.Personne 
WHERE date_naissance < '1980-01-01';
```

---

## 🔍 Exercice 3 : Mise à jour en masse

### ❌ **Problème avec l'approche OneToMany**

**Objectif** : Mettre à jour le nom de famille "Dupont" en "Durand" pour toutes les personnes

#### SQL Ludo (5 REQUÊTES) :
```sql
UPDATE heritage.ClientLudo SET nom = REPLACE(nom, 'Dupont', 'Durand') WHERE nom LIKE '%Dupont%';
UPDATE heritage.FournisseurLudo SET nom = REPLACE(nom, 'Dupont', 'Durand') WHERE nom LIKE '%Dupont%';
UPDATE heritage.EmployeLudo SET nom = REPLACE(nom, 'Dupont', 'Durand') WHERE nom LIKE '%Dupont%';
UPDATE heritage.ManagerLudo SET nom = REPLACE(nom, 'Dupont', 'Durand') WHERE nom LIKE '%Dupont%';
UPDATE heritage.TechnicienLudo SET nom = REPLACE(nom, 'Dupont', 'Durand') WHERE nom LIKE '%Dupont%';
```

### ✅ **Solution efficace avec l'approche Heritage Pure**

#### SQL Kevin (1 SEULE REQUÊTE) :
```sql
UPDATE heritage.Personne 
SET nom = REPLACE(nom, 'Dupont', 'Durand') 
WHERE nom LIKE '%Dupont%';
```

---

## 🔍 Exercice 4 : Contraintes d'intégrité et validation

### ❌ **Problème avec l'approche Ludo**

**Test** : Que se passe-t-il si on incére l'id d'un type autre que l'id client dans `ClientLudo` ?

#### REST Ludo (ERREUR) :

Aller dans postamn Requet GET: Get All PersonnesLudo
```
localhost:8082/api/ludo/personnes
```
vous devriez avoir:
```json
[
    {
        "id": 1,
        "type": "Client"
    },
    {
        "id": 2,
        "type": "Fournisseur"
    },
    {
        "id": 3,
        "type": "Employe"
    },
    {
        "id": 4,
        "type": "Manager"
    },
    {
        "id": 5,
        "type": "Technicien"
    }
]
```

Aller dans postman Requête POST: Create ClientLudo:
```
localhost:8082/api/ludo/clients
```
et dans body insérer:
```json
{
    "nom": "Test Client",
    "dateNaissance": "1990-01-01",
    "numeroClient": "CLI999",
    "dateInscription": "2023-01-01",
    "personneLudoId": 2
}
```
Si vous remarquez bien nous avons mis un id différent de l'id qui défini la catégorie client mais on a mis l'id fournisseur et pourtant nous avons eu aucune erreur.

Je peux même modifier l'id d'un nuplet déja présent et je me retrouve avec un nuplet avec des attributs par exemple d'un fournisseurs mais qui est catégoriser comme un client en base par exemple faite :

```
GET localhost:8082/api/ludo/fournisseurs
```
On va modifier le fournisseur actuellement présent via la requête PUT Update FournisseurLudo:
```
PUT localhost:8082/api/ludo/fournisseurs/1
``` 
ajouter dans le body:

```json
{
    "nom": "Antoine Moreau",
    "dateNaissance": "1978-12-10",
    "societe": "TechCorp SARL",
    "contact": "contact@techcorp.com",
    "personneLudoId": 5
}
```

Et voila vous avez modifier un fournisseur en un Technicien avec les attributs d'un fournisseurs ce qui n'est pas conforme.




### ✅ **Gestion naturelle avec l'approche HeritagePur**

Avec l'heriage pure cet aspect n'est pas possible.

### **Impossible de catégoriser une Personne comme employe et technicien avec l'approche OneToMany contrairement a l'heritage Pure avec le même id**

Si vous lister la liste des employé avec l'approche heritage pure via la requête
GET Get ALL Employes: 

```
GET localhost:8082/api/kevin/employes
```
```json
[
    {
        "id": 1,
        "nom": "Alice Dupont",
        "dateNaissance": "1980-05-12",
        "numeroEmploye": "EMP001",
        "dateEmbauche": "2010-09-01",
        "departement": "Informatique",
        "budget": 150000.00
    },
    {
        "id": 2,
        "nom": "Jean Martin",
        "dateNaissance": "1975-03-22",
        "numeroEmploye": "EMP002",
        "dateEmbauche": "2015-06-15",
        "specialite": "Développement Web",
        "niveau": 3
    },
    {
        "id": 3,
        "nom": "Sophie Bernard",
        "dateNaissance": "1990-11-05",
        "numeroEmploye": "EMP003",
        "dateEmbauche": "2018-01-20",
        "specialite": "Réseaux",
        "niveau": 2
    }
]
```
Maitenant vous faites la requêtes GET All Techniciens:

```
GET localhost:8082/api/kevin/techniciens
```
```json
[
    {
        "id": 2,
        "nom": "Jean Martin",
        "dateNaissance": "1975-03-22",
        "numeroEmploye": "EMP002",
        "dateEmbauche": "2015-06-15",
        "specialite": "Développement Web",
        "niveau": 3
    },
    {
        "id": 3,
        "nom": "Sophie Bernard",
        "dateNaissance": "1990-11-05",
        "numeroEmploye": "EMP003",
        "dateEmbauche": "2018-01-20",
        "specialite": "Réseaux",
        "niveau": 2
    }
]
```
Maintenant si vous faites la même chose avec l'approche OneToMany :
```
GET localhost:8082/api/ludo/employes
```
Vous obtenez:
```json
[
    {
        "id": 1,
        "nom": "Alice Dupont",
        "dateNaissance": "1980-05-12",
        "numeroEmploye": "EMP001",
        "dateEmbauche": "2010-09-01",
        "personneLudo": {
            "id": 3,
            "type": "Employe"
        }
    },
    {
        "id": 2,
        "nom": "Jean Martin",
        "dateNaissance": "1975-03-22",
        "numeroEmploye": "EMP002",
        "dateEmbauche": "2015-06-15",
        "personneLudo": {
            "id": 3,
            "type": "Employe"
        }
    },
    {
        "id": 3,
        "nom": "Sophie Bernard",
        "dateNaissance": "1990-11-05",
        "numeroEmploye": "EMP003",
        "dateEmbauche": "2018-01-20",
        "personneLudo": {
            "id": 3,
            "type": "Employe"
        }
    }
]
```
GET ALL TechniciensLudo :
```
GET localhost:8082/api/ludo/techniciens
```
```json
[
    {
        "id": 1,
        "nom": "Jean Martin",
        "dateNaissance": "1975-03-22",
        "numeroEmploye": "EMP002",
        "dateEmbauche": "2015-06-15",
        "specialite": "Développement Web",
        "niveau": 3,
        "personneLudo": {
            "id": 5,
            "type": "Technicien"
        }
    },
    {
        "id": 2,
        "nom": "Sophie Bernard",
        "dateNaissance": "1990-11-05",
        "numeroEmploye": "EMP003",
        "dateEmbauche": "2018-01-20",
        "specialite": "Réseaux",
        "niveau": 2,
        "personneLudo": {
            "id": 5,
            "type": "Technicien"
        }
    }
]
```

Vous remarquerez que l'id de Jean Martin a complétement changer, en effet ceci est du au fait qu'il y a deux insertions en base une dans Employé et une dans technitiens. Et nous avons pas le même id ! 
De plus rien n'empéche d'update les attributs de l'employé Jean Martin dans la table employé et de laisser les attributs par défaut dans la table Technicien !

Et aussi par défaut avec l'heritage pur on obtient l'emsemble des attributs de la classe ce qui n'est pas le cas de l'autre approche!

**Ceci n'est pas possible avec l'apporoche Heritage Pure !**


## 🏆 Conclusion : Pourquoi l'approche HeritagePure > OneToMany

### ✅ **Avantages de l'approche Kevin (Héritage JPA)**

1. **🎯 Polymorphisme naturel** : Une seule requête pour tous les types
2. **📝 Code plus propre** : Pas de duplication d'attributs
3. **⚡ Meilleures performances** : Moins de jointures
4. **🔒 Intégrité garantie** : Suppression en cascade automatique
5. **🛠️ Maintenance facilitée** : Modifications centralisées
6. **🏗️ Architecture orientée objet** : Respect des principes OOP

### ❌ **Inconvénients de l'approche Ludo (Relations)**

1. **🔄 Requêtes répétitives** : UNION pour chaque recherche globale
2. **💾 Duplication de données** : Attributs répétés dans chaque table
3. **🐌 Performances dégradées** : Jointures obligatoires
4. **🚫 Contraintes complexes** : Gestion manuelle des dépendances
5. **🔧 Maintenance difficile** : Modifications à répéter partout
6. **📊 Pas de polymorphisme** : Impossible de traiter uniformément

---

A mon sens l'approche de ludo permet de catégoriser une table et elle peut être pertinente seulement dans le cas ou les catégories n'ont pas d'attribut ou de lien spécifique a leur catégorie, par exemple la table user dans compas dispose d'un attribut role: ADMIN, USER ça aurait pu être un héritage pur si jamais on avait voulu mettre des attributs ou des lien spécifique pour chaque catégorie d'utilisateur !