package com.example.a2021_mid2_2017305045_;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class deleteprofile extends AppCompatActivity {

    DBHelper helper;
    SQLiteDatabase db;
    EditText deletenum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.deleteprofile);
        setTitle("프로필 삭제(염동빈)");
        deletenum = (EditText) findViewById(R.id.deletenumber);
        helper = new DBHelper(this);
        try {
            db = helper.getWritableDatabase();
        } catch (SQLiteException ex) {
            db = helper.getReadableDatabase();
        }
    }

    public void deleteprofile(View view) {
        db.execSQL("DELETE FROM Profile2 Where number = '" + deletenum.getText().toString() + "';");
        finish();
    }
}