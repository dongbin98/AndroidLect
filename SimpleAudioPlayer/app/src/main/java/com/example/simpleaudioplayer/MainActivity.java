package com.example.simpleaudioplayer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    MediaPlayer mp = null;
    Button pauseBtn;
    TextView title;
    ArrayList<String> mp3List;
    private static final String mp3Path = new String("/mnt/sdcard/");
    String SelectedMP3;
    ProgressBar loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        super.setTitle("염동빈_2017305045");
        // 외부저장소 퍼미션 체크 (안넣어도 실행되는 듯)
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.READ_EXTERNAL_STORAGE)) {
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
            }
        }
        // 외부저장소 절대경로 확인
        //String path = Environment.getExternalStorageDirectory().getAbsolutePath();
        //Toast.makeText(this,path,Toast.LENGTH_SHORT).show();

        title = (TextView) findViewById(R.id.title);
        loading = (ProgressBar) findViewById(R.id.loading);
        loading.setVisibility(View.GONE);
        // mp3List 생성 및 파일이름을 추가
        mp3List = new ArrayList<String>();
        File[] listFiles = new File(mp3Path).listFiles();
        String fileName, extName;
        for (File file : listFiles) {
            fileName = file.getName();
            extName = fileName.substring(fileName.length() - 3);
            if (extName.equals((String) "mp3"))
                mp3List.add(fileName);
        }
        // 리스트뷰에 라디오버튼 형식 어댑터 할당
        ListView listViewMP3 = (ListView) findViewById(R.id.listViewMP3);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_single_choice, mp3List);
        listViewMP3.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        listViewMP3.setAdapter(adapter);
        listViewMP3.setItemChecked(0, true);
        listViewMP3.setOnItemClickListener(new AdapterView.OnItemClickListener() {  // 리스트뷰 클릭 이벤트
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                SelectedMP3 = mp3List.get(arg2);    // 정수 순서 가져옴
            }
        });
        SelectedMP3 = mp3List.get(0);   // 디폴트로는 첫번째 목록 클릭
    }
    public void startResAudio(View v) throws IOException {
        if(mp != null) {
            mp.stop();
            mp.release();
        }
        loading.setVisibility(v.VISIBLE);
        mp = new MediaPlayer();
        mp.setDataSource(mp3Path + SelectedMP3);
        mp.prepare();
        mp.start();
        title.setText("실행중인 음악 : " + SelectedMP3);
    }
    public void pauseResAudio(View v) {
        pauseBtn = (Button) findViewById(R.id.pauseBtn);
        if(mp != null) {
            if(mp.isPlaying()) {
                mp.pause();
                loading.setVisibility(v.GONE);
                pauseBtn.setText("이어듣기");
            }
            else {
                mp.start();
                loading.setVisibility(v.VISIBLE);
                pauseBtn.setText("일시정지");
            } 
        }
    }
    public void stopResAudio(View v) {
        if(mp != null) {
            mp.stop();
            mp.release();
        }
        mp = null;
        loading.setVisibility(v.GONE);
        title.setText("실행중인 음악 : ");
        pauseBtn.setText("일시정지");
    }
}