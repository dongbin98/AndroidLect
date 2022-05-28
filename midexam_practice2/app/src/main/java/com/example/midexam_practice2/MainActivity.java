package com.example.midexam_practice2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void Class(View view) {
        Intent intent = new Intent(MainActivity.this, ClassActivity.class);
        startActivity(intent);
    }

    public void Date(View view) {
        Intent intent = new Intent(MainActivity.this, DateActivity.class);
        startActivity(intent);
    }

    public void Movie(View view) {
        Intent intent = new Intent(MainActivity.this, MovieActivity.class);
        startActivity(intent);
    }

    public void Exit(View view) {
        // 다이얼로그 빌더 선언(다이얼로그 세팅)
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("프로그램을 종료할까요?");
        alertDialogBuilder.setPositiveButton("네", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        alertDialogBuilder.setNegativeButton("아니오", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        // 다이얼로그 생성 및 보이기
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
}