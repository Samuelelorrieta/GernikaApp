package com.example.gernikaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.TextView;

public class CancionArboleda extends AppCompatActivity {
    private MediaPlayer mediaPlayer;
    @Override
    //Hace que suene la canción 3 veces
    protected void onStart() {
        super.onStart();
        for(int i=0;i<3;i++) {
            mediaPlayer.start();
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cancion_arboleda);

        //TextView textView = findViewById(R.id.textView2);

        //Activar Sonido
        mediaPlayer=MediaPlayer.create(this, R.raw.gernikakoarbola);
    }
}