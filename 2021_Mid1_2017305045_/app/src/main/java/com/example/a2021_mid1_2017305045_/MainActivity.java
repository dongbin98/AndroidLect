package com.example.a2021_mid1_2017305045_;

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
        setTitle("(2017305045)_(염동빈)_2021 중간고사 1");
    }

    public void goVote(View view) {
        Intent intent = new Intent(MainActivity.this, VoteActivity.class);
        startActivity(intent);
    }

    public void goChat(View view) {
        Intent intent = new Intent(MainActivity.this, Chat1Activity.class);
        startActivity(intent);
    }

    public void Exit(View view) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("종료");
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
        AlertDialog dialog = alertDialogBuilder.create();
        dialog.show();
    }
}