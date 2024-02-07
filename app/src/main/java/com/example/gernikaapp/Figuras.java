package com.example.gernikaapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gernikaapp.BD.AppDatabase;

public class Figuras extends Fragment {

    private int queFragmentVoy=1;
    AppDatabase db;

    public Figuras() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        db = AppDatabase.getDatabase(getContext());

        Bundle bundle = new Bundle();
        if(bundle!=null)
        {
            queFragmentVoy=bundle.getInt("queFragmentVoy",1); //Recoge el valor del bundle
        }

        //Imagenes de figura
        ImageView toro =view.findViewById(R.id.toro);
        ImageView Guerrero =view.findViewById(R.id.guerrero);
        ImageView Caballo =view.findViewById(R.id.caballo);
        ImageView Paloma =view.findViewById(R.id.paloma);
        ImageView Madre =view.findViewById(R.id.madre);


        Button continuar = view.findViewById(R.id.continuarFiguras);

        //Informacion Figuras
        TextView titulo =view.findViewById(R.id.Titulo);
        TextView Descripcion =view.findViewById(R.id.textoExplicacion);

        continuar.setOnClickListener(v -> {
            cambiarFragment();
                });

        toro.setOnClickListener(v -> {
            rellenarDatos(titulo,Descripcion,"Toro","Zezena"); //Al clikcar en la imagens e muestra la informacion de la figura

        });
        Guerrero.setOnClickListener(v -> {
            rellenarDatos(titulo,Descripcion, "Guerrero","Gudari"); //Al clikcar en la imagens e muestra la informacion de la figura

        });
        Caballo.setOnClickListener(v -> {
            rellenarDatos(titulo,Descripcion, "Caballo","Zaldia"); //Al clikcar en la imagens e muestra la informacion de la figura

        });
        Paloma.setOnClickListener(v -> {
            rellenarDatos(titulo,Descripcion, "Paloma","Usoa"); //Al clikcar en la imagens e muestra la informacion de la figura

        });
        Madre.setOnClickListener(v -> {
            rellenarDatos(titulo,Descripcion, "Madre","Ama"); //Al clikcar en la imagens e muestra la informacion de la figura

        });
    }

    private void cambiarFragment() {
        Bundle bundle = new Bundle();
        bundle.putInt("queFragmentVoy",queFragmentVoy); //Pone la informacion en el bundle
        MapaFragment mapaFragment = new MapaFragment();
        mapaFragment.setArguments(bundle);

        getActivity().getSupportFragmentManager().beginTransaction() //Realiza la transaccion
                .replace(R.id.contenedorFragment, mapaFragment)
                .addToBackStack(null)
                .commit();
    }

    private void rellenarDatos(TextView titulo, TextView descripcion, String esp, String eus) {
        SharedPreferences prefs = requireContext().getSharedPreferences("Gernika", Context.MODE_PRIVATE);
        String idioma = prefs.getString("idiomas", "");
        if(idioma.equals(""))
            idioma="esp";
        String textoFigura =db.daoFigura().obtenerTexto(idioma,esp); //Recoge en la BD
        if(idioma.equals("eus"))
            titulo.setText(eus);
        else
            titulo.setText(esp);
        descripcion.setText(textoFigura);
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