package com.example.android.logindemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class TercerActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    private Button logout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tercer);

        firebaseAuth = FirebaseAuth.getInstance();

        logout = (Button)findViewById(R.id.btnLogout);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Logout();
            }
        });

    }


    private void Logout(){
        firebaseAuth.signOut();
        finish();
        startActivity(new Intent(TercerActivity.this, MainActivity.class));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_contuctor, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()){
            case R.id.logoutMenu:{
                Logout();
                break;
            }
            case R.id.mapsMenu: {
                startActivity(new Intent(TercerActivity.this, GMuber.class));
                break;
            }
            case R.id.datosconductor: {
                startActivity(new Intent(TercerActivity.this, ConductorPerfil.class));
                break;
            }
            case R.id.reportIngresos: {
                startActivity(new Intent(TercerActivity.this, MainIngreso.class));
                break;
            }
            case R.id.reportTrayectos: {
                startActivity(new Intent(TercerActivity.this, MainTrayecto.class));
                break;
            }
            case R.id.datosvehiculo: {
                startActivity(new Intent(TercerActivity.this, VehiculoPerfil.class));
                break;
            }
            case R.id.Updateperfil: {
                startActivity(new Intent(TercerActivity.this, UpdateProfile.class));
                break;
            }
            case R.id.refreshMenu:
                startActivity(new Intent(TercerActivity.this, SecondActivity.class));
                break;

        }
        return super.onOptionsItemSelected(item);
    }
}