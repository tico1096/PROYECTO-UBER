package com.example.android.logindemo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

public class DeveloperBD extends SQLiteOpenHelper {
    private static final String NOMBRE_BD="developer.bd";
    private static final int VERSION_BD=1;
    private static final String TABLA_CONDUCTORES="CREATE TABLE CONDUCTORES(CEDULA TEXT PRIMARY KEY, NOMBRE TEXT, MARCA TEXT, MODELO TEXT, PLACA TEXT)";

    public DeveloperBD(@Nullable Context context) {
        super(context, NOMBRE_BD, null, VERSION_BD);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(TABLA_CONDUCTORES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS"+TABLA_CONDUCTORES);
        sqLiteDatabase.execSQL(TABLA_CONDUCTORES);
    }
    public void agregarConductor(String cedula, String nombre, String marca, String modelo, String placa){
        SQLiteDatabase bd=getWritableDatabase();
        if (bd!=null){
            bd.execSQL("INSERT INTO CURSOS VALUES('"+cedula+"','"+nombre+"','"+marca+"','"+modelo+"','"+placa+"')");
            bd.close();
        }
    }
}
