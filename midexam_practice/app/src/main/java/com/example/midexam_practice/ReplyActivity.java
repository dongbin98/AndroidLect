package com.example.midexam_practice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ReplyActivity extends AppCompatActivity {

    TextView msg;
    EditText sendreply;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reply);
        msg = (TextView) findViewById(R.id.msg);
        Intent in = getIntent();
        String message = in.getStringExtra("SEND");
        //Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
        msg.setText(message);
    }

    public void send(View view) {
        Intent intent = new Intent();
        sendreply = (EditText) findViewById(R.id.sendreply);
        intent.putExtra("INPUT_TEXT", sendreply.getText().toString());
        setResult(RESULT_OK, intent);
        finish();
    }
}
