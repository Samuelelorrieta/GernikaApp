package com.example.gernikaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

public class Test extends AppCompatActivity {

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
        ImageView tick2 =findViewById(R.id.tick2);
        tick2.setVisibility(View.INVISIBLE);
        ImageView tick3 =findViewById(R.id.tick3);
        tick3.setVisibility(View.INVISIBLE);
        ImageView cross1 =findViewById(R.id.cross1);
        cross1.setVisibility(View.INVISIBLE);
        ImageView cross2 =findViewById(R.id.cross2);
        cross2.setVisibility(View.INVISIBLE);
        ImageView cross3 =findViewById(R.id.cross3);
        cross3.setVisibility(View.INVISIBLE);



        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            if(test1.isChecked()) {
                tick1.setVisibility(View.VISIBLE);
                cross1.setVisibility(View.INVISIBLE);
            }
            else {
                cross1.setVisibility(View.VISIBLE);
                tick1.setVisibility(View.INVISIBLE);
            }

            if(test2.isChecked()) {
                tick2.setVisibility(View.VISIBLE);
                cross2.setVisibility(View.INVISIBLE);
            }
            else {
                cross2.setVisibility(View.VISIBLE);
                tick2.setVisibility(View.INVISIBLE);
            }

            if(test3.isChecked()) {
                tick3.setVisibility(View.VISIBLE);
                cross3.setVisibility(View.INVISIBLE);
            }
            else {
                cross3.setVisibility(View.VISIBLE);
                tick3.setVisibility(View.INVISIBLE);
            }
            if(test1.isChecked()&&test2.isChecked()&&test3.isChecked())
            {
                error.setVisibility(View.VISIBLE);
                Intent irBunker = new Intent(Test.this, MainActivity.class);
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
            }
        });




    }
}