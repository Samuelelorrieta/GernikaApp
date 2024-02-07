package com.example.gernikaapp;

import static androidx.core.app.ActivityCompat.recreate;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.room.Room;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.RadioButton;

import com.example.gernikaapp.BD.AppDatabase;

import java.util.Objects;

public class AjustesUsuario extends Fragment {

    int id=0;
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

    AppDatabase db;

    public AjustesUsuario() {
        // Required empty public constructor
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        db= AppDatabase.getDatabase(getContext());
       SharedPreferences prefs = requireContext().getSharedPreferences("Gernika", Context.MODE_PRIVATE);
        Button continuar =view.findViewById(R.id.continuar);

        RadioButton euskera = view.findViewById(R.id.radioEuskera);
        RadioButton castellano = view.findViewById(R.id.radioCastellano);

        Button borrar =view.findViewById(R.id.borrar);
        EditText contra = view.findViewById(R.id.nombre);

        Bundle bundle = getArguments();
        if (bundle != null) {
            //Recogida de siguiente punto para el marker
            id = bundle.getInt("idUsuario", 0);
        }

        continuar.setOnClickListener(v -> {
            if(castellano.isChecked()||euskera.isChecked()) //Si alguno de los idiomas esta seleccionado
            {
                String idioma="";
                if(euskera.isChecked())
                    idioma="eus";
                if(!(contra.getText().toString().equals("")))
                    actualizarContra(contra);
                cambiarIdioma(idioma,prefs);
                cambiarFragment(new MapaFragment());
            }
        });
        borrar.setOnClickListener(v -> {
            borrarUsuario();
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ajustes_usuario, container, false);
    }
    private void cambiarIdioma(String idioma,SharedPreferences prefs){
        Configuration configuration = new Configuration(); // Crea la configuracion
        configuration.setLocale(new java.util.Locale(idioma)); // Cambia los recursos
        getResources().updateConfiguration(configuration, getResources().getDisplayMetrics()); // Actualiza los recursos

        SharedPreferences.Editor editor = prefs.edit(); // Crea el editor
        editor.putString("idiomas",idioma); //Indica que la aplicacion ya ha sido iniciada, y no es necesario realizar los Insert
        editor.commit(); // Guarda los cambios
    }
     public void actualizarContra(EditText contra){
            try {
                    String nuevaContra = contra.getText().toString();
                    user.updatePassword(nuevaContra) //Actualiza la contraseña
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {

                                        db.daoUsuario().actualizarContra(contra.getText().toString(),id);
                                    } else {
                                    }
                                }
                            });
            }catch(Exception e)
            {

            }
        }
            public void borrarUsuario(){

                user.delete() //Se borra
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    cambiarFragment(new IniciarSesion());

                                    db.daoUsuario().borrarUsuario(id);
                                } else {
                                }
                            }
                        });
            }
            public void cambiarFragment(Fragment fragment){
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.contenedorFragment, new MapaFragment())
                        .addToBackStack(null)
                        .commit();
            }
        }
