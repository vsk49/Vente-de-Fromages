# Vente-de-Fromages

**Description**: Application pour la vente de fromages.

## Table des matières
- [Aperçu](#aperçu)
- [Fonctionnalités](#fonctionnalités)
- [Installation](#installation)
- [Utilisation](#utilisation)
- [Contribuer](#contribuer)
- [Licence](#licence)

## Aperçu
Vente-de-Fromages est une application développée en Java pour faciliter la vente de fromages en ligne. Elle permet aux utilisateurs de parcourir différents types de fromages, de les ajouter à leur panier et de finaliser leurs achats.

## Fonctionnalités
- Affichage de la liste de fromages disponibles en France. 
- Ajout des fromages au panier
- Modification de la ligne de commande du panier (modifier la quantite souhaitee)
- Recuperation des coordonnees des clients (nom, prenom, adresse complete, type de livraison)
- edition du ticket d'achat

## Installation
Pour installer et exécuter ce projet localement, suivez les étapes ci-dessous :

1. Clonez le dépôt :
    ```bash
    git clone https://github.com/vsk49/Vente-de-Fromages.git
    ```
2. Accédez au répertoire du projet :
    ```bash
    cd Vente-de-Fromages
    ```
3. Compilez le projet :
    ```bash
    javac -d bin src/*.java
    ```
4. Exécutez l'application :
    ```bash
    java -cp bin Main
    ```

## Utilisation
Après avoir installé l'application, vous pouvez l'utiliser en suivant ces étapes :

1. Lancez l'application en exécutant la commande ci-dessus.
2. Naviguez à travers les différents fromages disponibles.
3. Ajoutez les fromages de votre choix à votre panier.
4. Finalisez votre achat en procédant au paiement.

## Contribuer
Les contributions sont les bienvenues ! Pour contribuer, veuillez suivre ces étapes :

1. Forkez le dépôt.
2. Créez une nouvelle branche (`git checkout -b feature/nom_de_la_fonctionnalité`).
3. Effectuez vos modifications.
4. Commitez vos changements (`git commit -m 'Ajout d'une nouvelle fonctionnalité'`).
5. Poussez à la branche (`git push origin feature/nom_de_la_fonctionnalité`).
6. Créez une Pull Request.

## Licence
Ce projet est sous licence MIT. Voir le fichier [LICENSE](LICENSE) pour plus de détails.
