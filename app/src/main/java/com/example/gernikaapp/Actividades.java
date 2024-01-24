package com.example.gernikaapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;

import com.example.gernikaapp.BD.Figura;
import com.example.gernikaapp.BD.Texto;
import com.example.gernikaapp.Modelo.Ubicaciones;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class Actividades extends AppCompatActivity {
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividades);
        SharedPreferences prefs = getSharedPreferences ("MisPreferencias", Context.MODE_PRIVATE); //Inicia shared preferences
        Boolean primerInicio = prefs.getBoolean("primerInicio", true);
        if(primerInicio)
        {
            //Meter todos los inserts

            Figura toro = new Figura(1,"Toro");
            Figura guerrero = new Figura(2,"Guerrero");
            Figura caballo = new Figura(3,"Caballo");
            Figura hombre = new Figura(4,"Hombre");
            Figura mujer = new Figura(5,"Mujer");



            Texto toroEsp = new Texto("Aparece en la izquierda del cuadro, con el cuerpo oscuro y la cabeza blanca. Este voltea y parece mostrarse impasible ante lo que ocurre a su alrededor.","esp",1);
            Texto guerreroEsp = new Texto("En realidad, sólo aparecen los restos de la cabeza, brazos. Un brazo tiene la mano extendida. El otro brazo sostiene una espada rota y una flor, que puede interpretarse como un rayo de esperanza dentro de ese panorama descorazonador.","esp",2);
            Texto caballoEsp = new Texto("Ocupa el centro de la composición.Adelanta una de las patas delanteras para mantenerse en equilibrio, pues parece a punto de caerse. En su costado izquierdo se abre una herida vertical y está, además, atravesado por una lanza. ","esp",3);
            Texto hombreEsp = new Texto("Situada a la izquierda, no resulta visible a simple vista. Tiene un ala caída y la cabeza vuelta hacia arriba, con el pico abierto. Generalmente se ha considerado un símbolo de la paz rota.","esp",4);
            Texto mujerEsp = new Texto("Se sitúa a la izquierda, con la cara vuelta hacia el cielo en un ademán o grito de dolor. Sostiene en sus brazos a su hijo ya muerto. Según la muy discutida interpretación de Juan Larrea, el grupo madre-hijo simbolizaría a la ciudad de Madrid, sitiada por las tropas de Franco.","esp",5);



            Texto toroEus = new Texto("Koadroaren ezkerrean agertzen da, gorputza ilun eta burua zuri duela. Itzulipurdika ari da, eta inguruan gertatzen denaren aurrean soraio dagoela dirudi.","eus",1);
            Texto guerreroEus = new Texto("Egia esan, buruaren hondarrak baino ez dira agertzen, besoak. Beso batek eskua luzatuta dauka. Beste besoak ezpata hautsi bat eta lore bat eusten ditu, itxaropen-izpi bat bezala interpreta daitekeena panorama bihozgabe horren barruan.","eus",2);
            Texto caballoEus = new Texto("Konposizioaren erdian dago. Aurreko hanketako bat aurreratzen du orekan mantentzeko, erortzeko zorian dagoela ematen baitu. Ezkerreko saihetsean zauri bertikal bat irekitzen da eta, gainera, lantza batek zeharkatzen du.","eus",3);
            Texto hombreEus = new Texto("Ezkerrean dago, eta ez da begi hutsez ikusten. Hegala erorita dauka eta burua gorantz jiratuta, mokoa irekita. Oro har, hautsitako bakearen sinbolotzat hartu izan da.","eus",4);
            Texto mujerEus = new Texto("Ezkerrean dago, aurpegia zerurantz begira, oinazezko keinu edo oihu batean. Semeari besoetan eusten dio. Juan Larrearen interpretazio eztabaidatuaren arabera, ama-semeen taldeak Madril hiria sinbolizatuko luke, Francoren tropek setiatua.","eus",5);

            SharedPreferences.Editor editor = prefs.edit(); // Crea el editor
            editor.putBoolean("primerInicio",false);
            editor.commit(); // Guarda los cambios
        }

        mAuth = FirebaseAuth.getInstance();
        // Obtén el FragmentManager
        FragmentManager fragmentManager = getSupportFragmentManager();

        // Inicia una transacción
        FragmentTransaction transaction = fragmentManager.beginTransaction();

        // Reemplaza el contenido del contenedor con el Fragment
        transaction.replace(R.id.contenedorFragment, new IniciarSesion());

        // Confirma la transacción
        transaction.commit();
    }
    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
            // Bloquear el botón de retrocesos
            return true;
        }
        return super.dispatchKeyEvent(event);
    }

    public ArrayList<Ubicaciones> obtenerArrayList() {
        return null;
    }
}