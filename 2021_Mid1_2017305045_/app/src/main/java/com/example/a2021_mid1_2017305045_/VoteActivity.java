package com.example.a2021_mid1_2017305045_;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

public class VoteActivity extends AppCompatActivity {
    int cat;
    int jumpcat;
    int sitcat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vote);
        setTitle("2017305045_Vote");
    }

    public void vote(View view) {
        RadioButton cat1 = (RadioButton) findViewById(R.id.cat);
        RadioButton cat2 = (RadioButton) findViewById(R.id.cat2);
        RadioButton cat3 = (RadioButton) findViewById(R.id.cat3);

        if(cat1.isChecked()) {
            cat++;
            Toast.makeText(getApplicationContext(),"cat " + cat + "표", Toast.LENGTH_SHORT).show();
        }
        else if(cat2.isChecked()) {
            sitcat++;
            Toast.makeText(getApplicationContext(),"sit cat " + sitcat + "표", Toast.LENGTH_SHORT).show();
        }
        else if(cat3.isChecked()) {
            jumpcat++;
            Toast.makeText(getApplicationContext(),"jump cat " + jumpcat + "표", Toast.LENGTH_SHORT).show();
        }
    }

    public void result(View view) {
        Intent intent = new Intent(VoteActivity.this, VoteResultActivity.class);
        intent.putExtra("cat", cat);
        intent.putExtra("sitcat", sitcat);
        intent.putExtra("jumpcat", jumpcat);
        startActivity(intent);
    }
}