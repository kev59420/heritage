# TP : Comparaison des approches d'héritage - Kevin vs Ludo

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

---

## 🔍 Exercice 1 : Polymorphisme et requêtes sur la classe parente

### ❌ **Problème avec l'approche Ludo**

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

### ✅ **Solution élégante avec l'approche Kevin**

#### Requête SQL Kevin (SIMPLE) :
```sql
-- Une seule requête suffit !
SELECT * FROM heritage.Personne;
```

---

## 🔍 Exercice 2 : Recherche par attribut commun

### ❌ **Problème avec l'approche Ludo**

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

### ✅ **Solution simple avec l'approche Kevin**

#### Requête SQL Kevin (CONCISE) :
```sql
SELECT * FROM heritage.Personne 
WHERE date_naissance < '1980-01-01';
```

---

## 🔍 Exercice 3 : Mise à jour en masse

### ❌ **Problème avec l'approche Ludo**

**Objectif** : Mettre à jour le nom de famille "Dupont" en "Durand" pour toutes les personnes

#### SQL Ludo (5 REQUÊTES) :
```sql
UPDATE heritage.ClientLudo SET nom = REPLACE(nom, 'Dupont', 'Durand') WHERE nom LIKE '%Dupont%';
UPDATE heritage.FournisseurLudo SET nom = REPLACE(nom, 'Dupont', 'Durand') WHERE nom LIKE '%Dupont%';
UPDATE heritage.EmployeLudo SET nom = REPLACE(nom, 'Dupont', 'Durand') WHERE nom LIKE '%Dupont%';
UPDATE heritage.ManagerLudo SET nom = REPLACE(nom, 'Dupont', 'Durand') WHERE nom LIKE '%Dupont%';
UPDATE heritage.TechnicienLudo SET nom = REPLACE(nom, 'Dupont', 'Durand') WHERE nom LIKE '%Dupont%';
```

### ✅ **Solution efficace avec l'approche Kevin**

#### SQL Kevin (1 SEULE REQUÊTE) :
```sql
UPDATE heritage.Personne 
SET nom = REPLACE(nom, 'Dupont', 'Durand') 
WHERE nom LIKE '%Dupont%';
```

---

## 🔍 Exercice 4 : Contraintes d'intégrité et validation

### ❌ **Problème avec l'approche Ludo**

**Test** : Que se passe-t-il si on supprime un `PersonneLudo` référencé ?

#### REST Ludo (ERREUR) :
```bash
# Créer un client
curl -X POST http://localhost:8082/api/ludo/clients \
  -H "Content-Type: application/json" \
  -d '{
    "nom": "Test Client",
    "dateNaissance": "1990-01-01",
    "numeroClient": "CLI999",
    "dateInscription": "2023-01-01",
    "personneLudoId": 1
  }'

# Essayer de supprimer le PersonneLudo référencé
curl -X DELETE http://localhost:8082/api/ludo/personnes/1
# ERREUR : Contrainte de clé étrangère violée !
```

### ✅ **Gestion naturelle avec l'approche Kevin**

#### REST Kevin (COHÉRENT) :
```bash
# La suppression d'une personne supprime automatiquement 
# toutes ses spécialisations grâce à l'héritage
curl -X DELETE http://localhost:8082/api/kevin/personnes/1
# Suppression en cascade naturelle !
```

---

## 🔍 Exercice 5 : Problème de données UPDATE manager

### ❌ **Problème avec l'approche Ludo**

**Objectif** : Aucune erreur survient lors du changement d'un manager vers un client avec des attributs d'un manager

#### SQL Ludo :
```sql
UPDATE heritage.managerludo
SET personneludoid = 1
WHERE nom = 'Alice Dupont'

SELECT *
FROM heritage.managerludo as m
INNER JOIN heritage.personneludo as p 
	ON m.personneludoid = p.id

```


### ✅ **Performance optimale avec l'approche Kevin**

#### SQL Kevin (DIRECT) :
```sql
UPDATE heritage.personne
set id = 2
WHERE nom = 'Alice Dupont'
```

---

## 📊 Exercice 6 : Tests pratiques avec Postman

### Test 1 : Polymorphisme
```bash
# Kevin - Récupérer toutes les personnes (polymorphisme)
GET http://localhost:8082/api/kevin/personnes

# Ludo - Impossible en un seul appel !
```

### Test 2 : Création d'entités
```bash
# Kevin - Création d'un manager (héritage automatique)
POST http://localhost:8082/api/kevin/managers
{
  "nom": "Alice Manager",
  "dateNaissance": "1985-03-15",
  "numeroEmploye": "MGR001",
  "dateEmbauche": "2020-01-01",
  "departement": "IT",
  "budget": 100000.00
}

# Ludo - Création complexe (référence obligatoire)
POST http://localhost:8082/api/ludo/managers
{
  "nom": "Alice Manager",
  "dateNaissance": "1985-03-15", 
  "numeroEmploye": "MGR001",
  "dateEmbauche": "2020-01-01",
  "departement": "IT",
  "budget": 100000.00,
  "personneLudoId": 4  # ⚠️ Référence obligatoire !
}
```

---

## 🏆 Conclusion : Pourquoi Kevin > Ludo

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

## 📝 Exercices pratiques

1. **Testez les deux approches** avec les collections Postman fournies
2. **Comparez les temps de réponse** pour les requêtes complexes
3. **Analysez les logs SQL** générés par Hibernate
4. **Essayez de faire des modifications en masse** sur les deux systèmes

### Questions de réflexion :
- Quelle approche facilite le refactoring ?
- Laquelle respecte mieux les principes SOLID ?
- Comment évoluent les performances avec l'augmentation des données ?

**Verdict final** : L'héritage JPA (Kevin) est clairement supérieur pour modéliser des hiérarchies d'entités ! 🏆