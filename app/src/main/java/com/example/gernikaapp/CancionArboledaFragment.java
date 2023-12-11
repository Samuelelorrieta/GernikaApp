package com.example.gernikaapp;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class CancionArboledaFragment extends Fragment {

    private MediaPlayer mediaPlayer;

    public CancionArboledaFragment() {
        // Constructor vacío requerido
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_cancion_arboleda, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ImageView tick = view.findViewById(R.id.tick);
        ImageView tick2 = view.findViewById(R.id.tick2);
        ImageView tick3 = view.findViewById(R.id.tick3);
        ImageView tick4 = view.findViewById(R.id.tick4);
        ImageView tick5 = view.findViewById(R.id.tick5);
        ImageView tick6 = view.findViewById(R.id.tick6);
        ImageView tick7 = view.findViewById(R.id.tick7);
        ImageView tick8 = view.findViewById(R.id.tick8);
        ImageView tick9 = view.findViewById(R.id.tick9);

        tick.setVisibility(View.INVISIBLE);
        tick2.setVisibility(View.INVISIBLE);
        tick3.setVisibility(View.INVISIBLE);
        tick4.setVisibility(View.INVISIBLE);
        tick5.setVisibility(View.INVISIBLE);
        tick6.setVisibility(View.INVISIBLE);
        tick7.setVisibility(View.INVISIBLE);
        tick8.setVisibility(View.INVISIBLE);
        tick9.setVisibility(View.INVISIBLE);

        ImageView equis1 = view.findViewById(R.id.equis1);
        ImageView equis2 = view.findViewById(R.id.equis2);
        ImageView equis3 = view.findViewById(R.id.equis3);
        ImageView equis4 = view.findViewById(R.id.equis4);
        ImageView equis5 = view.findViewById(R.id.equis5);
        ImageView equis6 = view.findViewById(R.id.equis6);
        ImageView equis7 = view.findViewById(R.id.equis7);
        ImageView equis8 = view.findViewById(R.id.equis8);
        ImageView equis9 = view.findViewById(R.id.equis9);

        equis1.setVisibility(View.INVISIBLE);
        equis2.setVisibility(View.INVISIBLE);
        equis3.setVisibility(View.INVISIBLE);
        equis4.setVisibility(View.INVISIBLE);
        equis5.setVisibility(View.INVISIBLE);
        equis6.setVisibility(View.INVISIBLE);
        equis7.setVisibility(View.INVISIBLE);
        equis8.setVisibility(View.INVISIBLE);
        equis9.setVisibility(View.INVISIBLE);

        EditText palabra1 = view.findViewById(R.id.editText1);
        EditText palabra2 = view.findViewById(R.id.editText2);
        EditText palabra3 = view.findViewById(R.id.editText3);
        EditText palabra4 = view.findViewById(R.id.editText4);
        EditText palabra5 = view.findViewById(R.id.editText5);
        EditText palabra6 = view.findViewById(R.id.editText6);
        EditText palabra7 = view.findViewById(R.id.editText7);
        EditText palabra8 = view.findViewById(R.id.editText8);
        EditText palabra9 = view.findViewById(R.id.editText9);

        Button boton = view.findViewById(R.id.buttonComprobar);

        mediaPlayer = MediaPlayer.create(requireContext(), R.raw.gernikakoarbola);

        boton.setOnClickListener(v -> {
            if (palabra1.getText().toString().equals("maitatua")) {
                tick.setVisibility(View.VISIBLE);
                equis1.setVisibility(View.INVISIBLE);
                palabra1.setEnabled(false);
            } else {
                palabra1.setText("");
                equis1.setVisibility(View.VISIBLE);
            }

            if (palabra2.getText().toString().equals("arbola")) {
                tick2.setVisibility(View.VISIBLE);
                equis2.setVisibility(View.INVISIBLE);
                palabra2.setEnabled(false);
            } else {
                palabra2.setText("");
                equis2.setVisibility(View.VISIBLE);
            }

            if (palabra3.getText().toString().equals("Gernikako")) {
                tick3.setVisibility(View.VISIBLE);
                equis3.setVisibility(View.INVISIBLE);
                palabra3.setEnabled(false);
            } else {
                palabra3.setText("");
                equis3.setVisibility(View.VISIBLE);
            }

            if (palabra4.getText().toString().equals("galdu")) {
                tick4.setVisibility(View.VISIBLE);
                equis4.setVisibility(View.INVISIBLE);
                palabra4.setEnabled(false);
            } else {
                palabra4.setText("");
                equis4.setVisibility(View.VISIBLE);
            }

            if (palabra5.getText().toString().equals("zurekin")) {
                tick5.setVisibility(View.VISIBLE);
                equis5.setVisibility(View.INVISIBLE);
                palabra5.setEnabled(false);
            } else {
                palabra5.setText("");
                equis5.setVisibility(View.VISIBLE);
            }

            if (palabra6.getText().toString().equals("bihotzetikan")) {
                tick6.setVisibility(View.VISIBLE);
                equis6.setVisibility(View.INVISIBLE);
                palabra6.setEnabled(false);
            } else {
                palabra6.setText("");
                equis6.setVisibility(View.VISIBLE);
            }

            if (palabra7.getText().toString().equals("jendia")) {
                tick7.setVisibility(View.VISIBLE);
                equis7.setVisibility(View.INVISIBLE);
                palabra7.setEnabled(false);
            } else {
                palabra7.setText("");
                equis7.setVisibility(View.VISIBLE);
            }

            if (palabra8.getText().toString().equals("emanik")) {
                tick8.setVisibility(View.VISIBLE);
                equis8.setVisibility(View.INVISIBLE);
                palabra8.setEnabled(false);
            } else {
                palabra8.setText("");
                equis8.setVisibility(View.VISIBLE);
            }

            if (palabra9.getText().toString().equals("zuzenak")) {
                tick9.setVisibility(View.VISIBLE);
                equis9.setVisibility(View.INVISIBLE);
                palabra9.setEnabled(false);
            } else {
                palabra9.setText("");
                equis9.setVisibility(View.VISIBLE);
            }

            if (tick.getVisibility() == View.VISIBLE && tick2.getVisibility() == View.VISIBLE && tick3.getVisibility() == View.VISIBLE
                    && tick4.getVisibility() == View.VISIBLE && tick5.getVisibility() == View.VISIBLE && tick6.getVisibility() == View.VISIBLE
                    && tick7.getVisibility() == View.VISIBLE && tick8.getVisibility() == View.VISIBLE && tick9.getVisibility() == View.VISIBLE) {
                new Handler().postDelayed(() -> {

                    TestFragment testFragment = new TestFragment();
                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
                    transaction.replace(R.id.contenedorFragment, testFragment); // Reemplaza el fragmento actual con FragmentB
                    transaction.commit();

                    mediaPlayer.stop();
                }, 3000);
            }
        });
    }
}
