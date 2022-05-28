package com.example.mediaplayertest;

import android.Manifest;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import java.io.IOException;

public class RecordActivity extends AppCompatActivity {

    private static final String LOG_TAG = "AudioRecordTest";
    private static final int REQUEST_RECORD_AUDIO_PERMISSION = 200;
    private static String filename = null;

    Button Record, Play;

    private boolean permissionToRecordAccepted = false;
    private String[] permissions = {Manifest.permission.RECORD_AUDIO};

    private MediaPlayer player = null;
    private MediaRecorder recorder = null;

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST_RECORD_AUDIO_PERMISSION:
                permissionToRecordAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                break;
        }
        if(!permissionToRecordAccepted) finish();
    }

    private void onRecord(boolean start) {
        if(start) { startRecording(); }
        else { stopRecording(); }
    }
    private void onPlay(boolean start) {
        if(start) { startPlaying(); }
        else { stopPlaying(); }
    }
    private void startRecording() {
        recorder = new MediaRecorder();
        recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        recorder.setOutputFile(filename);
        recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);

        try { recorder.prepare();}
        catch (IOException e) { Log.e(LOG_TAG, "prepare() failed");}
        recorder.start();
    }
    private void stopRecording() {
        recorder.stop();
        recorder.release();
        recorder = null;
    }
    private void startPlaying() {
        player = new MediaPlayer();
        try {
            player.setDataSource(filename);
            player.prepare();
            player.start();
        } catch (IOException e) {
            Log.e(LOG_TAG, "prepare() failed");
        }
    }
    private void stopPlaying() {
        player.release();
        player = null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);
        super.setTitle("염동빈_2017305045");

        filename = getExternalCacheDir().getAbsolutePath();
        filename += "/audiorecordtest.3gp";

        ActivityCompat.requestPermissions(this, permissions, REQUEST_RECORD_AUDIO_PERMISSION);

        Record = (Button)findViewById(R.id.Recording);
        Record.setOnClickListener(new View.OnClickListener() {
            boolean start = true;
            @Override
            public void onClick(View view) {
                onRecord(start);
                if (start)
                    Record.setText("녹음 중지");
                else
                    Record.setText("녹음 시작");
                start = !start;
            }
        });
        Play = (Button)findViewById(R.id.RecordPlay);
        Play.setOnClickListener(new View.OnClickListener() {
            boolean start = true;
            @Override
            public void onClick(View view) {
                onPlay(start);
                if (start)
                    Play.setText("재생 중지");
                else
                    Play.setText("재생 시작");
                start = !start;
            }
        });
    }
}
