package com.example.android.logindemo;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.firebase.auth.FirebaseAuth;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import Modules.DirectionFinder;
import Modules.DirectionFinderListener;
import Modules.Route;

public class GMuber extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private FirebaseAuth firebaseAuth;
    private static final int LOCATION_REQUEST=500;
    ArrayList<LatLng> listPoints;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gmuber);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        listPoints=new ArrayList<>();


        firebaseAuth = FirebaseAuth.getInstance();
    }
    private void Logout(){
        firebaseAuth.signOut();
        finish();
        startActivity(new Intent(GMuber.this, MainActivity.class));
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()){
            case R.id.logoutMenu:{
                Logout();
                break;
            }
            case R.id.profileMenu: {
                startActivity(new Intent(GMuber.this, ProfileActivity.class));
                break;
            }
            case R.id.refreshMenu:
                startActivity(new Intent(GMuber.this, UpdateProfile.class));
                break;

        }
        return super.onOptionsItemSelected(item);
    }
    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        // Controles UI
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},LOCATION_REQUEST);
            return;
        }
        mMap.setMyLocationEnabled(true);
        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                //reinicie el marcador cuando hayan 2
                if(listPoints.size()==2){
                    listPoints.clear();
                    mMap.clear();
                }
                //Guarde el primer punto seleccionado
                listPoints.add(latLng);
                //Crear marcador
                MarkerOptions markerOptions=new MarkerOptions();
                markerOptions.position(latLng);
                if (listPoints.size()==1){
                    // AGREGAR PRIMER MARCADOR AL MAPA
                    markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)).title("Ubicación de origen");
                } else {
                    // añpade segundo marcador
                    markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)).title("Ubicación de destino");
                }
                mMap.addMarker(markerOptions);

                if (listPoints.size()==2){
                    //Crear URL para la respuesta get de primer marcador al segundo marcador
                    String url=getRequestUrl(listPoints.get(0),listPoints.get(1));
                    TaskRequestDirections taskRequestDirections=new TaskRequestDirections();
                    taskRequestDirections.execute(url);
                }
            }
        });
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        mMap.getUiSettings().setZoomControlsEnabled(true);
    }
    private String getRequestUrl(LatLng origin, LatLng dest){
        //Valor de origen
        String str_org = "origin="+origin.latitude+","+origin.longitude;
        //Valor de destinos
        String str_dest = "destination="+dest.latitude+","+dest.longitude;
        //poner activo el sensor
        String sensor = "sensor=false";
        //Modo para buscar dirección
        String mode = "mode=driving";
        //Vincular parametros
        String param = str_org+"&"+str_dest+"&"+sensor+"&"+mode;
        //Formato de salida
        String output = "json";
        //Crear url de respuesta
        String url = "https://maps.googleapis.com/maps/api/directions/"+output+"?"+param+"&key=AIzaSyDAeg7lXxYMqgX6Zw0sFyC74YLh8m4u5S8";
        return url;
    }

    private String resquestDirection(String reqUrl) throws IOException {
        String responseString = "";
        InputStream inputStream = null;
        HttpURLConnection httpURLConnection = null;
        try{
            URL url=new URL(reqUrl);
            httpURLConnection=(HttpURLConnection) url.openConnection();
            httpURLConnection.connect();
            //Get resultado de la respuesta
            inputStream=httpURLConnection.getInputStream();
            InputStreamReader inputStreamReader=new InputStreamReader(inputStream);
            BufferedReader bufferedReader=new BufferedReader(inputStreamReader);

            StringBuffer stringBuffer=new StringBuffer();
            String line = "";
            while ((line=bufferedReader.readLine())!=null){
                stringBuffer.append(line);
            }
            responseString=stringBuffer.toString();
            bufferedReader.close();
            inputStreamReader.close();
        } catch (Exception e){
            e.printStackTrace();
        }finally {
            if(inputStream!=null){
                inputStream.close();
            }
            httpURLConnection.disconnect();
        }
        return responseString;
    }

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        switch(requestCode){
            case LOCATION_REQUEST:
                if (grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    mMap.setMyLocationEnabled(true);
                }
                break;
        }

    }

    public class TaskRequestDirections extends AsyncTask<String,Void,String>{
        protected String doInBackground(String... strings){
            String responseString="";
            try{
                responseString=resquestDirection(strings[0]);
            }catch (IOException e){
                e.printStackTrace();
            }
            return responseString;
        }

        protected void onPostExecute(String s){
            super.onPostExecute(s);
            //Parse json aqui
            TaskParser taskParser=new TaskParser();
            taskParser.execute(s);

        }
    }

    public class TaskParser extends AsyncTask<String,Void, List<List<HashMap<String,String>>> > {
        protected List<List<HashMap<String,String>>> doInBackground(String... strings){
            JSONObject jsonObject=null;
            List<List<HashMap<String,String>>> routes=null;
            try{
            jsonObject=new JSONObject(strings[0]);
            DirectionsParser directionsParser=new DirectionsParser();
            routes=directionsParser.parse(jsonObject);
            } catch (JSONException e){
                e.printStackTrace();
            }
            return routes;
        }
        protected void onPostExecute(List<List<HashMap<String,String>>>lists){
            //Poner lista de rutas y display en el mapa
            ArrayList points=null;
            PolylineOptions polylineOptions=null;

            for(List<HashMap<String,String>> path:lists){
                points= new ArrayList();
                polylineOptions=new PolylineOptions();

                for(HashMap<String,String> point:path){
                    double lat=Double.parseDouble(point.get("lat"));
                    double lon=Double.parseDouble(point.get("lon"));

                    points.add(new LatLng(lat,lon));
                }

                polylineOptions.addAll(points);
                polylineOptions.width(15);
                polylineOptions.color(Color.BLUE);
                polylineOptions.geodesic(true);
            }

            if (polylineOptions!=null){
                mMap.addPolyline(polylineOptions);
            }else{
                Toast.makeText(getApplicationContext(),"Dirección no encontrada!", Toast.LENGTH_SHORT).show();
            }
        }
    }
}

