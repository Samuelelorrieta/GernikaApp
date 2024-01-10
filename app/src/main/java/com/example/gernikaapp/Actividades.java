package com.example.gernikaapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.KeyEvent;

import com.example.gernikaapp.Modelo.Ubicaciones;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class Actividades extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private  ArrayList<Ubicaciones> listaUbicaciones;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividades);
        mAuth = FirebaseAuth.getInstance();
        // Obtén el FragmentManager
        FragmentManager fragmentManager = getSupportFragmentManager();

        // Inicia una transacción
        FragmentTransaction transaction = fragmentManager.beginTransaction();

        // Reemplaza el contenido del contenedor con el Fragment
        transaction.replace(R.id.contenedorFragment, new MapaFragment());

        // Confirma la transacción
        transaction.commit();

        //Recogida de Ubicaciones ya cargadas en el ArrayList
        listaUbicaciones = getIntent().getParcelableArrayListExtra("clave_listaUbicaciones");

    }

    public ArrayList<Ubicaciones> obtenerArrayList() {
        return listaUbicaciones;
    }
    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
            // Bloquear el botón de retrocesos
            return true;
        }
        return super.dispatchKeyEvent(event);
    }
}