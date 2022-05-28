package com.example.servicetest;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Service1Activity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "MusicService";
    Button start, stop;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service1);

        start = (Button)findViewById(R.id.startBtn);
        stop = (Button)findViewById(R.id.stopBtn);

        start.setOnClickListener(this);
        stop.setOnClickListener(this);
    }

    public void onClick(View src) {
        switch(src.getId()) {
            case R.id.startBtn:
                startService(new Intent(this, MusicService.class));
                break;
            case R.id.stopBtn:
                stopService(new Intent(this, MusicService.class));
                break;
        }
    }
}
