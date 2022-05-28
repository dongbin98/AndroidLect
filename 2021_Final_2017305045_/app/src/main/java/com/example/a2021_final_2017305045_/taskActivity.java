package com.example.a2021_final_2017305045_;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class taskActivity extends AppCompatActivity {
    boolean emergency;
    private final int GET_GALLERY_IMAGE = 100;
    private int mYear =0, mMonth=0, mDay=0;
    Button imagechoice, datepicker, timepicker;
    ImageView imageview;
    EditText edit1;
    String buttonname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);
        setTitle("2021년 기말 염동빈_2017305045");
        emergency = false;
        imagechoice = (Button) findViewById(R.id.choice);
        datepicker = (Button) findViewById(R.id.datepicker);
        timepicker = (Button) findViewById(R.id.timepicker);
        imageview = (ImageView) findViewById(R.id.image);
        edit1 = (EditText) findViewById(R.id.name);

        imagechoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,"image/*");
                startActivityForResult(intent, GET_GALLERY_IMAGE);
            }
        });
        datepicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar c = Calendar.getInstance();
                DatePickerDialog datePickerDialog = new DatePickerDialog(taskActivity.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth, new DatePickerDialog.OnDateSetListener(){
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                        buttonname = year+"-"+(monthOfYear+1)+"-"+dayOfMonth;
                        datepicker.setText(buttonname);
                    }
                }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.getDatePicker().setCalendarViewShown(false);
                datePickerDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                datePickerDialog.show();
            }
        });
        timepicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar c = Calendar.getInstance();
                int mHour = c.get(Calendar.HOUR_OF_DAY);
                int mMinute = c.get(Calendar.MINUTE);

                TimePickerDialog timePickerDialog = new TimePickerDialog(taskActivity.this, new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute)
                            {
                                buttonname = hourOfDay + ":" + minute;
                                timepicker.setText(buttonname);
                            }
                        }, mHour, mMinute, false);
                timePickerDialog.show();
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == GET_GALLERY_IMAGE && resultCode == RESULT_OK && data != null && data.getData() != null);
        Uri selectedImageUri = data.getData();
        imageview.setImageURI(selectedImageUri);
    }
    public void checkbox(View v) {
        boolean checked = ((CheckBox)v).isChecked();
        switch(v.getId()) {
            case R.id.emerg:
                if(checked) emergency = true;
                else emergency = false;
                break;
        }
    }
    public void save(View v) {
        Intent intent = new Intent();
        String s;
        if(emergency) s = "긴급) " + edit1.getText().toString() + "\n" + datepicker.getText().toString() + " // " + timepicker.getText().toString();
        else s = edit1.getText().toString() + "\n" + datepicker.getText().toString();
        Toast.makeText(this, s + "저장", Toast.LENGTH_SHORT).show();
        intent.putExtra("VALUE", s);
        setResult(RESULT_OK, intent);
        finish();
    }
}
