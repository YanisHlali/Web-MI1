package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class VictoireActivity extends AppCompatActivity {
    public static final String DERNIERJOUEUR_KEY = "dernierjoueur_key";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.victoire);
    }

    @Override
    protected void onStart()
    {
        super.onStart();
        afficherGagnant();
    }


    public void afficherGagnant() {
        String dernierjoueur = getIntent().getStringExtra(DERNIERJOUEUR_KEY);
        TextView tvGagnant = findViewById(R.id.tvGagnant);
        tvGagnant.setText(dernierjoueur  + " a gagné");
    }
}
