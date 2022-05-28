package com.example.a2021_final_2017305045_;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private final int LIST_VALUE = 100;

    ArrayList<String> listItem;
    ArrayAdapter<String> adapter;
    ListView listview;
    int selectedItem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("염동빈_2017305045");
        setContentView(R.layout.activity_main);

        listItem = new ArrayList<String>();
        listview = (ListView) findViewById(R.id.listview);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_single_choice, listItem);
        listview.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        listview.setAdapter(adapter);
        //setListViewHeightBasedOnChildren(listview);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                selectedItem = i;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.optionmenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.add:
                Intent intent = new Intent(MainActivity.this, taskActivity.class);
                startActivityForResult(intent, LIST_VALUE);
                return true;
            case R.id.delete:
                listItem.remove(selectedItem);
                adapter.notifyDataSetChanged();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == LIST_VALUE && resultCode == RESULT_OK );
        {
            listItem.add(data.getStringExtra("VALUE"));
            adapter.notifyDataSetChanged();
        }
    }
}