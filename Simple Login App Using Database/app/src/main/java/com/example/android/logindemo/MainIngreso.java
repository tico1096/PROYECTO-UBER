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

public class MainIngreso extends AppCompatActivity {

    EditText etBuscadorIng;
    RecyclerView rvLista;
    AdaptadorIngresos adaptador;
    List<Ingreso> listaIngresos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingresos);

        etBuscadorIng = findViewById(R.id.etBuscadorIng);
        etBuscadorIng.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }

            @Override
            public void afterTextChanged(Editable s) {
                filtrar(s.toString());
            }
        });

        rvLista = findViewById(R.id.rvListaIngresos);
        rvLista.setLayoutManager(new GridLayoutManager(this, 1));

        listaIngresos = new ArrayList<>();

        obtenerIngresos();

        adaptador = new AdaptadorIngresos(MainIngreso.this, listaIngresos);
        rvLista.setAdapter(adaptador);
    }

    public void obtenerIngresos() {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());

        StringRequest stringRequest = new StringRequest(Request.Method.POST, getResources().getString(R.string.URL_INGRESOS),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray jsonArray = jsonObject.getJSONArray("Ingresos");

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                                listaIngresos.add(
                                        new Ingreso(
                                                jsonObject1.getString("metodo"),
                                                jsonObject1.getString("cedula"),
                                                jsonObject1.getString("valor"),
                                                jsonObject1.getString("fecha")

                                        )
                                );
                            }

                            adaptador = new AdaptadorIngresos(MainIngreso.this, listaIngresos);
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
        ArrayList<Ingreso> filtrarLista = new ArrayList<>();

        for(Ingreso ingreso : listaIngresos) {
            if(ingreso.getIdUsuario().toLowerCase().contains(texto.toLowerCase())) {
                filtrarLista.add(ingreso);
            }
        }

        adaptador.filtrar(filtrarLista);
    }
}