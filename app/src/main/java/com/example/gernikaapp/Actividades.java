package com.example.gernikaapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

public class Actividades extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividades);
        // Obtén el FragmentManager
        FragmentManager fragmentManager = getSupportFragmentManager();

        // Inicia una transacción
        FragmentTransaction transaction = fragmentManager.beginTransaction();

        // Reemplaza el contenido del contenedor con el Fragment
        transaction.replace(R.id.contenedorFragment, new MapaFragment());

        // Confirma la transacción
        transaction.commit();
    }
}