package com.example.choicetest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("I Love Pet(염동빈)");
        setContentView(R.layout.activity_main);
        Button btn = (Button) findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ImageView img = (ImageView) findViewById((R.id.img));
                RadioButton dog = (RadioButton) findViewById(R.id.rb1);
                RadioButton cat = (RadioButton) findViewById(R.id.rb2);
                RadioButton duck = (RadioButton) findViewById(R.id.rb3);

                if (dog.isChecked())
                    img.setImageResource(R.drawable.dog);
                else if (cat.isChecked())
                    img.setImageResource(R.drawable.cat);
                else if (duck.isChecked())
                    img.setImageResource(R.drawable.duck);
                else {
                    Toast.makeText(getApplicationContext(), "동물을 선택하세요", Toast.LENGTH_SHORT).show();
                    return;
                }
                img.setVisibility(v.VISIBLE);
            }
        });
    }

    public void onClick(View v) {
        TextView text = (TextView) findViewById(R.id.text);
        RadioGroup group = (RadioGroup) findViewById(R.id.rbtns);
        Button btn = (Button) findViewById(R.id.btn);
        ImageView img = (ImageView) findViewById((R.id.img));

        boolean checked = ((CheckBox) v).isChecked();
        if(checked){
            text.setVisibility(v.VISIBLE);
            group.setVisibility(v.VISIBLE);
            btn.setVisibility((v.VISIBLE));
            btn.setClickable(true);
        }
        else {
            text.setVisibility(v.INVISIBLE);
            group.setVisibility(v.INVISIBLE);
            btn.setVisibility((v.INVISIBLE));
            btn.setClickable(false);
            img.setVisibility(v.INVISIBLE);
        }
    }
}