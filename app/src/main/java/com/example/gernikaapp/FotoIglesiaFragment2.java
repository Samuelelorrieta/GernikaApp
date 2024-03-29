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

public class FotoIglesiaFragment2 extends Fragment {
    //Informacion para llegar a la siguiente parte de la gincana
    private final int queFragmentVoy = 3;

    public FotoIglesiaFragment2() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_foto_iglesia2, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //Imagenes error/Acierto
        ImageView tick = view.findViewById(R.id.tick);
        ImageView equis = view.findViewById(R.id.equis);
        ImageView equis2 = view.findViewById(R.id.equis2);
        ImageView equis3 = view.findViewById(R.id.equis3);
        tick.setVisibility(View.INVISIBLE);
        equis.setVisibility(View.INVISIBLE);
        equis2.setVisibility(View.INVISIBLE);
        equis3.setVisibility(View.INVISIBLE);

        Button botonComprobar = view.findViewById(R.id.buttonCom);

        //RadioButton
        RadioButton rbIglesia1 = view.findViewById(R.id.radioButtonIglesia1);
        RadioButton rbIglesia2 = view.findViewById(R.id.radioButtonIglesia2);
        RadioButton rbIglesia3 = view.findViewById(R.id.radioButtonIglesia3);
        RadioButton rbIglesia4 = view.findViewById(R.id.radioButtonIglesia4);

        botonComprobar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rbIglesia1.isChecked()) { //Si la primera opcion es seleccionada
                    equis.setVisibility(View.VISIBLE);
                    equis2.setVisibility(View.INVISIBLE);
                    equis3.setVisibility(View.INVISIBLE);
                } else if (rbIglesia2.isChecked()) {//Si la segunda opcion es seleccionada
                    tick.setVisibility(View.VISIBLE);
                    equis.setVisibility(View.INVISIBLE);
                    equis2.setVisibility(View.INVISIBLE);
                    equis3.setVisibility(View.INVISIBLE);
                    new Thread(new Runnable() { //Cambia de frament
                        @Override
                        public void run() {

                            //Guardar toda la información para el Marker del mapa
                            Bundle bundle = new Bundle();
                            bundle.putInt("queFragmentVoy", queFragmentVoy);

                            try {
                                // Pausa el hilo durante 3 segundos (3000 milisegundos)
                                Thread.sleep(3000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }

                            MapaFragment mapaFragment = new MapaFragment();
                            mapaFragment.setArguments(bundle);

                            getActivity().getSupportFragmentManager().beginTransaction()
                                    .replace(R.id.contenedorFragment, mapaFragment)
                                    .addToBackStack(null)
                                    .commit();
                            // Después de la pausa, puedes realizar más operaciones en este hilo
                        }
                    }).start();
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
