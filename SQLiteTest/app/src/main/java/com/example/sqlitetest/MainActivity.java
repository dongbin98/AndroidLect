package com.example.sqlitetest;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

class DBHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "lecture.db";
    private static final int DB_VERSION = 2;

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE contacts ( _id INTEGER PRIMARY KEY AUTOINCREMENT, title TEXT, count TEXT)");
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS contacts");
        onCreate(db);
    }
}

public class MainActivity extends AppCompatActivity {
    DBHelper helper;
    SQLiteDatabase db;
    EditText edit_title, edit_count, title_board, count_board;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("수업 관리 DB");

        helper = new DBHelper(this);
        try {
            db = helper.getWritableDatabase();
        } catch (SQLiteException e) {
            db = helper.getReadableDatabase();
        }

        edit_title = (EditText) findViewById(R.id.title);
        edit_count = (EditText) findViewById(R.id.count);
        title_board = (EditText) findViewById(R.id.titleboard);
        count_board = (EditText) findViewById(R.id.countboard);
    }
    public void clear(View target) {
        db.execSQL("DELETE FROM contacts");
        title_board.setText("");
        count_board.setText("");
    }
    public void insert(View target) {
        String title = edit_title.getText().toString();
        String count = edit_count.getText().toString();
        if(edit_title.getText().length() == 0 || edit_count.getText().length() == 0) {
            Toast.makeText(getApplicationContext(), "삽입할 것을 입력하세요", Toast.LENGTH_SHORT).show();
            return;
        }
        // 해당 튜플 삽입
        db.execSQL("INSERT INTO contacts VALUES (null, '" + title + "', '" + count + "');");
        edit_title.setText("");
        edit_count.setText("");
        // 테이블 조회
        select(target);
    }
    public void update(View target) {
        String title = edit_title.getText().toString();
        String count = edit_count.getText().toString();
        if(edit_title.getText().length() == 0 || edit_count.getText().length() == 0) {
            Toast.makeText(getApplicationContext(), "수정할 것을 입력하세요", Toast.LENGTH_SHORT).show();
            return;
        }
        // 해당 튜플 수정
        db.execSQL("UPDATE contacts set count = '" + count + "' where title = '" + title + "';");
        edit_title.setText("");
        edit_count.setText("");
        // 테이블 조회
        select(target);
    }
    public void delete(View target) {
        String title = edit_title.getText().toString();
        if(edit_title.getText().length() == 0 ) {
            Toast.makeText(getApplicationContext(), "삭제할 것을 입력하세요", Toast.LENGTH_SHORT).show();
            return;
        }
        // 해당 튜플 삭제
        db.execSQL("DELETE FROM contacts WHERE title ='" + title + "';");
        edit_title.setText("");
        // 테이블 조회
        select(target);
    }
    public void select(View target) {
        Cursor cursor = db.rawQuery("SELECT title, count FROM contacts", null);
        title_board.setText("수업명");
        count_board.setText("인원");
        while(cursor.moveToNext()) {
            String t = cursor.getString(0);
            String c = cursor.getString(1);
            title_board.append("\n" + t);
            count_board.append("\n" + c);
        }
    }
}