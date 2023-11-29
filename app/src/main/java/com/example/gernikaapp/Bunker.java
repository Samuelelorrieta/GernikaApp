package com.example.gernikaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.logging.Handler;
import java.util.logging.LogRecord;

public class Bunker extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bunker);
        String respuestaCorrecta= getString(R.string.respuesta);
        TextView error = findViewById(R.id.error);
        String mensajeError= getString(R.string.mensajeError);
        String mensajeAcierto= getString(R.string.mensajeAcierto);

        EditText respuesta = findViewById(R.id.respuesta);
        Button boton = findViewById(R.id.boton);

        boton.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {

                if(respuesta.getText().toString().equals(respuestaCorrecta))
                {
                    error.setTextColor(R.color.black);
                    error.setText(mensajeAcierto);
                    Intent irBunker = new Intent(Bunker.this, FotoIglesia.class);
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
                }
                else
                {
                    error.setText(mensajeError);
                    respuesta.setText("");
                }
            }
        });
    }
}