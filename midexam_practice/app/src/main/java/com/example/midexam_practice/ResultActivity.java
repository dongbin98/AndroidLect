package com.example.midexam_practice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result);
        TextView stand = (TextView)findViewById(R.id.stand);
        TextView jump = (TextView)findViewById(R.id.jump);
        TextView walk = (TextView)findViewById(R.id.walk);
        Intent intent = getIntent();
        stand.setText("stand는 " + intent.getIntExtra("STAND",0) + "표");
        jump.setText("jump는 " + intent.getIntExtra("JUMP",0) + "표");
        walk.setText("walk는 " + intent.getIntExtra("WALK",0) + "표");
    }

    public void back(View view) {
        finish();
    }
}