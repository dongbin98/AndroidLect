package com.example.a2021_mid1_2017305045_;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class Chat1Activity extends AppCompatActivity {
    static final int GET_STRING = 1;
    TextView receiver;
    EditText msg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat1);
        setTitle("2017305045_Chatter1");
        receiver = (TextView) findViewById(R.id.receiver);
    }

    public void sendmsg(View view) {
        msg = (EditText) findViewById(R.id.msg);
        Intent intent = new Intent(Chat1Activity.this, Chat2Activity.class);
        intent.putExtra("msg", msg.getText().toString());
        msg.setText("");
        startActivityForResult(intent,GET_STRING);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == GET_STRING) {
            if(resultCode == RESULT_OK) {
                receiver.setText("Received : " + data.getStringExtra("reply"));
            }
        }
    }
}