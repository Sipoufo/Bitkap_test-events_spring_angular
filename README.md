# Bitkap_test-events_spring_angular

Ce projet est la résultante du teste technique proposé par **Bitakp** dont le but est de réaliser une application web de
gestion d'évènement.

## Objectifs :
Développer une application de gestion d'événements où les utilisateurs peuvent s'inscrire, modifier, supprimer, afficher
des événements et y faire des commentaires.

## Contraintes :
* Utiliser SpringBoot pour la partie API,
* Utiliser Angular pour la partie FrontEnd,
* Utiliser Keycloak comme fournisseur d'identité pour sécuriser tous les endpoints,
* Ne pas sécuriser la route qui fournit la liste des évènements.

## Entités :
### a- Évènement :
* Un titre (avec un maximum de 100 caractères)
* Une description
* Une date de l'événement
* Une personne responsable de l'événement
* Une liste de commentaires (elle peut être vide)

### b- Commentaire :
* Un texte
* Une date

### c- User :
* Un nom
* Un email
* Un password

## Exigences de l'API
Routes :
* Liste d'événements (pas d'authentification nécessaire). Elle doit être paginée
* Créer un nouvel événement
* Obtenir les détails d'un événement ainsi que ses commentaires
* Mise à jour d'un événement
* Supprimer un événement
* Ajouter un commentaire à un événement
* Supprimer un commentaire

## Exigences Frontend
* Implémenter une interface utilisateur utilisant Angular qui permet :

    * de lister événements, tout en les paginant (cette route doit être publique)

    * aux utilisateurs authentifiés de créer, mettre à jour et supprimer des événements

    * Les utilisateurs authentifiés peuvent ajouter et supprimer des commentaires sur les événements.

* Intégrer Keycloak pour la connexion et la déconnexion.

* Affichage des informations sur l'utilisateur lorsqu'il est connecté

* Fournir des messages d'erreur et des états de chargement



## Sécurité
* Utiliser Keycloak comme fournisseur d'identité OAuth2

* Sécuriser toutes les routes du backend, sauf celui qui permet d'obtenir la liste des événements

* Le frontend Angular devrait :

    * Rediriger les utilisateurs vers le login de Keycloak

    * Effectuer des requêtes authentifiées

## Paramètres Keycloak à utiliser :

* issuer: "https://sso.bitkap.africa"
* domain: "https://sso.bitkap.africa"
* Realm: "bitkap_dev"
* client id: "angolar_test"
Avec un utilisateur : email = "test@bitkap.net" , password = "password"