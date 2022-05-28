package com.example.a2021_mid1_2017305045_;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Chat2Activity extends AppCompatActivity {
    TextView sender;
    EditText reply;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat2);
        setTitle("2017305045_Chatter2");
        Intent intent = getIntent();
        sender = (TextView) findViewById(R.id.sender);
        String message = intent.getStringExtra("msg");
        //Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
        sender.setText("Received : " + message);
    }

    public void sendreply(View view) {
        Intent intent = new Intent();
        reply = (EditText) findViewById(R.id.reply);
        intent.putExtra("reply", reply.getText().toString());
        setResult(RESULT_OK, intent);
        finish();
    }
}