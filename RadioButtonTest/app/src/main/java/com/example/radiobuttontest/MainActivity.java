package com.example.radiobuttontest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;
public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn = (Button) findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText edit1 = (EditText) findViewById(R.id.edit1);
                EditText edit2 = (EditText) findViewById(R.id.edit2);

                RadioButton plus = (RadioButton) findViewById(R.id.plus);
                RadioButton minus = (RadioButton) findViewById(R.id.minus);
                RadioButton multiple = (RadioButton) findViewById(R.id.multiple);
                RadioButton divide = (RadioButton) findViewById(R.id.divide);

                if (edit1.getText().length() == 0 || edit2.getText().length() == 0) {
                    Toast.makeText(getApplicationContext(), "피연산자 값을 입력하세요", Toast.LENGTH_SHORT).show();
                    return;
                }
                double op1 = Double.parseDouble(edit1.getText().toString());
                double op2 = Double.parseDouble(edit2.getText().toString());
                double result;
                if (plus.isChecked())
                    result = op1 + op2;
                else if (minus.isChecked())
                    result = op1 - op2;
                else if (multiple.isChecked())
                    result = op1 * op2;
                else if (divide.isChecked())
                    result = op1 / op2;
                else {
                    Toast.makeText(getApplicationContext(), "연산자를 선택하세요", Toast.LENGTH_SHORT).show();
                    return;
                }

                Toast.makeText(getApplicationContext(), Double.toString(result), Toast.LENGTH_SHORT).show();
            }
        });
    }
}