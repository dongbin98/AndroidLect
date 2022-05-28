package com.example.a2021_mid2_2017305045_;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class query extends AppCompatActivity {

    DBHelper helper;
    SQLiteDatabase db;
    TextView board;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.query);
        setTitle("저장된 프로필 : (염동빈)");
        board = (TextView) findViewById(R.id.board);
        board.setText("");
        helper = new DBHelper(this);
        try {
            db = helper.getWritableDatabase();
        } catch (SQLiteException ex) {
            db = helper.getReadableDatabase();
        }
        Cursor cursor = db.rawQuery("SELECT * FROM Profile2", null);
        while(cursor.moveToNext()) {
            board.append(cursor.getString(0) + "\n");
        }
    }
}