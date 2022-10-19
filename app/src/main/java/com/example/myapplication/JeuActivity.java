package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class JeuActivity extends AppCompatActivity {
    int tourJoueur = 0;
    String dernierJoueur = "Joueur 2";
    ArrayList<Button> lesBoutons = new ArrayList<>();
    ArrayList<Button> lesBoutonsCliques = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grille);
    }

    public void recupererJoueur() {
        Intent intent = getIntent();
        Joueur joueurUn = (Joueur) intent.getSerializableExtra("JOUEURUN_KEY");
        System.out.println(joueurUn);
    }

    // ----------------- TOUR -----------------

    public void changementTour(View v) {
        if (tourJoueur == 0) recupererLesBoutons(); // Récupèration des boutons lors du premier tour
        cliquerBouton(findViewById(v.getId()));
        tourJoueur++;
        // Vérifie si la grille ne contient pas un scénario gagnant ...
        scenarioGagnant("O");
        scenarioGagnant("X");
        // ... sinon la grille est réinitialiser
        if (verfierGrilleRempli()) {
            reinitialiserGrille();
        }
    }

    // ----------------- BOUTON -----------------

    public void recupererLesBoutons() {
        lesBoutons.add(findViewById(R.id.button1));
        lesBoutons.add(findViewById(R.id.button2));
        lesBoutons.add(findViewById(R.id.button3));
        lesBoutons.add(findViewById(R.id.button4));
        lesBoutons.add(findViewById(R.id.button5));
        lesBoutons.add(findViewById(R.id.button6));
        lesBoutons.add(findViewById(R.id.button7));
        lesBoutons.add(findViewById(R.id.button8));
        lesBoutons.add(findViewById(R.id.button9));

        recupererJoueur();
    }

    public void cliquerBouton(Button button) {
        // Affichage du joueur qui doit jouer
        TextView tvJoueur = findViewById(R.id.tvJoueur);
        tvJoueur.setText("Au tour du " + dernierJoueur);
        // Le bouton cliqué est remplacé par le symbole du joueur
        if (tourJoueur % 2 == 0) {
            button.setText("X");
            dernierJoueur = "Joueur 1";
        } else {
            button.setText("O");
            dernierJoueur = "Joueur 2";
        }
        // Désactive et ajoute le bouton cliqué aux boutons cliqués
        lesBoutonsCliques.add(button);
        button.setEnabled(false);
    }

    // ----------------- SCENARIO -----------------

    public void scenarioGagnant(String element) {
        boolean scenarioGagnant = false;
        // Les 3 premières lignes
        for (int i = 0; i < 9; i += 3) scenario(element, i, (i+1), (i+2));
        // Les 3 premières colonnes
        for (int i = 0; i < 3; i++) scenario(element, i, (i+3), (i+6));
        // Les 2 diagonales
        for (int i = 0; i < 3; i += 2) scenario(element, i, 4, (8-i));
    }

    public void scenario(String element, int num1, int num2, int num3) {
        boolean combinaisonsGagnantes = false;
        boolean condition1 = lesBoutons.get(num1).getText().toString().equals(element);
        boolean condition2 = lesBoutons.get(num2).getText().toString().equals(element);
        boolean condition3 = lesBoutons.get(num3).getText().toString().equals(element);

        if (condition1 && condition2 && condition3) combinaisonsGagnantes = true;
        if (combinaisonsGagnantes) changerPage();
    }

    // ----------------- GRILLE -----------------

    public boolean verfierGrilleRempli() {
        boolean grilleEstRempli = true;
        for (int i = 0; i < lesBoutons.size(); i++) {
            if (lesBoutons.get(i).getText() != "X" && lesBoutons.get(i).getText() != "O") {
                grilleEstRempli = false;
            }
        }
        return grilleEstRempli;
    }

    public void reinitialiserGrille() {
        for (int i = 0; i < lesBoutons.size(); i++) {
            lesBoutons.get(i).setEnabled(true);
            lesBoutons.get(i).setText("-");
        }
    }

    public void changerPage() {
        Intent intent = new Intent(this, VictoireActivity.class);
        intent.putExtra(VictoireActivity.DERNIERJOUEUR_KEY, dernierJoueur);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK + Intent.FLAG_ACTIVITY_NEW_TASK);
        this.startActivity(intent);
    }
}

