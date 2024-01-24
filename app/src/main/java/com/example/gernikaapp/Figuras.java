package com.example.gernikaapp;

import android.content.res.Configuration;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.room.Room;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gernikaapp.BD.AppDatabase;
import com.example.gernikaapp.BD.DaoFigura;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Figuras#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Figuras extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Figuras() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Figuras.
     */
    // TODO: Rename and change types and number of parameters
    public static Figuras newInstance(String param1, String param2) {
        Figuras fragment = new Figuras();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        TextView toro =view.findViewById(R.id.toro);
        TextView Guerrero =view.findViewById(R.id.guerrero);
        TextView Caballo =view.findViewById(R.id.caballo);
        TextView Paloma =view.findViewById(R.id.paloma);
        TextView Madre =view.findViewById(R.id.madre);
        Button continuar = view.findViewById(R.id.continuarFiguras);

        TextView titulo =view.findViewById(R.id.Titulo);
        titulo.setVisibility(View.INVISIBLE);
        TextView Descripcion =view.findViewById(R.id.textoExplicacion);
        Descripcion.setVisibility(View.INVISIBLE);
        ImageView imagen =view.findViewById(R.id.imagenFigura);
        imagen.setVisibility(View.INVISIBLE);

        toro.setOnClickListener(v -> {
            cambiarFragment();
                });

        toro.setOnClickListener(v -> {
            rellenarDatos(titulo,Descripcion,imagen,"Toro");
            imagen.setImageResource(R.drawable.toro);
        });
        Guerrero.setOnClickListener(v -> {
            rellenarDatos(titulo,Descripcion, imagen, "Guerrero");
            imagen.setImageResource(R.drawable.guerrero);
        });
        Caballo.setOnClickListener(v -> {
            rellenarDatos(titulo,Descripcion, imagen, "Caballo");
            imagen.setImageResource(R.drawable.caballo);
        });
        Paloma.setOnClickListener(v -> {
            rellenarDatos(titulo,Descripcion, imagen, "Paloma");
            imagen.setImageResource(R.drawable.paloma);
        });
        Madre.setOnClickListener(v -> {
            rellenarDatos(titulo,Descripcion, imagen, "Madre");
            imagen.setImageResource(R.drawable.madre);
        });


    }

    private void cambiarFragment() {
    }

    private void rellenarDatos(TextView titulo, TextView descripcion, ImageView imagen, String nombre) {
        DaoFigura dao= llamarBD();
        Configuration configuration = getResources().getConfiguration();
        String languageCode = configuration.getLocales().get(0).getLanguage();

        String textoFigura =dao.obtenerTexto(languageCode,nombre);

        titulo.setVisibility(View.VISIBLE);
        descripcion.setVisibility(View.VISIBLE);
        imagen.setVisibility(View.VISIBLE);
        titulo.setText(nombre);
        descripcion.setText(textoFigura);

    }

    public DaoFigura llamarBD(){
        AppDatabase db = Room.databaseBuilder(
                        getContext().getApplicationContext(),
                        AppDatabase.class,
                        "Gernika")
                .allowMainThreadQueries().build();
        DaoFigura dao=db.daoFigura();
        return dao;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_figuras, container, false);
    }
}