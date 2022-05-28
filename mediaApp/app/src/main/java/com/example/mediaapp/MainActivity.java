package com.example.mediaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    MediaPlayer mp = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startResAudio(View v) {
        mp = MediaPlayer.create(this, R.raw.music);
        mp.start();
    }

    public void stopResAudio(View v) {
        if(mp != null) {
            mp.stop();
            mp.release();
        }
        mp = null;
    }
}