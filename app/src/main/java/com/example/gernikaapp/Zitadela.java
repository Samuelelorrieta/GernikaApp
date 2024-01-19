package com.example.gernikaapp;

import android.content.Intent;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.content.res.AssetManager;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.SeekBar;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;


import com.example.gernikaapp.PuzzleMain;
import com.example.gernikaapp.R;

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