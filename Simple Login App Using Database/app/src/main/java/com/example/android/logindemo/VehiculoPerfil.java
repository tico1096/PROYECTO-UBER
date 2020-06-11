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

public class VehiculoPerfil extends AppCompatActivity {
    EditText edtCedula,edtNombre,edtModelo,edtColor,edtPlaca;
    Button btnAgregarVehiculo;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehiculo);
        edtCedula=(EditText)findViewById(R.id.edtCedula);
        edtNombre=(EditText)findViewById(R.id.edtNombre);
        edtModelo=(EditText)findViewById(R.id.edtModelo);
        edtColor=(EditText)findViewById(R.id.edtColor);
        edtPlaca=(EditText)findViewById(R.id.edtPlaca);
        btnAgregarVehiculo=(Button) findViewById(R.id.btnAgregarVehiculo);
        btnAgregarVehiculo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ejecutarServicio("http://192.168.0.208/developeruber/insertar_vehiculo.php");
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
                parametros.put("modelo",edtModelo.getText().toString());
                parametros.put("color",edtColor.getText().toString());
                parametros.put("placa",edtPlaca.getText().toString());
                return parametros;
            }
        };
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}