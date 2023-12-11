package com.example.gernikaapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class BunkerFragment extends Fragment {

    public BunkerFragment() {
        // Constructor vacío requerido por Android
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bunker, container, false);

        String respuestaCorrecta = getString(R.string.respuesta);
        TextView error = view.findViewById(R.id.error);
        String mensajeError = getString(R.string.mensajeError);
        String mensajeAcierto = getString(R.string.mensajeAcierto);

        EditText respuesta = view.findViewById(R.id.respuesta);
        Button boton = view.findViewById(R.id.boton);

        boton.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                if (respuesta.getText().toString().equals(respuestaCorrecta)) {
                    error.setTextColor(R.color.black);
                    error.setText(mensajeAcierto);
                    Intent irBunker = new Intent(getActivity(), FotoIglesia.class);
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                // Pausa el hilo durante 3 segundos (3000 milisegundos)
                                Thread.sleep(3000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            startActivity(irBunker);
                            // Después de la pausa, puedes realizar más operaciones en este hilo
                        }
                    }).start();
                } else {
                    error.setText(mensajeError);
                    respuesta.setText("");
                }
            }
        });

        return view;
    }
}
