package com.example.servicetest;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Service3Activity extends AppCompatActivity {
    LocalService mService;
    boolean mBound = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service3);
    }
    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = new Intent(this, LocalService.class);
        bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
    }
    @Override
    protected void onStop() {
        super.onStop();
        if(mBound) {
            unbindService(mConnection);
            mBound = false;
        }
    }
    public void onButtonClick(View v) {
        if(mBound) {
            int num = mService.getRandomNumber();
            Toast.makeText(this, "number : " + num, Toast.LENGTH_SHORT).show();
        }
    }
    private ServiceConnection mConnection = new ServiceConnection() {
        //@Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            LocalService.LocalBinder binder = (LocalService.LocalBinder) iBinder;
            mService = binder.getService();
            mBound = true;
        }
        //@Override
        public void onServiceDisconnected(ComponentName arg0) {
            mBound = false;
        }
    };
}
