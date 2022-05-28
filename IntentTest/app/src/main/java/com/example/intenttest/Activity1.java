package com.example.intenttest;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class Activity1 extends Activity {

    static final int GET_STRING = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // layout1 을 뷰로 잡음
        setContentView(R.layout.layout1);
        Button b = (Button)findViewById(R.id.operation);
        b.setOnClickListener(new View.OnClickListener() {
            @Override   // 버튼 클릭 시
            public void onClick(View v) {
                // 각 위젯에 대한 변수 선언
                EditText edit1 = (EditText)findViewById(R.id.op1);
                EditText edit2 = (EditText)findViewById(R.id.op2);
                RadioButton plus = (RadioButton)findViewById(R.id.plus);
                RadioButton minus = (RadioButton)findViewById(R.id.minus);
                RadioButton multi = (RadioButton)findViewById(R.id.multiply);
                RadioButton divide = (RadioButton)findViewById(R.id.divide);
                // 피연산자 값을 넣지 않을 때
                if (edit1.getText().length() == 0 || edit2.getText().length() == 0) {
                    Toast.makeText(getApplicationContext(), "피연산자 값을 입력하세요", Toast.LENGTH_SHORT).show();
                    return;
                }
                double op1 = Double.parseDouble(edit1.getText().toString());
                double op2 = Double.parseDouble(edit2.getText().toString());
                Intent intent = new Intent(Activity1.this, Activity2.class);
                if (plus.isChecked())   // 연산기호 넘겨줌
                    intent.putExtra("oper", '+');
                else if (minus.isChecked())
                    intent.putExtra("oper", '-');
                else if (multi.isChecked())
                    intent.putExtra("oper", '*');
                else if (divide.isChecked())
                    intent.putExtra("oper", '/');
                else {  // 연산자를 선택하지 않았을 때
                    Toast.makeText(getApplicationContext(), "연산자를 선택하세요", Toast.LENGTH_SHORT).show();
                    return;
                }
                intent.putExtra("op1", op1);    // 두 숫자
                intent.putExtra("op2", op2);    // 넘겨줌
                startActivityForResult(intent, GET_STRING);
            }
        });
    }

    @Override   // 결과를 받아서 토스트 메시지로 나타냄
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == GET_STRING) {
            if(resultCode == RESULT_OK) {
                Toast.makeText(getApplicationContext(), data.getStringExtra("RESULT"), Toast.LENGTH_SHORT).show();
            }
        }
    }
}