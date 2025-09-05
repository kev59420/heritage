# Annotations @DiscriminatorColumn et @DiscriminatorValue

## 📋 Vue d'ensemble

Les annotations `@DiscriminatorColumn` et `@DiscriminatorValue` sont utilisées avec la stratégie d'héritage JPA `SINGLE_TABLE` pour distinguer les différents types d'entités dans une même table physique.

## 🏗️ Concept de base

### `@DiscriminatorColumn`
- **Position** : Sur la classe parente (racine de la hiérarchie)
- **Rôle** : Définit une colonne qui stocke le **type** de l'entité
- **Objectif** : Permet à JPA de distinguer les différents types dans une même table

### `@DiscriminatorValue`
- **Position** : Sur chaque classe (parente et filles)
- **Rôle** : Définit la **valeur** stockée dans la colonne discriminante
- **Objectif** : Identifie le type spécifique de l'entité

## 🔄 Stratégies d'héritage JPA

### 1. JOINED (Approche actuelle du projet)
```java
@Inheritance(strategy = InheritanceType.JOINED)
```
- **Structure** : Une table par classe
- **Avantages** : Normalisation, pas de colonnes NULL
- **Inconvénients** : Jointures nécessaires

### 2. SINGLE_TABLE (Avec discriminator)
```java
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type_personne", discriminatorType = DiscriminatorType.STRING)
```
- **Structure** : Une seule table pour toute la hiérarchie
- **Avantages** : Performance, pas de jointures
- **Inconvénients** : Beaucoup de colonnes NULL

## 📊 Comparaison des approches

| Aspect | JOINED (Actuel) | SINGLE_TABLE (Discriminator) |
|--------|----------------|------------------------------|
| **Tables** | Une par classe | Une seule table |
| **Jointures** | Nécessaires | Aucune |
| **Performance lecture** | Moyenne | Excellente |
| **Performance écriture** | Bonne | Excellente |
| **Espace disque** | Optimal | Gaspillage (NULL) |
| **Évolutivité** | Excellente | Limitée |
| **Contraintes** | Flexibles | Difficiles |
| **Maintenance** | Facile | Moyenne |

## 🎯 Quand utiliser chaque approche ?

### ✅ Utilisez SINGLE_TABLE + Discriminator quand :
- Les entités filles ont **peu d'attributs spécifiques**
- Vous privilégiez la **performance** aux requêtes
- Vous avez besoin de requêtes **très rapides** sur la hiérarchie complète
- La hiérarchie est **stable** et peu évolutive

### ✅ Utilisez JOINED (approche actuelle) quand :
- Les entités filles ont **beaucoup d'attributs spécifiques**
- Vous voulez une **structure normalisée**
- Vous privilégiez l'**organisation** et la **maintenance**
- La hiérarchie est **évolutive**
