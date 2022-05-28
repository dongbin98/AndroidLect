package com.example.paint;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

class MyView extends View {
    int key;
    String str;
    int x, y;

    public MyView(Context context) {
        super(context);
        setBackgroundColor(Color.YELLOW);
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        x = (int) event.getX(0);
        y = (int) event.getY(0);
        invalidate();
        return super.onTouchEvent(event);
    }
    @Override
    protected void onDraw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setTextSize(50);
        canvas.drawCircle(x, y, 30, paint);
        canvas.drawText("("+x+ ", "+y+") 에서 터치 이벤트가 발생하였음", x, y+100, paint);
    }
}

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        MyView w = new MyView(this);
//        setContentView(w);
        Button button = (Button) findViewById(R.id.button1);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "2017305045 염동빈", Toast.LENGTH_SHORT).show();
            }
        });
        final EditText eText;
        Button btn;
        eText = (EditText) findViewById(R.id.edittext);
        btn = (Button) findViewById(R.id.button3);
        btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String str = eText.getText().toString();
                Toast.makeText(getBaseContext(), str, Toast.LENGTH_LONG).show();
            }
        });
        final RatingBar ratingbar = (RatingBar) findViewById(R.id.ratingbar);
        ratingbar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                Toast.makeText(getApplicationContext(), "New Rating: " + rating, Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void onClick(View target) {
        Toast.makeText(getApplicationContext(), "응애", Toast.LENGTH_SHORT).show();
    }

    public void onCheckboxClicked(View view) {
        boolean checked = ((CheckBox) view).isChecked();

        switch(view.getId()) {
            case R.id.checkbox_meat:
                if(checked)
                    Toast.makeText(getApplicationContext(), "고기 선택", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(getApplicationContext(), "고기 선택 해제", Toast.LENGTH_SHORT).show();
                break;

            case R.id.checkbox_cheese:
                if(checked)
                    Toast.makeText(getApplicationContext(), "치즈 선택", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(getApplicationContext(), "치즈 선택 해제", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}