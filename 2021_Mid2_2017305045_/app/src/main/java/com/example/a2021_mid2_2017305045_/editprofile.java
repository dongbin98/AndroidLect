package com.example.a2021_mid2_2017305045_;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class editprofile extends AppCompatActivity {

    EditText email, name, phone, house;
    DBHelper helper;
    SQLiteDatabase db;
    String oldnum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editprofile);
        setTitle("프로필 갱신(염동빈)");

        Intent intent = getIntent();
        oldnum = intent.getStringExtra("phone");

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
        if(oldnum == phone.getText().toString()) {
            db.execSQL("UPDATE Profile2 set name = '" + name + "', email = '" + email + "', house = '" + house + "' Where number = '" + phone + "';");
            finish();
        }
        else {
            db.execSQL("DELETE FROM Profile2 Where number = '" + oldnum + "';");
            db.execSQL("INSERT INTO Profile2 VALUES ('" + phone.getText().toString() + "', '" + email.getText().toString() + "', '" + name.getText().toString() + "', '" + house.getText().toString() +"');");
            finish();
        }
    }
}