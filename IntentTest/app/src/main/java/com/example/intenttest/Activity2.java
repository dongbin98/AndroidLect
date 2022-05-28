package com.example.intenttest;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Activity2 extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout2);

        Intent intent = getIntent();    // Activity1로부터 인텐트 받아옴
        double op1 = intent.getDoubleExtra("op1", 0);
        double op2 = intent.getDoubleExtra("op2", 0);
        char oper = intent.getCharExtra("oper", '+');

        double result;

        if(oper == '+')
            result = op1 + op2;
        else if(oper == '-')
            result = op1 - op2;
        else if(oper == '*')
            result = op1 * op2;
        else
            result = op1 / op2;

        Button b = (Button)findViewById(R.id.back);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent();
                intent.putExtra("RESULT", Double.toString(result));
                setResult(RESULT_OK, intent);

                finish();
            }
        });
    }
}