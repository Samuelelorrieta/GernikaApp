package com.example.gernikaapp;

import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import java.io.IOException;

public class Zitadela extends AppCompatActivity {

    Button buttonJuego;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zitadela);
        buttonJuego = findViewById(R.id.button2);

        //Iniciar el juego
        Button buttonJuego = findViewById(R.id.button2);


        buttonJuego.setOnClickListener(v -> {
                    AssetManager am = getAssets();
                    try {
                        final String[] files = am.list("img/");
                        if (files != null) {
                            Intent intent = new Intent(getApplicationContext(), PuzzleMain.class);
                            intent.putExtra("assetName", files[0]);
                            startActivity(intent);
                        }
                    } catch (IOException e) {
                        Toast.makeText(this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
        );



    }
}