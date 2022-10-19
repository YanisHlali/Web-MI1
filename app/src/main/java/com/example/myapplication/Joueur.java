package com.example.myapplication;

import java.io.Serializable;

public class Joueur  implements Serializable {
    String nom;
    int partieGagne;

    public Joueur(String nom) {
        this.nom = nom;
        partieGagne = 0;
    }

    public void ajouterPartieGagne() {
        partieGagne++;
    }

    public void modifierNom(String nom) {
        this.nom = nom;
    }
}
