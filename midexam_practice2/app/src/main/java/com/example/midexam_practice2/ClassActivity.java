package com.example.midexam_practice2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

class DBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "myDB_2017305045";
    private static final int DATABASE_VERSION = 2;

    public  DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL("Create TABLE myAdress_염동빈 (hakbun INTEGER PRIMARY KEY, name CHAR(10), address CHAR(50));");
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS myAdress_염동빈");
        onCreate(db);
    }
}

public class ClassActivity extends AppCompatActivity {
    DBHelper helper;
    SQLiteDatabase db;
    EditText edit_year, edit_name, edit_city;
    EditText result1, result2, result3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class);
        helper = new DBHelper(this);
        try {
            db = helper.getWritableDatabase();
        } catch(SQLiteException ex) {
            db = helper.getReadableDatabase();
        }
        edit_year = (EditText) findViewById(R.id.edit_year);
        edit_name = (EditText) findViewById(R.id.edit_name);
        edit_city = (EditText) findViewById(R.id.edit_city);
        result1 = (EditText) findViewById(R.id.edit_result1);
        result2 = (EditText) findViewById(R.id.edit_result2);
        result3 = (EditText) findViewById(R.id.edit_result3);
    }
    public void clear(View target) {
        db.execSQL("DELETE FROM myAdress_염동빈;");
        result1.setText("학번");
        result2.setText("이름");
        result3.setText("주소");
        search(target);
    }

    public void search(View target) {
        Cursor cursor = db.rawQuery("SELECT * FROM myAdress_염동빈", null);
        result1.setText("학번");
        result2.setText("이름");
        result3.setText("주소");
        while(cursor.moveToNext()) {
            result1.append("\n" + cursor.getString(0));
            result2.append("\n" + cursor.getString(1));
            result3.append("\n" + cursor.getString(2));
        }
    }

    public void save(View target) {
        String year = edit_year.getText().toString();
        String name = edit_name.getText().toString();
        String city = edit_city.getText().toString();
        db.execSQL("INSERT INTO myAdress_염동빈 VALUES ('" + year +"','" + name +"','" + city + "');");
        edit_year.setText("");
        edit_name.setText("");
        edit_city.setText("");
        search(target);
    }

    public void update(View target) {
        String year = edit_year.getText().toString();
        String name = edit_name.getText().toString();
        String city = edit_city.getText().toString();
        db.execSQL("UPDATE myAdress_염동빈 set name = '" + name + "', address = '" + city + "' where hakbun = '" + year + "';");
        search(target);
    }

    public void delete(View target) {
        String year = edit_year.getText().toString();
        db.execSQL("DELETE FROM myAdress_염동빈 WHERE hakbun = '" + year + "';");
        search(target);
    }
}