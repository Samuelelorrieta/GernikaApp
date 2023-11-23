package com.example.gernikaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Configuration;
import android.os.Bundle;

public class Bunker extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bunker);

    }
    public void cambiarIdioma(String idioma){
        Configuration configuration = new Configuration(); // Crea la configuracion
        configuration.setLocale(new java.util.Locale(idioma)); // Cambia los recursos
        getResources().updateConfiguration(configuration, getResources().getDisplayMetrics()); // Actualiza los recursos
    }
}