package com.example.a2021_mid2_2017305045_;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class before_edit extends AppCompatActivity {
    DBHelper helper;
    SQLiteDatabase db;
    EditText editnum;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.before_edit);
        setTitle("프로필 수정(염동빈)");
        editnum = (EditText) findViewById(R.id.editnumber);
        helper = new DBHelper(this);
        try {
            db = helper.getWritableDatabase();
        } catch (SQLiteException ex) {
            db = helper.getReadableDatabase();
        }
    }

    public void check(View view) {
        Cursor cursor = db.rawQuery("SELECT * FROM Profile2", null);
        String check = null;
        while(cursor.moveToNext()) {
            check = cursor.getString(0);
        }
        if(check != null) {
            //Toast.makeText(getApplicationContext(), check, Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(before_edit.this, editprofile.class);
            intent.putExtra("phone", check);
            startActivity(intent);
        }
        else
            Toast.makeText(getApplicationContext(), "해당 되는 번호가 없습니다", Toast.LENGTH_SHORT).show();
    }
}