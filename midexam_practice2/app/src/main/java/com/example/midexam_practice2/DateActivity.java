package com.example.midexam_practice2;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

class DBHelper2 extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "database";
    private static final int DATABASE_VERSION = 2;

    public  DBHelper2(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE myDB_2017305045 (_id INTEGER PRIMARY KEY AUTOINCREMENT, diaryDate CHAR(10), content VARCHAR(500));");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS myDB_2017305045");
        onCreate(db);
    }

}

public class DateActivity extends AppCompatActivity {
    DatePicker date;
    EditText name;
    Button btn;
    DBHelper2 helper;
    SQLiteDatabase db;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date);
        helper = new DBHelper2(this);
        try {
            db = helper.getWritableDatabase();
        } catch (SQLiteException ex) {
            db = helper.getReadableDatabase();
        }
        btn = (Button) findViewById(R.id.save);
        date = (DatePicker) findViewById(R.id.calendar);
        name = (EditText) findViewById(R.id.name);

        date.setOnDateChangedListener(new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                String getYear = Integer.toString(date.getYear());
                String getMonth = Integer.toString(date.getMonth()+1);
                String getDay = Integer.toString(date.getDayOfMonth());
                String text = (getYear + getMonth + getDay);

                Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();

                String check1 = null;
                String check2 = null;

                Cursor cursor = db.rawQuery("SELECT diaryDate, content FROM myDB_2017305045 WHERE diaryDate = '" + text + "'", null);
                while(cursor.moveToNext()) {
                    check1 = cursor.getString(0);
                    check2 = cursor.getString(1);
                }
                if(check1 != null) {
                    //Toast.makeText(getApplicationContext(),check1+check2, Toast.LENGTH_SHORT).show();
                    btn.setText("수정");
                    name.setText(check2);
                    btn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String getYear = Integer.toString(date.getYear());
                            String getMonth = Integer.toString(date.getMonth()+1);
                            String getDay = Integer.toString(date.getDayOfMonth());
                            String title = name.getText().toString();
                            String text = (getYear + getMonth + getDay);

                            Toast.makeText(getApplicationContext(), text + title, Toast.LENGTH_SHORT).show();
                            db.execSQL("UPDATE myDB_2017305045 SET content = '" + title +"' WHERE diaryDate = '" + text + "';");
                        }
                     });
                }
                else {
                    btn.setText("저장");
                    name.setText("");
                    btn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String getYear = Integer.toString(date.getYear());
                            String getMonth = Integer.toString(date.getMonth()+1);
                            String getDay = Integer.toString(date.getDayOfMonth());
                            String title = name.getText().toString();
                            String text = (getYear + getMonth + getDay);

                            Toast.makeText(getApplicationContext(), text + title, Toast.LENGTH_SHORT).show();
                            db.execSQL("INSERT INTO myDB_2017305045 VALUES (null, '" + text + "', '" + title + "');");
                            btn.setText("수정");
                        }
                    });
                }
            }
        });
    }
}