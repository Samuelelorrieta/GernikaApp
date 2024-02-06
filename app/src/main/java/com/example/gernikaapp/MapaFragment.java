package com.example.gernikaapp;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.gernikaapp.Modelo.Ubicaciones;

import org.osmdroid.api.IMapController;
import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.mylocation.GpsMyLocationProvider;
import org.osmdroid.views.overlay.mylocation.IMyLocationProvider;
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay;

import java.util.ArrayList;

public class MapaFragment extends Fragment {

    private MapView map = null;
    private IMapController mapController;
    private MyLocationNewOverlay myLocationOverlay;

    //Informacion para llegar a la siguiente parte de la gincana

    //--------GeoPoint Información--------//
    private double lat = 0;
    private double lon = 0;
    private String ubicacion = "";
    private final int rango = 2000000;
    //----------------//

    //--------Variables que recogemos--------//
    private int queFragmentVoy = 0;
    private ArrayList<Ubicaciones> listaUbicaciones;
    //----------------//

    private GeoPoint geoPoint;
    private int permiso = 0;
    private boolean error = true;
    String llegada="";

    public MapaFragment() {
        // Constructor vacío requerido por Android
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mapa, container, false);
        String llegada = requireContext().getResources().getString(R.string.Destino);
        //Recibe queFragmentVoy del resto de fragment
        Bundle bundle = getArguments();
        if (bundle != null) {
            //Recogida de siguiente punto para el marker
            queFragmentVoy = bundle.getInt("queFragmentVoy", 0);
        }

        //Recibe la la latitud, longitud, ubicacion de la Activity Actividad
        Actividades actividades = (Actividades) getActivity();
        listaUbicaciones = actividades.obtenerArrayList();

        lat = listaUbicaciones.get(queFragmentVoy).getLat();
        lon = listaUbicaciones.get(queFragmentVoy).getLon();
        ubicacion = listaUbicaciones.get(queFragmentVoy).getNombreUbicacion().toString();

        //Inicializamos el GeoPoint con las coordenadas necesarias
        geoPoint = new GeoPoint(lat, lon); // Coordenadas del marcador
        //geoPoint = new GeoPoint( 43.2837756904145, -2.964419127575523); // Coordenadas del marcador (Clase)


        // Configuración de osmdroid
        Context ctx = requireActivity().getApplicationContext();
        Configuration.getInstance().load(ctx, PreferenceManager.getDefaultSharedPreferences(ctx));
        Configuration.getInstance().setUserAgentValue(requireActivity().getPackageName());

        map = view.findViewById(R.id.mapview);

        // Verificar permisos de ubicación
        while (permiso == 0) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
                    && ContextCompat.checkSelfPermission(requireActivity(), Manifest.permission.ACCESS_FINE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(requireActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            } else {
                setupMap();
                permiso++;
            }
        }
        return view;
    }

    private void setupMap() {
        // Configuración del mapa
        map.setTileSource(TileSourceFactory.DEFAULT_TILE_SOURCE);
        map.setMultiTouchControls(true);
        mapController = map.getController();
        mapController.setZoom(18.0);
        mapController.setCenter(geoPoint);

        // Habilitar el uso de la ubicación del dispositivo
        GpsMyLocationProvider locationProvider = new GpsMyLocationProvider(requireActivity());
        myLocationOverlay = new MyLocationNewOverlay(locationProvider, map) {
            @Override
            public void onLocationChanged(Location location, IMyLocationProvider source) {
                super.onLocationChanged(location, source);

                if (myLocationOverlay != null && myLocationOverlay.getMyLocation() != null && geoPoint != null) {
                    // Verificar la distancia al marcador (ajusta según tu necesidad)
                    double distance = myLocationOverlay.getMyLocation().distanceToAsDouble(geoPoint);

                    // Si estás lo suficientemente cerca del marcador, muestra un mensaje
                    if (distance < rango) { // Distancia a la que se activa el checpoint
                        showMessage(llegada);
                        irBunkerFragment();
                    }
                }
            }
        };

        myLocationOverlay.enableMyLocation();
        myLocationOverlay.enableFollowLocation();
        map.getOverlays().add(myLocationOverlay);

        map.setUseDataConnection(true);

        // Llamar a la función para agregar el marcador en las coordenadas especificadas
        addMarker(geoPoint.getLatitude(), geoPoint.getLongitude(), ubicacion);
    }

    private void addMarker(double latitude, double longitude, String title) {
        // Crear un marcador en las coordenadas especificadas
        org.osmdroid.views.overlay.Marker marker = new org.osmdroid.views.overlay.Marker(map);
        marker.setPosition(new GeoPoint(latitude, longitude));
        marker.setAnchor(org.osmdroid.views.overlay.Marker.ANCHOR_CENTER, org.osmdroid.views.overlay.Marker.ANCHOR_BOTTOM);
        marker.setTitle(title);

        // Agregar el marcador al mapa
        map.getOverlays().add(marker);
    }

    //Muestra pop-up
    private void showMessage(String message) {
        Toast.makeText(requireActivity(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1 && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            setupMap();
        }
    }

    //Cambia de fragment
    public void irBunkerFragment() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    // Pausa el hilo durante 3 segundos (3000 milisegundos)
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                while (error) {
                    try {

                        switch (queFragmentVoy) {
                            case 0:
                                // Fragment a Puzzle
                                GernikaVideoFragment GernikaVideoFragment = new GernikaVideoFragment();
                                FragmentTransaction transaction0 = requireActivity().getSupportFragmentManager().beginTransaction();
                                transaction0.replace(R.id.contenedorFragment, GernikaVideoFragment);
                                error = false;
                                transaction0.commit();
                                break;
                            case 1:
                                // Lleva a BunkerFragment
                                BunkerFragment bunkerFragment = new BunkerFragment();
                                FragmentTransaction transaction1 = requireActivity().getSupportFragmentManager().beginTransaction();
                                transaction1.replace(R.id.contenedorFragment, bunkerFragment);
                                error = false;
                                transaction1.commit();
                                break;
                            case 2:
                                // Lleva a FotoIglesiaFragment2 (Iglesia Andra Mari)
                                FotoIglesiaFragment2 fotoIglesiaFragment2 = new FotoIglesiaFragment2();
                                FragmentTransaction transaction2 = requireActivity().getSupportFragmentManager().beginTransaction();
                                transaction2.replace(R.id.contenedorFragment, fotoIglesiaFragment2);
                                error = false;
                                transaction2.commit();
                                break;

                            case 3:
                                // Lleva a FotoIglesiaFragment (Iglesia San Francisco)
                                FotoIglesiaFragment fotoIglesiaFragment = new FotoIglesiaFragment();
                                FragmentTransaction transaction3 = requireActivity().getSupportFragmentManager().beginTransaction();
                                transaction3.replace(R.id.contenedorFragment, fotoIglesiaFragment);
                                error = false;
                                transaction3.commit();
                                break;
                            case 4:
                                // Lleva a RuletaFragment
                                RuletaFragment ruletaFragment = new RuletaFragment();
                                FragmentTransaction transaction4 = requireActivity().getSupportFragmentManager().beginTransaction();
                                transaction4.replace(R.id.contenedorFragment, ruletaFragment);
                                error = false;
                                transaction4.commit();
                                break;
                            case 5:
                                // Lleva a CancionArboledaFragment
                                CancionArboledaFragment cancionArboledaFragment = new CancionArboledaFragment();
                                FragmentTransaction transaction5 = requireActivity().getSupportFragmentManager().beginTransaction();
                                transaction5.replace(R.id.contenedorFragment, cancionArboledaFragment);
                                error = false;
                                transaction5.commit();
                                break;
                            default:
                                showMessage("");
                                break;
                        }


                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                // Después de la pausa, puedes realizar más operaciones en este hilo
            }
        }).start();
    }

    @Override
    public void onResume() {
        super.onResume();
        map.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        map.onPause();
    }
}
