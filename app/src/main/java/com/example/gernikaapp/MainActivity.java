package com.example.gernikaapp;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

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
    }
}
