package com.example.android.logindemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class SecondActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    private Button logout, driving, client;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        firebaseAuth = FirebaseAuth.getInstance();

        logout = (Button)findViewById(R.id.btnLogout);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Logout();
            }
        });

        driving = (Button)findViewById(R.id.btnDriving);

        driving.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Driving();
            }
        });

        client = (Button)findViewById(R.id.btnClient);

        client.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Client();
            }
        });

    }


    private void Logout(){
        firebaseAuth.signOut();
        finish();
        startActivity(new Intent(SecondActivity.this, MainActivity.class));
    }
    private void Driving(){
        firebaseAuth.signOut();
        finish();
        startActivity(new Intent(SecondActivity.this, TercerActivity.class));
    }
    private void Client(){
        firebaseAuth.signOut();
        finish();
        startActivity(new Intent(SecondActivity.this, CuartaActivity.class));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()){

            case R.id.refreshMenu:
                startActivity(new Intent(SecondActivity.this, Perfil_usuario.class));
                break;

        }
        return super.onOptionsItemSelected(item);
    }
}
