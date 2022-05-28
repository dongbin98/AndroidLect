package com.example.meunapp2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        MenuItem item1 = menu.add(0, 1, 0, "사과");
        item1.setAlphabeticShortcut('a');
        MenuItem item2 = menu.add(0, 2, 0, "포도");
        item2.setAlphabeticShortcut('g');
        MenuItem item3 = menu.add(0, 3, 0, "바나나");
        item3.setAlphabeticShortcut('b');
//        menu.add(0, 2, 0, "grape");
//        menu.add(0, 3, 0, "banana");
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 1:
                Toast.makeText(this, "사과", Toast.LENGTH_SHORT).show();
                return true;
            case 2:
                Toast.makeText(this, "포도", Toast.LENGTH_SHORT).show();
                return true;
            case 3:
                Toast.makeText(this, "바나나", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}