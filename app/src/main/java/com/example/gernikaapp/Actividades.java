package com.example.gernikaapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.room.Room;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.Toast;

import com.example.gernikaapp.BD.AppDatabase;
import com.example.gernikaapp.BD.DaoFigura;
import com.example.gernikaapp.BD.DaoUsuario;
import com.example.gernikaapp.BD.Figura;
import com.example.gernikaapp.BD.JuegoRuleta.DaoLetra;
import com.example.gernikaapp.BD.JuegoRuleta.DaoMunicipio;
import com.example.gernikaapp.BD.JuegoRuleta.Letra;
import com.example.gernikaapp.BD.JuegoRuleta.Municipio;
import com.example.gernikaapp.BD.Texto;
import com.example.gernikaapp.BD.Usuario;
import com.example.gernikaapp.Modelo.Ubicaciones;
import com.google.firebase.auth.FirebaseAuth;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Actividades extends AppCompatActivity {
    private ArrayList<Ubicaciones> listaUbicaciones;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividades);

        SharedPreferences prefs = getSharedPreferences("Gernika", Context.MODE_PRIVATE); //Inicia shared preferences
        Boolean primerInicio = prefs.getBoolean("InicioGernika", true); //Revisa si es la primera vez que se inicia la aplicacion, para realizar solo una vez los Insert
       if (primerInicio) {
           //Crea la instancia de AppDatabase
           AppDatabase db = AppDatabase.getDatabase(getApplicationContext());

            // Llamada del DaoMunicipio para hacer el insert de municipios y las letras
            DaoMunicipio daoMunicipio = db.daoMunicipio();
            DaoLetra daoLetra = db.daoLetra();

            // Insert de Letras
            String abecedario = "ABDEFGHIJKLMNOPRSTUXZ";
            for (int i = 0; i < abecedario.length(); i++) {
                String letraSola = String.valueOf(abecedario.charAt(i));
                daoLetra.insertarLetra(new Letra(i + 1, letraSola));
            }

            // Insert de Municipios
            Resources res = getResources();
            InputStream is = res.openRawResource(R.raw.puebloseuskadi);
            Scanner scanner = new Scanner(is);
            // Mapa para rastrear el contador de cada letra
            Map<Character, Integer> letraContador = new HashMap<>();

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] lisMunicipio = line.split("#");

                for (String listaMunicipios : lisMunicipio) {
                    // Obtener la primera letra del elemento
                    char primeraLetra = listaMunicipios.toUpperCase().charAt(0);

                    // Verificar si ya hemos encontrado esta letra antes
                    if (letraContador.containsKey(primeraLetra)) {
                        // Incrementar el contador existente
                        int contador = letraContador.get(primeraLetra) + 1;
                        letraContador.put(primeraLetra, contador);
                    }   else {
                        // Agregar la letra al mapa con un contador inicial de 1
                        letraContador.put(primeraLetra, 1);
                    }

                    // Obtener el número asociado a la letra
                    int numero = abecedario.indexOf(primeraLetra) + 1;

                    System.out.println(listaMunicipios.trim() + " " + numero);

                    // Meter el Municipio con su nombre y código de letra
                    daoMunicipio.insertarMunicipio(new Municipio(listaMunicipios.trim(), numero));
                }
            }
            scanner.close();

            //Crea los objetos Figura y Texto

            Figura toro = new Figura(1,"Toro");
            Figura guerrero = new Figura(2,"Guerrero");
            Figura caballo = new Figura(3,"Caballo");
            Figura hombre = new Figura(4,"Paloma");
            Figura mujer = new Figura(5,"Madre");

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

            //Los introduce en la BD

            DaoFigura usur = db.daoFigura();

            usur.insertarFigura(toro);
            usur.insertarTexto(toroEus);
            usur.insertarTexto(toroEsp);

            usur.insertarFigura(guerrero);
            usur.insertarTexto(guerreroEsp);
            usur.insertarTexto(guerreroEus);

            usur.insertarFigura(caballo);
            usur.insertarTexto(caballoEus);
            usur.insertarTexto(caballoEsp);

            usur.insertarFigura(hombre);
            usur.insertarTexto(hombreEsp);
            usur.insertarTexto(hombreEus);

            usur.insertarFigura(mujer);
            usur.insertarTexto(mujerEsp);
            usur.insertarTexto(mujerEus);

            db.daoUsuario().insertarUsuario(new Usuario("pedro@gmail.com","Pedro10",true));

            SharedPreferences.Editor editor = prefs.edit(); // Crea el editor
            editor.putBoolean("InicioGernika",false); //Indica que la aplicacion ya ha sido iniciada, y no es necesario realizar los Insert
            editor.commit(); // Guarda los cambios
        }

        // Obtén el FragmentManager
        FragmentManager fragmentManager = getSupportFragmentManager();

        // Inicia una transacción
        FragmentTransaction transaction = fragmentManager.beginTransaction();

        // Reemplaza el contenido del contenedor con el Fragment
        transaction.replace(R.id.contenedorFragment, new IniciarSesion());

        // Confirma la transacción
        transaction.commit();

        //Recogida de Ubicaciones ya cargadas en el ArrayList
        listaUbicaciones = getIntent().getParcelableArrayListExtra("clave_listaUbicaciones");

    }

    public ArrayList<Ubicaciones> obtenerArrayList() {
        return listaUbicaciones;
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
            // Bloquear el botón de retrocesos
            return true;
        }
        return super.dispatchKeyEvent(event);
    }



}