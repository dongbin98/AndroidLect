package com.example.midexam_practice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ChatActivity extends AppCompatActivity {

    static final int GET_STRING = 1;
    TextView reply;
    EditText sendmsg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat);
    }

    public void send(View view) {
        sendmsg = (EditText) findViewById(R.id.sendmsg);
        Intent intent = new Intent(ChatActivity.this, ReplyActivity.class);
        intent.putExtra("SEND", sendmsg.getText().toString());
        sendmsg.setText("");
        //Toast.makeText(getApplicationContext(), sendmsg.getText().toString(), Toast.LENGTH_SHORT).show();
        startActivityForResult(intent, GET_STRING);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        reply = (TextView) findViewById(R.id.reply);
        if (requestCode == GET_STRING) {
            if (resultCode == RESULT_OK) {
                reply.setText(data.getStringExtra("INPUT_TEXT"));
            }
        }
    }
}
