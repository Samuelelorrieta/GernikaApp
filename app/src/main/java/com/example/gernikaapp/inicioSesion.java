package com.example.gernikaapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link inicioSesion#newInstance} factory method to
 * create an instance of this fragment.
 */
public class inicioSesion extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public inicioSesion() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment inicioSesion.
     */
    // TODO: Rename and change types and number of parameters
    public static inicioSesion newInstance(String param1, String param2) {
        inicioSesion fragment = new inicioSesion();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
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
        return inflater.inflate(R.layout.fragment_inicio_sesion, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Button iniciarSesion = view.findViewById(R.id.inicioSesion);
        Button registrarse = view.findViewById(R.id.registro1);

        String nombre = view.findViewById(R.id.nombre).toString();
        String contra = view.findViewById(R.id.contra).toString();

        Boolean check = view.findViewById(R.id.bool).isActivated();

        iniciarSesion.setOnClickListener(v -> {

        });
        registrarse.setOnClickListener(v -> {

        });
    }
}
