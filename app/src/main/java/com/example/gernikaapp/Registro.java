package com.example.gernikaapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.checkerframework.common.reflection.qual.NewInstance;

import java.util.concurrent.Executor;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Registro#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Registro extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Registro() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Registro.
     */
    // TODO: Rename and change types and number of parameters
    public static Registro newInstance(String param1, String param2) {
        Registro fragment = new Registro();
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
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Button registro = view.findViewById(R.id.registro);
        EditText nombre = view.findViewById(R.id.nombre);
        EditText contra = view.findViewById(R.id.contra);
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        TextView textoError =view.findViewById(R.id.textoError);
        TextView inicio =view.findViewById(R.id.iniciar);
        registro.setOnClickListener(v -> {
           registrarUsuario(nombre,contra,mAuth,textoError);
            if(nombre.getText()==null||contra.getText()==null)
                textoError.setText("Error, rellena los campos");
            else
                textoError.setText("Error, intentalo de nuevo");
        });
        inicio.setOnClickListener(v -> {
            cambioPantalla(new IniciarSesion());
        });
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_registro, container, false);
    }
    private void registrarUsuario(EditText correo, EditText contraseña, FirebaseAuth mAuth,TextView textoError) {
        try{
            mAuth.createUserWithEmailAndPassword(correo.getText().toString(), contraseña.getText().toString())
                    .addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                new Thread(new Runnable() {
                                    @Override
                                    public void run() {
                                        cambioPantalla(new MapaFragment());
                                    }
                                }).start();
                            } else {

                            }
                        }
                    });
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    private void cambioPantalla(Fragment fragment){
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.contenedorFragment, fragment)
                .addToBackStack(null)
                .commit();
    }
}