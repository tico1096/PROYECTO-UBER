package com.example.android.logindemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainTrayecto extends AppCompatActivity {

    EditText etBuscadorTra;
    RecyclerView rvLista;
    AdaptadorTrayectos adaptador;
    List<Trayecto> listaTrayectos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trayecto);

        etBuscadorTra = findViewById(R.id.etBuscadorTra);
        etBuscadorTra.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }

            @Override
            public void afterTextChanged(Editable s) {
                filtrar(s.toString());
            }
        });

        rvLista = findViewById(R.id.rvListaTrayecto);
        rvLista.setLayoutManager(new GridLayoutManager(this, 1));

        listaTrayectos = new ArrayList<>();

        obtenerTrayectos();

        adaptador = new AdaptadorTrayectos(MainTrayecto.this, listaTrayectos);
        rvLista.setAdapter(adaptador);
    }

    public void obtenerTrayectos() {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());

        StringRequest stringRequest = new StringRequest(Request.Method.POST, getResources().getString(R.string.URL_TRAYECTOS),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray jsonArray = jsonObject.getJSONArray("Trayectos");

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                                listaTrayectos.add(
                                        new Trayecto(
                                                jsonObject1.getString("cedula"),
                                                jsonObject1.getString("nombre"),
                                                jsonObject1.getString("nombrepas"),
                                                jsonObject1.getString("fechainicio"),
                                                jsonObject1.getString("fechafin"),
                                                jsonObject1.getString("valor")

                                        )
                                );
                            }

                            adaptador = new AdaptadorTrayectos(MainTrayecto.this, listaTrayectos);
                            rvLista.setAdapter(adaptador);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        }
        );

        requestQueue.add(stringRequest);
    }

    public void filtrar(String texto) {
        ArrayList<Trayecto> filtrarLista = new ArrayList<>();

        for(Trayecto trayecto : listaTrayectos) {
            if(trayecto.getIdUsuario().toLowerCase().contains(texto.toLowerCase())) {
                filtrarLista.add(trayecto);
            }
        }

        adaptador.filtrar(filtrarLista);
    }
}