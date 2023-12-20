package com.example.gernikaapp;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
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

import org.osmdroid.api.IMapController;
import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.mylocation.GpsMyLocationProvider;
import org.osmdroid.views.overlay.mylocation.IMyLocationProvider;
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay;

public class MapaFragment extends Fragment {

    private MapView map = null;
    private IMapController mapController;
    private MyLocationNewOverlay myLocationOverlay;
    private double lat = 43.28397879770591;
    private double lon = -2.9645066850317825;
    private String ubicacion = "Museo de la Paz";
    private int queFragmentVoy = 1;

    private GeoPoint geoPoint = new GeoPoint(lat, lon); // Coordenadas del marcador
    private int permiso = 0;
    private boolean error = true;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mapa, container, false);

        Bundle bundle = getArguments();
        if (bundle != null) {
            lat = bundle.getDouble("lat", 0.0);
            lon = bundle.getDouble("lon", 0.0);
            ubicacion = bundle.getString("ubicacion", "");
            queFragmentVoy = bundle.getInt("queFragmentVoy",0);

            geoPoint.setAltitude(lat);
            geoPoint.setLongitude(lon);

        }

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
                    if (distance < 50) { // Distancia a la que se activa el checpoint
                        showMessage("Acaba de llegar a su destino ;)");
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
    public void irBunkerFragment (){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    // Pausa el hilo durante 3 segundos (3000 milisegundos)
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                while(error){
                    try{

                        if(queFragmentVoy == 0){
                            //Fragment Puzzle
                        }else if(queFragmentVoy == 1){
                            BunkerFragment bunkerFragment = new BunkerFragment();
                            FragmentTransaction transaction = requireActivity().getSupportFragmentManager().beginTransaction();
                            transaction.replace(R.id.contenedorFragment, bunkerFragment);
                            error = false;
                            transaction.commit();
                        }else if(queFragmentVoy == 2){
                            FotoIglesiaFragment fotoIglesiaFragment = new FotoIglesiaFragment();
                            FragmentTransaction transaction = requireActivity().getSupportFragmentManager().beginTransaction();
                            transaction.replace(R.id.contenedorFragment, fotoIglesiaFragment);
                            error = false;
                            transaction.commit();
                        } else if(queFragmentVoy == 3){

                        }




                    }catch (Exception e) {
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
