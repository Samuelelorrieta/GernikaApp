package com.example.gernikaapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.room.Room;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.gernikaapp.BD.AppDatabase;
import com.example.gernikaapp.BD.Usuario;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link IniciarSesion#newInstance} factory method to
 * create an instance of this fragment.
 */
public class IniciarSesion extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public IniciarSesion() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment IniciarSesion.
     */
    // TODO: Rename and change types and number of parameters
    public static IniciarSesion newInstance(String param1, String param2) {
        IniciarSesion fragment = new IniciarSesion();
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
        Button iniciar = view.findViewById(R.id.registrar);
        Button recordar = view.findViewById(R.id.recordarContra);
        Button registro = view.findViewById(R.id.registro);
        EditText nombre = view.findViewById(R.id.nombre);
        EditText contra = view.findViewById(R.id.contra);
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        TextView textoError =view.findViewById(R.id.textoError);
        registro.setOnClickListener(v ->{
            cambioPantalla(new Registro(),"",false);
                });
        iniciar.setOnClickListener(v -> {
            iniciarSesion(nombre,contra,mAuth);
        });
        recordar.setOnClickListener(v -> {
            ponerContra(nombre.getText().toString(),contra);
        });
    }

    private void ponerContra(String nombre, EditText contra) {
        AppDatabase db = Room.databaseBuilder(
                        getContext().getApplicationContext(),
                        AppDatabase.class,
                        "DatuBase")
                .allowMainThreadQueries().build();
        Usuario usuario = db.daoUsuario().obtenerUsuarioNombre(nombre);
        if(usuario.guardarContra)
        {
            contra.setText(usuario.contrasenya.toString());
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_iniciar_sesion, container, false);
    }

    private void iniciarSesion(EditText correo, EditText contraseña, FirebaseAuth mAuth){
        mAuth.signInWithEmailAndPassword(correo.getText().toString(), contraseña.getText().toString()) .addOnCompleteListener(new OnCompleteListener<AuthResult>()
        {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            cambioPantalla(new AjustesUsuario(),correo.getText().toString(),true);
                        }
                    }).start();
                }
            }
        });
    }
    private void cambioPantalla(Fragment fragment,String nombre,boolean id){
        if(id) {
            AppDatabase db = Room.databaseBuilder(
                            getContext().getApplicationContext(),
                            AppDatabase.class,
                            "DatuBase")
                    .allowMainThreadQueries().build();
            Usuario usuario = db.daoUsuario().obtenerUsuarioNombre(nombre);
            Bundle bundle = new Bundle();
            bundle.putInt("idUsuario", usuario.id);
            fragment.setArguments(bundle);
        }
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.contenedorFragment, fragment)
                .addToBackStack(null)
                .commit();
    }
}