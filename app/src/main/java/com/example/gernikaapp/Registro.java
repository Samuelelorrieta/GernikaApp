package com.example.gernikaapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.room.Room;

import com.example.gernikaapp.BD.AppDatabase;
import com.example.gernikaapp.BD.Usuario;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

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
    TextView textoError;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Registro() {

    }

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
        Button registro = view.findViewById(R.id.Euskera);
        EditText nombre = view.findViewById(R.id.nombre);
        EditText contra = view.findViewById(R.id.contra);
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        CheckBox check =  view.findViewById(R.id.checkBox);
        textoError =view.findViewById(R.id.textoError);
        TextView inicio =view.findViewById(R.id.iniciar);
        registro.setOnClickListener(v -> {
           registrarUsuario(nombre,contra,mAuth,check);
            if(nombre.getText().equals("")||contra.getText().equals(""))
                textoError.setText("Error, rellena los campos");
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
    private void registrarUsuario(EditText correo, EditText contraseña, FirebaseAuth mAuth, CheckBox check) {
        try{
            mAuth.createUserWithEmailAndPassword(correo.getText().toString(), contraseña.getText().toString())
                    .addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                new Thread(new Runnable() {
                                    @Override
                                    public void run() {
                                       // cambioPantalla(new MapaFragment());
                                        registroBD(correo.getText().toString(), contraseña.getText().toString(),check.isChecked());
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

    private void registroBD(String nombre, String contra, boolean checked) {
        AppDatabase db = Room.databaseBuilder(
                        getContext().getApplicationContext(),
                        AppDatabase.class,
                        "Gernika")
                .allowMainThreadQueries().build();
        Usuario registrar = new Usuario(nombre,contra,checked);
        db.daoUsuario().insertarUsuario(registrar);
    }


    private void cambioPantalla(Fragment fragment){
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.contenedorFragment, fragment)
                .addToBackStack(null)
                .commit();
    }
}