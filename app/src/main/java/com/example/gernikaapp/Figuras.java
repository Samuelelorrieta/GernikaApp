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

public class Figuras extends Fragment {

    private int queFragmentVoy=1;

    public Figuras() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        Bundle bundle = new Bundle();
        if(bundle!=null)
        {
            queFragmentVoy=bundle.getInt("queFragmentVoy",1);
        }


        ImageView toro =view.findViewById(R.id.toro);
        ImageView Guerrero =view.findViewById(R.id.guerrero);
        ImageView Caballo =view.findViewById(R.id.caballo);
        ImageView Paloma =view.findViewById(R.id.paloma);
        ImageView Madre =view.findViewById(R.id.madre);
        Button continuar = view.findViewById(R.id.continuarFiguras);


        TextView titulo =view.findViewById(R.id.Titulo);
        TextView Descripcion =view.findViewById(R.id.textoExplicacion);

        continuar.setOnClickListener(v -> {
            cambiarFragment();
                });

        toro.setOnClickListener(v -> {
            rellenarDatos(titulo,Descripcion,"Toro");

        });
        Guerrero.setOnClickListener(v -> {
            rellenarDatos(titulo,Descripcion, "Guerrero");

        });
        Caballo.setOnClickListener(v -> {
            rellenarDatos(titulo,Descripcion, "Caballo");

        });
        Paloma.setOnClickListener(v -> {
            rellenarDatos(titulo,Descripcion, "Paloma");

        });
        Madre.setOnClickListener(v -> {
            rellenarDatos(titulo,Descripcion, "Madre");

        });


    }

    private void cambiarFragment() {
        Bundle bundle = new Bundle();
        bundle.putInt("queFragmentVoy",queFragmentVoy);
        MapaFragment mapaFragment = new MapaFragment();
        mapaFragment.setArguments(bundle);

        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.contenedorFragment, mapaFragment)

                .addToBackStack(null)
                .commit();
    }

    private void rellenarDatos(TextView titulo, TextView descripcion, String nombre) {
        DaoFigura dao= llamarBD();
        String textoFigura =dao.obtenerTexto("esp",nombre);
        titulo.setText(nombre);
        descripcion.setText(textoFigura);
    }

    public DaoFigura llamarBD(){
        AppDatabase db = Room.databaseBuilder(
                        getContext().getApplicationContext(),
                        AppDatabase.class,
                        "DatuBase")
                .allowMainThreadQueries().build();
        DaoFigura dao=db.daoFigura();
        return dao;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_figuras, container, false);
    }
}