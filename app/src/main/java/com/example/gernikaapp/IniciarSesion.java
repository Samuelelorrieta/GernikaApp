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

public class IniciarSesion extends Fragment {

    AppDatabase db;
    public IniciarSesion() {
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        //Recoge la instancia de AppDatabase
        db = AppDatabase.getDatabase(getContext());

        //Botones
        Button iniciar = view.findViewById(R.id.registrar);
        Button recordar = view.findViewById(R.id.recordarContra);
        Button registro = view.findViewById(R.id.registro);

        EditText nombre = view.findViewById(R.id.nombre);
        EditText contra = view.findViewById(R.id.contra);

        FirebaseAuth mAuth = FirebaseAuth.getInstance();

        registro.setOnClickListener(v ->{
            cambioPantalla(new Registro(),"",false); //Cambia el fragment a Registro
                });

        iniciar.setOnClickListener(v -> {
            if(!(nombre.getText().toString().equals(""))&&!(contra.getText().toString().equals(""))) //Si los dos campos no estan vacios
             iniciarSesion(nombre,contra,mAuth); //Realiza el inicio de sesion el Firebase
        });

        recordar.setOnClickListener(v -> {
            if(!(nombre.getText().toString().equals(""))||!(contra.getText().toString().equals(""))) //Si los dos campos no estan vacios
                ponerContra(nombre.getText().toString(),contra); //Llama a la BD y revisa si esta guardada la contra
        });

    }

    private void ponerContra(String nombre, EditText contra) {
        Usuario usuario = db.daoUsuario().obtenerUsuarioNombre(nombre); //Llama a la BD
        if(usuario!=null) //Si existe el usuario y guardo la contraseña
        {
            if(usuario.guardarContra)
                contra.setText(usuario.contrasenya); //Pone la contraseña en el campo correspondiente
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_iniciar_sesion, container, false);
    }

    private void iniciarSesion(EditText correo, EditText contraseña, FirebaseAuth mAuth){
        mAuth.signInWithEmailAndPassword(correo.getText().toString(), contraseña.getText().toString()) .addOnCompleteListener(new OnCompleteListener<AuthResult>() //Inicia sesion con Firebase
        {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) { //Si el inicio de sesion se realiza
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            cambioPantalla(new AjustesUsuario(),correo.getText().toString(),true); //Cambia la pantalla a ajustes usuario
                        }
                    }).start();
                }
            }
        });
    }
    private void cambioPantalla(Fragment fragment,String nombre,boolean id){
        if(id)//Si queremos que envie el nombre de usuario
             {
            Usuario usuario = db.daoUsuario().obtenerUsuarioNombre(nombre); //Recoge el nombre de usuario
            Bundle bundle = new Bundle();
            bundle.putInt("idUsuario", usuario.id);//Lo manda en el bundle
            fragment.setArguments(bundle);
        }
        getActivity().getSupportFragmentManager().beginTransaction() //Realiza la transaccion
                .replace(R.id.contenedorFragment, fragment)
                .addToBackStack(null)
                .commit();
    }
}