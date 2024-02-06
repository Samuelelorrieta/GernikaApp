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

public class Registro extends Fragment {
    TextView textoError;
    AppDatabase db;

    public Registro() {

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        FirebaseAuth mAuth = FirebaseAuth.getInstance();

        //Recoge la instancia de AppDatabase
        db = AppDatabase.getDatabase(getContext());

        //Registra el usuario en Firebase y BD
        Button registro = view.findViewById(R.id.registrar);

        //Campos de registro9
        EditText nombre = view.findViewById(R.id.nombre);
        EditText contra = view.findViewById(R.id.contra);
        CheckBox check =  view.findViewById(R.id.checkBox);

        textoError =view.findViewById(R.id.textoError);

        registro.setOnClickListener(v -> {
            if(nombre.getText().equals("")||contra.getText().equals("")) //Si alguno de los campos esta vacio
                textoError.setText("Error, rellena los campos");
            else
                registrarUsuario(nombre,contra,mAuth,check); //Realiza el registro
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
            mAuth.createUserWithEmailAndPassword(correo.getText().toString(), contraseña.getText().toString()) //Registra el usuario
                    .addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) { ///Si el registro se realiza
                                new Thread(new Runnable() {
                                    @Override
                                    public void run() {
                                       // cambioPantalla(new MapaFragment());
                                        db.daoUsuario().insertarUsuario(new Usuario(correo.getText().toString(),contraseña.getText().toString(),check.isChecked())); //Realiza el registro en la BD
                                        cambioPantalla(new AjustesUsuario(),correo.getText().toString()); //Cambia de pantalla a Ajustes
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

    private void cambioPantalla(Fragment fragment,String nombre){

        Usuario usuario=db.daoUsuario().obtenerUsuarioNombre(nombre); //Recoge el nombre de usuario
        Bundle bundle = new Bundle();
        bundle.putInt("idUsuario", usuario.id);//Lo manda en el bundle
        fragment.setArguments(bundle);
        getActivity().getSupportFragmentManager().beginTransaction()//Realiza la transaccion
                .replace(R.id.contenedorFragment, fragment)
                .addToBackStack(null)
                .commit();
    }
}