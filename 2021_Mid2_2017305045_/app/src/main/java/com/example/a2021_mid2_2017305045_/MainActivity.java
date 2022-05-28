package com.example.a2021_mid2_2017305045_;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;

class DBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "myDB2";
    private static final int DATABASE_VERSION = 2;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("Create TABLE Profile2 (number VARCHAR(100) PRIMARY KEY, email VARCHAR(500), name VARCHAR(100), house VARCHAR(100));");
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Profile2");
        onCreate(db);
    }
}

public class MainActivity extends AppCompatActivity {

    DBHelper helper;
    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("(2017305045)_(염동빈)_중간고사 2");
        //db.execSQL("DROP TABLE IF EXISTS Profile");
    }

    public void create(View view) {
        Intent intent = new Intent(MainActivity.this, createprofile.class);
        startActivity(intent);
    }

    public void update(View view) {
        Intent intent = new Intent(MainActivity.this, before_edit.class);
        startActivity(intent);
    }

    public void delete(View view) {
        Intent intent = new Intent(MainActivity.this, deleteprofile.class);
        startActivity(intent);
    }

    public void search(View view) {
        Intent intent = new Intent(MainActivity.this, query.class);
        startActivity(intent);
    }
}