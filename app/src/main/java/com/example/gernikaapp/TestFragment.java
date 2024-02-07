package com.example.gernikaapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class TestFragment extends Fragment {

    public TestFragment() {
        // Constructor vacío requerido
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_test, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button boton = view.findViewById(R.id.botonTest);
        TextView error = view.findViewById(R.id.errorTest);
        error.setVisibility(View.INVISIBLE);

        //RadioButtons
        RadioButton test1 = view.findViewById(R.id.radio11);
        RadioButton test2 = view.findViewById(R.id.radio21);
        RadioButton test3 = view.findViewById(R.id.radio31);

        //Imagenes
        ImageView tick1 = view.findViewById(R.id.tick1);
        tick1.setVisibility(View.INVISIBLE);
        ImageView tick2 = view.findViewById(R.id.tick2);
        tick2.setVisibility(View.INVISIBLE);
        ImageView tick3 = view.findViewById(R.id.tick3);
        tick3.setVisibility(View.INVISIBLE);
        ImageView cross1 = view.findViewById(R.id.cross1);
        cross1.setVisibility(View.INVISIBLE);
        ImageView cross2 = view.findViewById(R.id.cross2);
        cross2.setVisibility(View.INVISIBLE);
        ImageView cross3 = view.findViewById(R.id.cross3);
        cross3.setVisibility(View.INVISIBLE);

        boton.setOnClickListener(v -> {
            if (test1.isChecked()) { //Si la respuest correcta esta seleccionada
                tick1.setVisibility(View.VISIBLE); //Indica que la respuesta es correcta
                cross1.setVisibility(View.INVISIBLE);
            } else {
                cross1.setVisibility(View.VISIBLE); //Indica que la respuesta es incorrecta
                tick1.setVisibility(View.INVISIBLE);
            }

            if (test2.isChecked()) { //Si la respuest correcta esta seleccionada
                tick2.setVisibility(View.VISIBLE);//Indica que la respuesta es correcta
                cross2.setVisibility(View.INVISIBLE);
            } else {
                cross2.setVisibility(View.VISIBLE);//Indica que la respuesta es incorrecta
                tick2.setVisibility(View.INVISIBLE);
            }

            if (test3.isChecked()) { //Si la respuest correcta esta seleccionada
                tick3.setVisibility(View.VISIBLE);//Indica que la respuesta es correcta
                cross3.setVisibility(View.INVISIBLE);
            } else {
                cross3.setVisibility(View.VISIBLE);//Indica que la respuesta es incorrecta
                tick3.setVisibility(View.INVISIBLE);
            }

            if (test1.isChecked() && test2.isChecked() && test3.isChecked()) { //Si las tres respuestas correctas estan seleccionadas
                error.setVisibility(View.VISIBLE);
                Intent irBunker = new Intent(requireContext(), MainActivity.class);
                new Handler().postDelayed(() -> {
                    startActivity(irBunker);
                    // Puedes realizar más operaciones después de la pausa en este hilo
                }, 3000);
            }
        });
    }
}
