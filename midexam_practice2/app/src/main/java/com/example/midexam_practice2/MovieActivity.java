package com.example.midexam_practice2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Movie;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class MovieActivity extends AppCompatActivity {
    ImageButton[] btn = new ImageButton[9];
    int[] count = new int[9];
    int[] position = {0, 1, 2, 3, 4, 5, 6, 7, 8};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);
        btn[0] = (ImageButton) findViewById(R.id.img1);
        btn[1] = (ImageButton) findViewById(R.id.img2);
        btn[2] = (ImageButton) findViewById(R.id.img3);
        btn[3] = (ImageButton) findViewById(R.id.img4);
        btn[4] = (ImageButton) findViewById(R.id.img5);
        btn[5] = (ImageButton) findViewById(R.id.img6);
        btn[6] = (ImageButton) findViewById(R.id.img7);
        btn[7] = (ImageButton) findViewById(R.id.img8);
        btn[8] = (ImageButton) findViewById(R.id.img9);

        for(int p : position) {
            btn[p].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    count[p]++;
                    Toast.makeText(getApplicationContext(), (p+1) + "번" + count[p] + "표", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
    public void goResult(View view) {
        Intent intent = new Intent(MovieActivity.this, ResultActivity.class);
        intent.putExtra("pic1", count[0]);
        intent.putExtra("pic2", count[1]);
        intent.putExtra("pic3", count[2]);
        intent.putExtra("pic4", count[3]);
        intent.putExtra("pic5", count[4]);
        intent.putExtra("pic6", count[5]);
        intent.putExtra("pic7", count[6]);
        intent.putExtra("pic8", count[7]);
        intent.putExtra("pic9", count[8]);
        startActivity(intent);
    }
}