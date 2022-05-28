package com.example.viewapp;

import android.content.Context;
import android.content.DialogInterface;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    GridView grid;
    AlertDialog dialog;
    // 이미지 배열 16개 추가
    Integer[] images = {
            R.drawable.movie1, R.drawable.movie2, R.drawable.movie3, R.drawable.movie4,
            R.drawable.movie5, R.drawable.movie6, R.drawable.movie7, R.drawable.movie8,
            R.drawable.movie9, R.drawable.movie10, R.drawable.movie11, R.drawable.movie12,
            R.drawable.movie13, R.drawable.movie14, R.drawable.movie15, R.drawable.movie16
    };
    // 이벤트리스너 클래스 popupClass 생성
    class popupClass implements AdapterView.OnItemClickListener {
        private Context mContext;
        // 생성자를 통해 Context 가져오기
        public popupClass(Context c) {
            mContext = c;
        }
        // 클릭된 이미지의 position값을 통해 popupImg 함수의 인자로 넣어 호출
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            popUpImg(position);
        }
        // 이미지 팝업창 함수 선언
        public void popUpImg(int position) {
            // 해당되는 이미지 가져오기
            ImageView image = new ImageView(mContext);
            image.setImageResource(images[position]);

            AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
            // 다이얼로그 제목
            builder.setTitle("학번 : 2017305045");
            // 다이얼로그 뷰로 이미지 선언
            builder.setView(image);
            // 종료버튼 선언
            builder.setNegativeButton("종료", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if(dialog != null)
                        // 클릭 시 닫기
                        dialog.dismiss();
                }
            });
            // 다이얼로그 보이기
            dialog = builder.create();
            dialog.show();
        }
    }

    // onCreate 선언
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // popupClass 객체 pop 생성
        popupClass pop = new popupClass(this);
        // GridView 객체 grid에 activity_main.xml에 선언된 gridview id를 통해 넣음
        grid = (GridView)findViewById(R.id.grid);
        // grid의 어댑터를 커스텀 어댑터로 설정
        grid.setAdapter(new imgAdapter(this));
        // grid에 pop 이벤트리스너 설정
        grid.setOnItemClickListener(pop);
    }

    // 커스텀 어댑터 선언
    public class imgAdapter extends BaseAdapter {
       private Context mContext;
       public imgAdapter(Context c) {
           mContext = c;
       }
       public int getCount() {
           return images.length;
       }
       public Object getItem(int position) {
           return null;
       }
       public long getItemId(int position) {
           return 0;
       }
       public View getView(int position, View convertView, ViewGroup parent) {
           ImageView imageView;
           if(convertView == null) {
               imageView = new ImageView(mContext);
               imageView.setLayoutParams(new GridView.LayoutParams(300, 300));
               imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
               imageView.setPadding(8, 8, 8, 8);
           }
           else {
               imageView = (ImageView) convertView;
           }
           imageView.setImageResource(images[position]);
           return imageView;
       }
    }
}
