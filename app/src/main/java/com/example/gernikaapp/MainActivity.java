package com.example.gernikaapp;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.example.gernikaapp.Modelo.Ubicaciones;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Ubicaciones ubicaciones = new Ubicaciones();
    private double[] lat = new double[]{43.31502960219348, 43.3120758198961, 43.30888911048448, 43.31323892537951, 43.3130240623675};
    private double[] lon = new double[]{-2.6785832178308917, -2.676278594468019, -2.6833587603397713, -2.6795759699238295, -2.679950266821695};
    private String[]  nombreUbicacion = new String[]{"Museo de la Paz de Gernika", "Bunker", "Iglesia San Francisco", "Casa de Juntas", "Arbol de Gernika"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Obtener la referencia al TextView
        TextView textView = findViewById(R.id.textView);

        // Crear un ObjectAnimator para la propiedad "alpha" (transparencia)
        ObjectAnimator animator = ObjectAnimator.ofFloat(textView, "alpha", 0f, 1f);
        animator.setDuration(5000); // Duración de la animación en milisegundos
        animator.setInterpolator(new AccelerateDecelerateInterpolator()); // Interpolador para suavizar la animación

        // Iniciar la animación
        animator.start();

        //Meter todas las ubicaciones en el Objeto Ubicaciones
        ArrayList<Ubicaciones> listaUbicaciones = new ArrayList<Ubicaciones>();
        for(int i = 0; i < lat.length; i++){
            ubicaciones.setLat(lat[i]);
            ubicaciones.setLon(lon[i]);
            ubicaciones.setNombreUbicacion(nombreUbicacion[i]);

            listaUbicaciones.add(ubicaciones);
        }

        Button botonContinuar=findViewById(R.id.button);
        Intent irPuzzle = new Intent(this, Actividades.class);
        botonContinuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(irPuzzle);
            }
        });
    }
}
