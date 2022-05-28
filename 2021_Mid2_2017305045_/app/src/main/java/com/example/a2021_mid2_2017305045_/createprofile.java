package com.example.a2021_mid2_2017305045_;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class createprofile extends AppCompatActivity {

    EditText email, name, phone, house;
    DBHelper helper;
    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.createprofile);
        setTitle("프로필 입력(염동빈)");

        helper = new DBHelper(this);
        try {
            db = helper.getWritableDatabase();
        } catch (SQLiteException ex) {
            db = helper.getReadableDatabase();
        }

        email = (EditText) findViewById(R.id.email);
        name = (EditText) findViewById(R.id.name);
        phone = (EditText) findViewById(R.id.phone);
        house = (EditText) findViewById(R.id.house);
    }

    public void save(View view) {
        db.execSQL("INSERT INTO Profile2 VALUES ('" + phone.getText().toString() + "', '" + email.getText().toString() + "', '" + name.getText().toString() + "', '" + house.getText().toString() +"');");
        finish();
    }

}