package com.example.android.logindemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class ConductorPerfil extends AppCompatActivity {
    EditText edtCedula,edtNombre,edtEdad,edtLicencia,edtPropiedad,edtSoat,edtTecnico,edtCuenta;
    Button btnAgregarse;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conduct);
        edtCedula=(EditText)findViewById(R.id.edtCedula);
        edtNombre=(EditText)findViewById(R.id.edtNombre);
        edtEdad=(EditText)findViewById(R.id.edtEdad);
        edtLicencia=(EditText)findViewById(R.id.edtLicencia);
        edtPropiedad=(EditText)findViewById(R.id.edtPropiedad);
        edtSoat=(EditText)findViewById(R.id.edtSoat);
        edtTecnico=(EditText)findViewById(R.id.edtTecnico);
        edtCuenta=(EditText)findViewById(R.id.edtCuenta);
        btnAgregarse=(Button) findViewById(R.id.btnAgregarse);

        btnAgregarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ejecutarServicio("http://192.168.0.208/developeruber/insertar_usuarios.php");
            }
        });
    }
    private void ejecutarServicio(String URL){
        StringRequest stringRequest=new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getApplicationContext(), "OPERACION EXITOSA", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),error.toString(),Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> parametros=new HashMap<String, String>();
                parametros.put("cedula",edtCedula.getText().toString());
                parametros.put("nombre",edtNombre.getText().toString());
                parametros.put("edad",edtEdad.getText().toString());
                parametros.put("licencia",edtLicencia.getText().toString());
                parametros.put("propiedad",edtPropiedad.getText().toString());
                parametros.put("soat",edtSoat.getText().toString());
                parametros.put("tecnico",edtTecnico.getText().toString());
                parametros.put("cuenta",edtCuenta.getText().toString());
                return parametros;
            }
        };
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
