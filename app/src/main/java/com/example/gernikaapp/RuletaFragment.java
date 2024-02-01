package com.example.gernikaapp;

import android.content.res.Resources;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
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
import androidx.room.Dao;
import androidx.room.Room;

import com.example.gernikaapp.BD.AppDatabase;
import com.example.gernikaapp.BD.DaoFigura;
import com.example.gernikaapp.BD.JuegoRuleta.DaoMunicipio;
import com.example.gernikaapp.BD.JuegoRuleta.Municipio;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class RuletaFragment extends Fragment {

    ImageView ruleta, flecha;
    TextView lbl_Letra_Seleccionada;
    EditText txt_Pueblo1, txt_Pueblo2, txt_Pueblo3;
    Button btn_Comprobar;
    int rotacion = 0, velocidadRotacion = 21;
    double[] posicionParada = {360, 17.14, 34.29, 51.43, 68.57, 85.71, 102.86, 120, 137.14, 154.29, 171.43, 188.57, 205.71, 222.86, 240, 257.14, 274.29, 291.43, 308.57, 325.71, 342.86};
    String[] letrasRuleta = {"A", "B", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "R", "S", "T", "U", "X", "Z"};
    int posicionRadianes = 0;
    boolean gira = false;
    private MediaPlayer mediaPlayer;

    //Informacion para llegar a la siguiente parte de la gincana
    private final int queFragmentVoy = 5;
    //----------------//

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

        //Invisible los Tiks
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
                if (obtenerMunicipiosPorLetra() == true) {
                    //Guardar toda la información para el Marker del mapa
                    Bundle bundle = new Bundle();
                    bundle.putInt("queFragmentVoy", queFragmentVoy);

                    MapaFragment mapaFragment = new MapaFragment();
                    mapaFragment.setArguments(bundle);

                    getActivity().getSupportFragmentManager().beginTransaction()
                            .replace(R.id.contenedorFragment, mapaFragment)
                            .addToBackStack(null)
                            .commit();
                }else{
                    Toast.makeText(requireContext(), "Revisa que los pueblos existen o que esten bien escritos", Toast.LENGTH_SHORT).show();
                }
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

                obtenerMunicipiosPorLetra();

            } else {
                startSpin();
            }
        }, 1);
    }


    private boolean obtenerMunicipiosPorLetra() {

        boolean todoCorrecto = false;

        // Obtener los textos de los TextView en un array
        String[] pueblos = {
                txt_Pueblo1.getText().toString(),
                txt_Pueblo2.getText().toString(),
                txt_Pueblo3.getText().toString()
        };

        if (!pueblos[0].equalsIgnoreCase(pueblos[1]) && !pueblos[0].equalsIgnoreCase(pueblos[2]) &&
                !pueblos[1].equalsIgnoreCase(pueblos[2])) {

            // Reinicia la validación en cada iteración
            boolean pueblo1PuebloValidado = false;
            boolean pueblo2PuebloValidado = false;
            boolean pueblo3PuebloValidado = false;

            //Cración de BD
            AppDatabase db = Room.databaseBuilder(
                            getContext().getApplicationContext(),
                            AppDatabase.class,
                            "Gernika")
                    .allowMainThreadQueries().build();

            DaoMunicipio dao = db.daoMunicipio();

            List<Municipio> municipios = (List<Municipio>) dao.obtenerMunicipioPorIdLetra(posicionRadianes + 1);

            for (Municipio municipio : municipios) {
                System.out.println(municipio.nombre + " " + municipio.idLetra);

                if (pueblos[0].equalsIgnoreCase(municipio.nombre) && !pueblo1PuebloValidado) {
                    pueblo1PuebloValidado = true;
                }

                if (pueblos[1].equalsIgnoreCase(municipio.nombre) && !pueblo2PuebloValidado) {
                    pueblo2PuebloValidado = true;
                }

                if (pueblos[2].equalsIgnoreCase(municipio.nombre) && !pueblo3PuebloValidado) {
                    pueblo3PuebloValidado = true;
                }

                // Verifica que todos los pueblos estén validados
                if (pueblo1PuebloValidado && pueblo2PuebloValidado && pueblo3PuebloValidado) {
                    todoCorrecto = true;
                }
            }
        } else {
            Toast.makeText(requireContext(), "Esta prohibido usar repetir nombres", Toast.LENGTH_SHORT).show();
        }
        return todoCorrecto;
    }


}
