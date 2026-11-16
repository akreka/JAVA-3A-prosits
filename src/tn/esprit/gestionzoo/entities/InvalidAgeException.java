package tn.esprit.gestionzoo.entities;

public class InvalidAgeException extends Exception {

    public InvalidAgeException() {
        super("Âge d'animal invalide : l'âge ne peut pas être négatif.");
    }
}