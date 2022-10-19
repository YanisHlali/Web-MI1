package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.io.Serializable;

public class JoueurActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.joueur);
    }

    public void commencerLeJeu(View v) {
        EditText etJoueurUn = findViewById(R.id.etJoueurUn);
        String joueurUnNom = etJoueurUn.getText().toString();

        EditText etJoueurDeux = findViewById(R.id.etJoueurDeux);
        String joueurDeuxNom = etJoueurDeux.getText().toString();

        Joueur joueurUn = new Joueur(joueurUnNom);
        Joueur joueurDeux = new Joueur(joueurDeuxNom);

        Intent intent = new Intent(this, JeuActivity.class);
        intent.putExtra("JOUEURUN_KEY", joueurUn);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK + Intent.FLAG_ACTIVITY_NEW_TASK);
        this.startActivity(intent);
    }
}
