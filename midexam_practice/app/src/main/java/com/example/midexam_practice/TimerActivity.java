package com.example.midexam_practice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.SystemClock;
import android.view.View;
import android.widget.Chronometer;
import android.widget.TextView;

public class TimerActivity extends AppCompatActivity {
    private Chronometer chronometer;
    private boolean isRun, first=true;
    private long pauseValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.timer);
        chronometer = findViewById(R.id.board);
        chronometer.setFormat("경과시간=%s");
    }

    public void start(View view) {
        if(first)
            chronometer.setBase(SystemClock.elapsedRealtime());
        //if (!isRun) {
            //chronometer.setBase(SystemClock.elapsedRealtime() - pauseValue);
            chronometer.start();
            first = false;
            //isRun = true;
        //}
    }

    public void stop(View view) {
        //if(isRun)
            //pauseValue = SystemClock.elapsedRealtime() - chronometer.getBase();
            chronometer.stop();
    }

    public void clear(View view) {
        chronometer.setBase(SystemClock.elapsedRealtime());
        first = true;
    }
}