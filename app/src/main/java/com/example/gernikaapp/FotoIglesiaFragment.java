package com.example.gernikaapp;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FotoIglesiaFragment extends Fragment {

    public FotoIglesiaFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_foto_iglesia, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ImageView tick = view.findViewById(R.id.tick);
        ImageView equis = view.findViewById(R.id.equis);
        ImageView equis2 = view.findViewById(R.id.equis2);
        ImageView equis3 = view.findViewById(R.id.equis3);
        tick.setVisibility(View.INVISIBLE);
        equis.setVisibility(View.INVISIBLE);
        equis2.setVisibility(View.INVISIBLE);
        equis3.setVisibility(View.INVISIBLE);

        Button botonComprobar = view.findViewById(R.id.buttonCom);
        RadioButton rbIglesia1 = view.findViewById(R.id.radioButtonIglesia1);
        RadioButton rbIglesia2 = view.findViewById(R.id.radioButtonIglesia2);
        RadioButton rbIglesia3 = view.findViewById(R.id.radioButtonIglesia3);
        RadioButton rbIglesia4 = view.findViewById(R.id.radioButtonIglesia4);

        botonComprobar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rbIglesia1.isChecked()) {
                    equis.setVisibility(View.VISIBLE);
                    equis2.setVisibility(View.INVISIBLE);
                    equis3.setVisibility(View.INVISIBLE);
                } else if (rbIglesia2.isChecked()) {
                    tick.setVisibility(View.VISIBLE);
                    equis.setVisibility(View.INVISIBLE);
                    equis2.setVisibility(View.INVISIBLE);
                    equis3.setVisibility(View.INVISIBLE);
                } else if (rbIglesia3.isChecked()) {
                    equis2.setVisibility(View.VISIBLE);
                    equis.setVisibility(View.INVISIBLE);
                    equis3.setVisibility(View.INVISIBLE);
                } else if (rbIglesia4.isChecked()) {
                    equis3.setVisibility(View.VISIBLE);
                    equis.setVisibility(View.INVISIBLE);
                    equis2.setVisibility(View.INVISIBLE);
                }
            }
        });
    }
}
