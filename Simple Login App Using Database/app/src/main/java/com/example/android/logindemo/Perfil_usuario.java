package com.example.android.logindemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class Perfil_usuario extends AppCompatActivity {
    DatabaseReference mRootReference;
    Button mButtonSubirDatosFirebase;
    EditText mEditTextDatoNombreUsuario, mEditTextDatoApellidoUsuario, mEditTextDatoTelefonoUsuario, mEditTextDatoDireccionUsuario, mEditTextDatoEmailUsuario, mEditTextDatoPerfilUsuario, mEditTextDatoPlacaUsuario, mEditTextDatoModeloUsuario, mEditTextDatoLicenciaUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_usuario);

        mButtonSubirDatosFirebase = findViewById(R.id.btnSubirDatos);
        mEditTextDatoNombreUsuario = findViewById(R.id.etNombre);
        mEditTextDatoApellidoUsuario = findViewById(R.id.etApellido);
        mEditTextDatoTelefonoUsuario = findViewById(R.id.etTelefono);
        mEditTextDatoDireccionUsuario = findViewById(R.id.etDireccion);
        mEditTextDatoEmailUsuario = findViewById(R.id.etCorreo);
        mEditTextDatoPerfilUsuario = findViewById(R.id.etPerfil);
        mEditTextDatoPlacaUsuario = findViewById(R.id.etPlacaVH);
        mEditTextDatoModeloUsuario = findViewById(R.id.etModeloVH);
        mEditTextDatoLicenciaUsuario = findViewById(R.id.etLicenciaVH);

        mRootReference = FirebaseDatabase.getInstance().getReference();

        mButtonSubirDatosFirebase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombre = mEditTextDatoNombreUsuario.getText().toString();
                String apellido = mEditTextDatoApellidoUsuario.getText().toString();
                int telefono = Integer.parseInt(mEditTextDatoTelefonoUsuario.getText().toString());
                String direccion = mEditTextDatoDireccionUsuario.getText().toString();
                String correo = mEditTextDatoEmailUsuario.getText().toString();
                String perfil = mEditTextDatoPerfilUsuario.getText().toString();
                String placa = mEditTextDatoPlacaUsuario.getText().toString();
                String modelo = mEditTextDatoModeloUsuario.getText().toString();
                String licencia = mEditTextDatoLicenciaUsuario.getText().toString();

                Map<String, Object> datosUsuario = new HashMap<>();
                datosUsuario.put("nombre", nombre);
                datosUsuario.put("apellido", apellido);
                datosUsuario.put("telefono", telefono);
                datosUsuario.put("direccion", direccion);
                datosUsuario.put("email", correo);
                datosUsuario.put("perfil", perfil);
                datosUsuario.put("placa", placa);
                datosUsuario.put("modelo", modelo);
                datosUsuario.put("licencia", licencia);

                mRootReference.child("perfil").push().setValue(datosUsuario);
            }
        });


    }

}