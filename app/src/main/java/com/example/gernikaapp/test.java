package com.example.gernikaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

public class test extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        Button boton= findViewById(R.id.botonTest);
        TextView error =findViewById(R.id.errorTest);
        error.setVisibility(View.INVISIBLE);

        RadioButton test1 = findViewById(R.id.radio11);
        RadioButton test2 = findViewById(R.id.radio21);
        RadioButton test3 = findViewById(R.id.radio31);

        ImageView tick1 =findViewById(R.id.tick1);
        tick1.setVisibility(View.INVISIBLE);
        ImageView tick2 =findViewById(R.id.tick1);
        tick2.setVisibility(View.INVISIBLE);
        ImageView tick3 =findViewById(R.id.tick1);
        tick3.setVisibility(View.INVISIBLE);
        ImageView cross1 =findViewById(R.id.tick1);
        cross1.setVisibility(View.INVISIBLE);
        ImageView cross2 =findViewById(R.id.tick1);
        cross2.setVisibility(View.INVISIBLE);
        ImageView cross3 =findViewById(R.id.tick1);
        cross3.setVisibility(View.INVISIBLE);

        Intent main = new Intent(this, MainActivity.class);

        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            if(test1.isActivated()) {
                tick1.setVisibility(View.VISIBLE);
                cross1.setVisibility(View.INVISIBLE);
            }
            else {
                cross1.setVisibility(View.VISIBLE);
                tick1.setVisibility(View.INVISIBLE);
            }

            if(test2.isActivated()) {
                tick2.setVisibility(View.VISIBLE);
                cross2.setVisibility(View.INVISIBLE);
            }
            else {
                cross2.setVisibility(View.VISIBLE);
                tick2.setVisibility(View.INVISIBLE);
            }

            if(test3.isActivated()) {
                tick3.setVisibility(View.VISIBLE);
                cross3.setVisibility(View.INVISIBLE);
            }
            else {
                cross3.setVisibility(View.VISIBLE);
                tick3.setVisibility(View.INVISIBLE);
            }
            if(test1.isActivated()&&test2.isActivated()&&test3.isActivated())
            {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            error.setVisibility(View.VISIBLE);
                            // Pausa el hilo durante 3 segundos (3000 milisegundos)
                            Thread.sleep(3000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        startActivity(main);
                        // Después de la pausa, puedes realizar más operaciones en este hilo
                    }
                }).start();
            }
            }
        });




    }
}