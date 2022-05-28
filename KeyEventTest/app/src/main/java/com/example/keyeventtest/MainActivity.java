package com.example.keyeventtest;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("(2017305045), 염동빈");
        final EditText eText;
        eText = (EditText) findViewById(R.id.edittext);
        eText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                String str = eText.getText().toString();
                Toast.makeText(getBaseContext(), str, Toast.LENGTH_SHORT).show();
                return true;
            }
        });
    }
}