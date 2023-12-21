package com.example.gernikaapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.concurrent.Executor;

public class inicioSesion extends Fragment {
    private FirebaseAuth mAuth;
    public static inicioSesion newInstance() {
        inicioSesion fragment = new inicioSesion();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseApp.initializeApp(this.getContext());

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_inicio_sesion, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Button iniciarSesion = view.findViewById(R.id.inicioSesion);
        Button registrarse = view.findViewById(R.id.registro1);
        mAuth=FirebaseAuth.getInstance();
        EditText nombre = view.findViewById(R.id.nombre);
        EditText contra = view.findViewById(R.id.contra);

        Boolean check = view.findViewById(R.id.bool).isActivated();

        iniciarSesion.setOnClickListener(v -> {
            iniciarSesion(nombre,contra,mAuth);

        });
        registrarse.setOnClickListener(v -> {
            registrarUsuario(nombre,contra,mAuth);
        });
    }
    private void registrarUsuario(EditText correo, EditText contraseña, FirebaseAuth mAuth) {
        try{
            mAuth.createUserWithEmailAndPassword(correo.getText().toString(), contraseña.getText().toString())
                    .addOnCompleteListener((Executor) this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                MapaFragment MapaFragment = new MapaFragment();
                                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                                transaction.replace(R.id.contenedorFragment, MapaFragment);
                                transaction.commit();
                            } else {
                                // Hubo un error al crear la cuenta
                                // Muestra un mensaje de error o realiza alguna acción de manejo de errores
                            }
                        }
                    });
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    private void iniciarSesion(EditText correo, EditText contraseña, FirebaseAuth mAuth){
        mAuth.signInWithEmailAndPassword(correo.getText().toString(), contraseña.getText().toString())
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {

                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            MapaFragment MapaFragment = new MapaFragment();
                            FragmentTransaction transaction = getFragmentManager().beginTransaction();
                            transaction.replace(R.id.contenedorFragment, MapaFragment);
                            transaction.commit();
                        }
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    // Pausa el hilo durante 3 segundos (3000 milisegundos)
                                    Thread.sleep(3000);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                        }).start();
                    }
                });
    }
}


