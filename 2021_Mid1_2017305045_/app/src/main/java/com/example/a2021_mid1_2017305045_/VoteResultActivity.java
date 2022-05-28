package com.example.a2021_mid1_2017305045_;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class VoteResultActivity extends AppCompatActivity {

    TextView view1, view2, view3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vote_result);
        setTitle("2017305045_Vote_Result");
        view1 = (TextView) findViewById(R.id.view1);
        view2 = (TextView) findViewById(R.id.view2);
        view3 = (TextView) findViewById(R.id.view3);
        Intent intent = getIntent();
        view1.setText("cat : " + intent.getIntExtra("cat",0) + "표");
        view2.setText("sitcat : " + intent.getIntExtra("sitcat",0) + "표");
        view3.setText("jumpcat : " + intent.getIntExtra("jumpcat",0) + "표");
    }

    public void back(View view) {
        finish();
    }
}