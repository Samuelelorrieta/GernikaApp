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
import android.widget.EditText;

import com.example.gernikaapp.BD.AppDatabase;
import com.example.gernikaapp.BD.Usuario;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AjustesUsuario#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AjustesUsuario extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    int id=0;
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AjustesUsuario() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AjustesUsuario.
     */
    // TODO: Rename and change types and number of parameters
    public static AjustesUsuario newInstance(String param1, String param2) {
        AjustesUsuario fragment = new AjustesUsuario();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Button continuar =view.findViewById(R.id.continuar);
        Button euskera =view.findViewById(R.id.euskera);
        Button castellano =view.findViewById(R.id.castellano);
        Button borrar =view.findViewById(R.id.borrar);
        EditText contra = view.findViewById(R.id.nombre);

        Bundle bundle = getArguments();
        if (bundle != null) {
            //Recogida de siguiente punto para el marker
            id = bundle.getInt("idUsuario", 0);
        }

        continuar.setOnClickListener(v -> {
            if(!(contra.getText().toString().equals("")))
                actualizarContra(contra);
            cambiarFragment(new MapaFragment());
        });
        euskera.setOnClickListener(v -> {
            cambiarIdioma("eu");
        });
        castellano.setOnClickListener(v -> {
            cambiarIdioma("");
        });
        borrar.setOnClickListener(v -> {
            borrarUsuario();
        });
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
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ajustes_usuario, container, false);
    }
    private void cambiarIdioma(String idioma){
        Configuration configuration = new Configuration(); // Crea la configuracion
        configuration.setLocale(new java.util.Locale(idioma)); // Cambia los recursos
        getResources().updateConfiguration(configuration, getResources().getDisplayMetrics()); // Actualiza los recursos
    }
     public void actualizarContra(EditText contra){
            try {
                    String nuevaContra = contra.getText().toString();
                    user.updatePassword(nuevaContra) //Actualiza la contraseña
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        AppDatabase db = Room.databaseBuilder(
                                                        getContext().getApplicationContext(),
                                                        AppDatabase.class,
                                                        "Gernikaren")
                                                .allowMainThreadQueries().build();
                                        db.daoUsuario().actualizarContra(contra.getText().toString(),id);
                                        cambiarFragment(new MapaFragment());
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
                                    AppDatabase db = Room.databaseBuilder(
                                                    getContext().getApplicationContext(),
                                                    AppDatabase.class,
                                                    "Gernikaren")
                                            .allowMainThreadQueries().build();
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
