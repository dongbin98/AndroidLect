package com.example.midexam_practice2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.Rating;
import android.os.Bundle;
import android.view.View;
import android.widget.RatingBar;

public class ResultActivity extends AppCompatActivity {
    int[] point = new int[9];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        Intent intent = getIntent();
        point[0] = intent.getIntExtra("pic1", 0);
        point[1] = intent.getIntExtra("pic2", 0);
        point[2] = intent.getIntExtra("pic3", 0);
        point[3] = intent.getIntExtra("pic4", 0);
        point[4] = intent.getIntExtra("pic5", 0);
        point[5] = intent.getIntExtra("pic6", 0);
        point[6] = intent.getIntExtra("pic7", 0);
        point[7] = intent.getIntExtra("pic8", 0);
        point[8] = intent.getIntExtra("pic9", 0);

        RatingBar ratingBar1 = (RatingBar) findViewById(R.id.rating1);
        RatingBar ratingBar2 = (RatingBar) findViewById(R.id.rating2);
        RatingBar ratingBar3 = (RatingBar) findViewById(R.id.rating3);
        RatingBar ratingBar4 = (RatingBar) findViewById(R.id.rating4);
        RatingBar ratingBar5 = (RatingBar) findViewById(R.id.rating5);
        RatingBar ratingBar6 = (RatingBar) findViewById(R.id.rating6);
        RatingBar ratingBar7 = (RatingBar) findViewById(R.id.rating7);
        RatingBar ratingBar8 = (RatingBar) findViewById(R.id.rating8);
        RatingBar ratingBar9 = (RatingBar) findViewById(R.id.rating9);

        ratingBar1.setRating(point[0]);
        ratingBar2.setRating(point[1]);
        ratingBar3.setRating(point[2]);
        ratingBar4.setRating(point[3]);
        ratingBar5.setRating(point[4]);
        ratingBar6.setRating(point[5]);
        ratingBar7.setRating(point[6]);
        ratingBar8.setRating(point[7]);
        ratingBar9.setRating(point[8]);
    }
}