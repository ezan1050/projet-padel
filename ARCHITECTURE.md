# Dossier d'architecture — Projet Padel

## Architecture backend

Le backend est une API REST développée avec Spring Boot (Java 21).
Il est organisé en couches, chacune ayant une responsabilité unique.

Une requête HTTP (par exemple `GET /api/sites`) traverse les couches
suivantes, de haut en bas, et la réponse remonte dans l'autre sens :

- **Controller** : reçoit les requêtes HTTP et renvoie les réponses.
- **Service** : contient la logique métier (les règles du projet).
- **Repository** : lit et écrit dans la base de données.
- **Model / Entity** : représente les tables de la base de données.

La communication entre les couches se fait par injection de dépendances :
chaque couche reçoit celles dont elle a besoin sans les instancier elle-même.

## Modèle de données

Le projet compte six entités :

- **Site** : un complexe de padel.
- **Terrain** : un court, rattaché à un site.
- **Membre** : un joueur (type Global, Site ou Libre).
- **Match** : une partie, privée ou publique.
- **Participation** : relie un membre à un match (qui joue à quel match).
- **Paiement** : le règlement d'une participation.

## Relations entre les tables

Le modèle utilise les trois grands types de relations :

- **Plusieurs vers un (`@ManyToOne`)** : plusieurs terrains appartiennent à
  un même site. De même, plusieurs matchs se jouent sur un même terrain.

- **Un vers un (`@OneToOne`)** : un paiement correspond à exactement une
  participation.

- **Plusieurs vers plusieurs**, via la table `Participation` : un match compte
  plusieurs joueurs, et un joueur participe à plusieurs matchs. Cette relation
  ne pouvant pas être directe, la table `Participation` sert d'intermédiaire :
  chaque ligne relie un membre à un match.

L'intégrité référentielle est assurée par les clés étrangères : il est
impossible de relier une entité à une autre qui n'existe pas.

## Sécurité et authentification

L'authentification repose sur des jetons JWT (JSON Web Token).

- L'utilisateur se connecte via `POST /api/auth/login` en envoyant son
  matricule et son mot de passe.
- Si les identifiants sont corrects, le backend renvoie un **token JWT**.
- Ce token est ensuite envoyé à chaque requête (en-tête `Authorization`).
  Un filtre le vérifie et identifie l'utilisateur ainsi que son rôle.

Les mots de passe ne sont jamais stockés en clair : ils sont chiffrés
avec BCrypt.

Trois rôles définissent les droits d'accès :

- **USER** : utilisateur simple (joueur).
- **ADMIN_SITE** : administrateur d'un site.
- **ADMIN_GLOBAL** : administrateur de l'ensemble des sites.

Certaines opérations sont réservées à certains rôles. Par exemple, la
suppression d'un match n'est autorisée qu'aux administrateurs.


## Architecture frontend

Le frontend est développé avec Angular. Il est organisé autour de
plusieurs types d'éléments :

- **Composants** (`login`, `matchs`, `admin`) : les écrans de l'application.
  Chaque composant a son fichier HTML (structure), CSS (style) et
  TypeScript (logique).
- **Services** (`site`, `match`, `auth`) : ils communiquent avec le backend
  via HttpClient pour récupérer ou envoyer des données.
- **Intercepteur** (`auth-interceptor`) : ajoute automatiquement le token JWT
  à chaque requête envoyée au backend.
- **Guard** (`auth-guard`) : empêche l'accès aux pages protégées si
  l'utilisateur n'est pas connecté.
- **Routing** (`app.routes.ts`) : associe chaque URL à un composant.

La communication avec le backend se fait uniquement via des appels HTTP
sur l'API REST.


## Librairies, outils et frameworks

**Backend :**
- Spring Boot (Java 21) — framework principal
- Spring Data JPA / Hibernate — accès à la base de données
- H2 — base de données en mémoire
- Spring Security — sécurité et authentification
- jjwt — génération et vérification des tokens JWT
- springdoc-openapi — documentation Swagger de l'API
- Maven — gestion des dépendances et build

**Frontend :**
- Angular — framework principal
- Bootstrap — mise en forme et responsive
- npm — gestion des dépendances

## API — Swagger

Lorsque le backend est démarré, la documentation interactive de l'API
est disponible à l'adresse :

http://localhost:8080/swagger-ui.html