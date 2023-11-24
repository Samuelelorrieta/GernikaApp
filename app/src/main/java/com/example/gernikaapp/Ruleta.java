package com.example.gernikaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class Ruleta extends AppCompatActivity {

    //Ruleta que gira y que saca una letra del abecedario Basco. Una vez que se le da se bloquea la opción de giro de la ruleta y se habilitan los 3 EditText y el boton de comprobacion

    ImageView ruleta, flecha;
    TextView lbl_Letra_Seleccionada;
    EditText txt_Pueblo1;
    EditText txt_Pueblo2;
    EditText txt_Pueblo3;
    Button btn_Comprobar;
    int rotacion = 0, velocidadRotacion = 21;
    double[] posicionParada = {17.14,34.29,51.43,68.57,85.71,102.86,120,137.14,154.29,171.43,188.57,205.71,222.86,240,257.14,274.29,291.43,308.57,325.71,342.86,360};
    String[] letrasRuleta = {"R", "S", "T", "U", "X", "Z", "A", "B", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P"};
    int posicionRadianes = 0;
    boolean gira = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ruleta);

        Intent irCancionArboleda = new Intent(this, CancionArboleda.class);

        //Junta xml con Java
        ruleta = findViewById(R.id.wheel);
        flecha = findViewById(R.id.arrow);
        lbl_Letra_Seleccionada = findViewById(R.id.lbl_Letra_Seleccionada);
        txt_Pueblo1 = findViewById(R.id.txt_Pueblo1);
        txt_Pueblo2 = findViewById(R.id.txt_Pueblo2);
        txt_Pueblo3 = findViewById(R.id.txt_Pueblo3);
        btn_Comprobar = findViewById(R.id.btn_Comprobar);

        //Boton invisible para que no se vea
        btn_Comprobar.setVisibility(View.INVISIBLE);

        //Cuadno se le de a la imagen que gire la ruleta
        flecha.setOnClickListener(view -> {
            //Valida que ya se le aya dado o no
            if (!gira) {
                posicionRadianes = new Random().nextInt(21);
                gira = true;
                startSpin(irCancionArboleda);
            }
        });
    }

    //Metodo para que gire la ruleta y saque el resultado
    public void startSpin(Intent irCancionArboleda) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                ruleta.setRotation(rotacion);
                System.out.println(rotacion);

                // Controlador de velocidad de la ruleta
                if (rotacion >= 21) {
                    //Mas lento
                    velocidadRotacion = 5;
                }
                if (rotacion >= 75) {
                    //Mas lento
                    velocidadRotacion = 4;
                }
                if (rotacion >= 125) {
                    //Mas lento
                    velocidadRotacion = 2;
                }

                rotacion = rotacion + velocidadRotacion;

                if (rotacion >= posicionParada[posicionRadianes]) {
                    //Se para la ruleta y se activan los EditText y el boton
                    lbl_Letra_Seleccionada.setText(letrasRuleta[posicionRadianes]);
                    txt_Pueblo1.setEnabled(true);
                    txt_Pueblo2.setEnabled(true);
                    txt_Pueblo3.setEnabled(true);
                    btn_Comprobar.setVisibility(View.VISIBLE);
                } else {
                    startSpin(irCancionArboleda);
                }
            }
        }, 1); // This timer will run every one milliseconds

        //Boton para validar que los EditText esten bien
        btn_Comprobar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //En caso de que algun campo este vacio salta un error
                if(txt_Pueblo1.getText().toString().isEmpty() || txt_Pueblo2.getText().toString().isEmpty() || txt_Pueblo3.getText().toString().isEmpty()){
                    Toast.makeText(Ruleta.this, "Rellena todos los campos",
                            Toast.LENGTH_SHORT).show();
                }else{
                    startActivity(irCancionArboleda);
                }
            }
        });
    }
}
