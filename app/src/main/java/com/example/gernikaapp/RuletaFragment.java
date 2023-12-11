package com.example.gernikaapp;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import java.util.Random;

public class RuletaFragment extends Fragment {

    ImageView ruleta, flecha;
    TextView lbl_Letra_Seleccionada;
    EditText txt_Pueblo1, txt_Pueblo2, txt_Pueblo3;
    Button btn_Comprobar;
    int rotacion = 0, velocidadRotacion = 21;
    double[] posicionParada = {17.14, 34.29, 51.43, 68.57, 85.71, 102.86, 120, 137.14, 154.29, 171.43, 188.57, 205.71, 222.86, 240, 257.14, 274.29, 291.43, 308.57, 325.71, 342.86, 360};
    String[] letrasRuleta = {"R", "S", "T", "U", "X", "Z", "A", "B", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P"};
    int posicionRadianes = 0;
    boolean gira = false;
    private MediaPlayer mediaPlayer;

    public RuletaFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_ruleta, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ruleta = view.findViewById(R.id.wheel);
        flecha = view.findViewById(R.id.arrow);
        lbl_Letra_Seleccionada = view.findViewById(R.id.lbl_Letra_Seleccionada);
        txt_Pueblo1 = view.findViewById(R.id.txt_Pueblo1);
        txt_Pueblo2 = view.findViewById(R.id.txt_Pueblo2);
        txt_Pueblo3 = view.findViewById(R.id.txt_Pueblo3);
        btn_Comprobar = view.findViewById(R.id.btn_Comprobar);

        mediaPlayer = MediaPlayer.create(requireContext(), R.raw.ruletasound);

        btn_Comprobar.setVisibility(View.INVISIBLE);

        flecha.setOnClickListener(v -> {
            if (!gira) {
                posicionRadianes = new Random().nextInt(21);
                gira = true;
                mediaPlayer.start();
                startSpin();
            }
        });

        btn_Comprobar.setOnClickListener(v -> {
            if (txt_Pueblo1.getText().toString().isEmpty() || txt_Pueblo2.getText().toString().isEmpty() || txt_Pueblo3.getText().toString().isEmpty()) {
                Toast.makeText(requireContext(), "Rellena todos los campos", Toast.LENGTH_SHORT).show();
            } else {
                CancionArboledaFragment cancionArboledaFragment = new CancionArboledaFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.contenedorFragment, cancionArboledaFragment); // Reemplaza el fragmento actual con FragmentB
                transaction.commit();
            }
        });
    }

    public void startSpin() {
        new Handler().postDelayed(() -> {
            ruleta.setRotation(rotacion);

            if (rotacion >= 21) {
                velocidadRotacion = 5;
            }
            if (rotacion >= 75) {
                velocidadRotacion = 4;
            }
            if (rotacion >= 125) {
                velocidadRotacion = 2;
            }

            rotacion = rotacion + velocidadRotacion;

            if (rotacion >= posicionParada[posicionRadianes]) {
                lbl_Letra_Seleccionada.setText(letrasRuleta[posicionRadianes]);
                mediaPlayer.stop();
                txt_Pueblo1.setEnabled(true);
                txt_Pueblo2.setEnabled(true);
                txt_Pueblo3.setEnabled(true);
                btn_Comprobar.setVisibility(View.VISIBLE);
            } else {
                startSpin();
            }
        }, 1);
    }
}
