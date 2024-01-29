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

        continuar.setOnClickListener(v -> {
            cambiarFragment();
                });

        toro.setOnClickListener(v -> {
            rellenarDatos(titulo,Descripcion,imagen,continuar,"Toro");
            imagen.setImageResource(R.drawable.toro);
        });
        Guerrero.setOnClickListener(v -> {
            rellenarDatos(titulo,Descripcion, imagen,continuar, "Guerrero");
            imagen.setImageResource(R.drawable.guerrero);
        });
        Caballo.setOnClickListener(v -> {
            rellenarDatos(titulo,Descripcion, imagen,continuar, "Caballo");
            imagen.setImageResource(R.drawable.caballo);
        });
        Paloma.setOnClickListener(v -> {
            rellenarDatos(titulo,Descripcion, imagen,continuar, "Paloma");
            imagen.setImageResource(R.drawable.paloma);
        });
        Madre.setOnClickListener(v -> {
            rellenarDatos(titulo,Descripcion, imagen,continuar, "Madre");
            imagen.setImageResource(R.drawable.madre);
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

    private void rellenarDatos(TextView titulo, TextView descripcion, ImageView imagen, Button continuar, String nombre) {
        DaoFigura dao= llamarBD();
        String textoFigura="";

            textoFigura =dao.obtenerTexto("esp",nombre);

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
                        "Gernikako")
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