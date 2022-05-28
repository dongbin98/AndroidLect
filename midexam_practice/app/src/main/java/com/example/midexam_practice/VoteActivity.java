package com.example.midexam_practice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.Toast;

public class VoteActivity extends AppCompatActivity {
    int standValue;
    int walkValue;
    int jumpValue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vote);
    }

    public void vote(View view) {
        RadioButton stand = (RadioButton) findViewById(R.id.stand);
        RadioButton jump = (RadioButton) findViewById(R.id.jump);
        RadioButton walk = (RadioButton) findViewById(R.id.walk);
        if(stand.isChecked()) {
            standValue++;
            Toast.makeText(getApplicationContext(), "서기 " + standValue + "표", Toast.LENGTH_SHORT).show();
        }
        else if(jump.isChecked()) {
            jumpValue++;
            Toast.makeText(getApplicationContext(), "점프 " + jumpValue + "표", Toast.LENGTH_SHORT).show();
        }
        else if(walk.isChecked()) {
            walkValue++;
            Toast.makeText(getApplicationContext(), "걷기 " + walkValue + "표", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(getApplicationContext(), "투표 똑바로 해라", Toast.LENGTH_SHORT).show();
        }
    }

    public void result(View view) {
        Intent intent = new Intent(VoteActivity.this, ResultActivity.class);
        intent.putExtra("STAND", standValue);
        intent.putExtra("JUMP", jumpValue);
        intent.putExtra("WALK", walkValue);
        setResult(RESULT_OK, intent);
        startActivity(intent);
    }

    public void Exit(View view) {
        finish();
    }
}