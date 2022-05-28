package com.example.servicetest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void goService1(View view) {
        Intent intent = new Intent(MainActivity.this, Service1Activity.class);
        startActivity(intent);
    }
    public void goService2(View view) {
        Intent intent = new Intent(MainActivity.this, Service2Activity.class);
        startActivity(intent);
    }
    public void goService3(View view) {
        Intent intent = new Intent(MainActivity.this, Service3Activity.class);
        startActivity(intent);
    }
    public void goService4(View view) {
        Intent intent = new Intent(MainActivity.this, Service4Activity.class);
        startActivity(intent);
    }
    public void goService5(View view) {
        Intent intent = new Intent(MainActivity.this, ReceiverActivity.class);
        startActivity(intent);
    }
}