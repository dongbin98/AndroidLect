package com.example.firebasetest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = database.getReference();
    private String serviceUrl;
    private String serviceUrl2;
    private String serviceKey;

    Button save, read, delete, deleteAll;
    EditText edit1, edit2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        save = (Button) findViewById(R.id.save);
        read = (Button) findViewById(R.id.read);
        delete = (Button) findViewById(R.id.delete);
        deleteAll = (Button) findViewById(R.id.deleteAll);
        edit1 = (EditText) findViewById(R.id.busNum);
        edit2 = (EditText) findViewById(R.id.routeId);

        serviceUrl = "http://ws.bus.go.kr/api/rest/busRouteInfo/getBusRouteList";
        serviceUrl2 = "http://ws.bus.go.kr/api/rest/busRouteInfo/getStaionByRoute";
        serviceKey = "6QzxyxwgIYyrLx303Ylnud8BZMmZ4Caw%2Ble0fsW8oc9lsv0KHGZWV5jhZ%2BLgzGbESVdaYzjRmnnwdzWychAaeA%3D%3D";

        save.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                String BusNumber = edit1.getText().toString();
                String searchUrl = serviceUrl + "?serviceKey=" + serviceKey + "&strSrch=" + BusNumber;
                FindBusInfo fBusinfo = new FindBusInfo();
                fBusinfo.execute(searchUrl);
            }
        });
        read.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MapsActivity.class);
                startActivity(intent);
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteBusstation(edit2.getText().toString());
            }
        });
        deleteAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteAllBusstation();
            }
        });
    }

    private class FindRoot extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            try {
                return (String) downloadUrl((String) strings[0]);
            } catch(IOException e){
                return "다운로드 실패";
            }
        }
        @Override
        protected void onPostExecute(String result) {
            try {
                XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
                factory.setNamespaceAware(true);
                XmlPullParser xpp = factory.newPullParser();

                xpp.setInput(new StringReader(result));
                int eventType = xpp.getEventType();

                String headerCd = "";
                String gpsX = "";
                String gpsY = "";
                String plainNo = "";
                String stationNm = "";
                String direction = "";

                boolean bSet_headerCd = false;
                boolean bSet_gpsX = false;
                boolean bSet_gpsY = false;
                boolean bSet_plainNo = false;
                boolean bSet_direction = false;
                boolean bSet_stationNm = false;
                boolean isWrite = false;

                while(eventType != XmlPullParser.END_DOCUMENT) {
                    if(eventType == XmlPullParser.START_DOCUMENT) { ; }
                    else if(eventType == XmlPullParser.START_TAG) {
                        String tag_name = xpp.getName();
                        if(tag_name.equals("seq"))
                            bSet_plainNo = true;
                        if(tag_name.equals("headerCd"))
                            bSet_headerCd = true;
                        if(tag_name.equals("gpsX"))
                            bSet_gpsX = true;
                        if(tag_name.equals("gpsY"))
                            bSet_gpsY = true;
                        if(tag_name.equals("direction"))
                            bSet_direction = true;
                        if(tag_name.equals("stationNm"))
                            bSet_stationNm = true;
                    }
                    else if(eventType == XmlPullParser.TEXT)
                    {
                        if(bSet_headerCd)
                        {
                            headerCd = xpp.getText();
                            Log.d("headerCd", headerCd);
                            bSet_headerCd = false;
                        }
                        if(headerCd.equals("0") && bSet_direction) {
                            if(bSet_direction) {
                                direction = xpp.getText();
                                //if(direction.equals("서경대입구"))
                                    //isWrite = true;
                            }
                            //if(isWrite) {
                                if (bSet_plainNo) {
                                    plainNo = xpp.getText();
                                    Log.d("plainNo", plainNo);
                                    bSet_plainNo = false;
                                }
                                if (bSet_gpsX) {
                                    gpsX = xpp.getText();
                                    Log.d("gpsX", gpsX);
                                    bSet_gpsX = false;
                                }
                                if (bSet_gpsY) {
                                    gpsY = xpp.getText();
                                    Log.d("gpsY", gpsY);
                                    bSet_gpsY = false;
                                }
                                if (bSet_stationNm) {
                                    stationNm = xpp.getText();
                                    Log.d("stationNm", stationNm);
                                    bSet_stationNm = false;
                                    isWrite = false;
                                    addBusstation(plainNo, gpsX, gpsY, stationNm);
                                }
                            //}
                        }
                    }
                    else if(eventType == XmlPullParser.END_TAG) { ; }
                    eventType = xpp.next();
                }
            } catch (Exception e) { Log.e("Error", e.getMessage()); }
        }
        private String downloadUrl(String myurl) throws IOException {
            HttpURLConnection conn = null;
            try {
                URL url = new URL(myurl);
                conn = (HttpURLConnection) url.openConnection();
                BufferedInputStream buf = new BufferedInputStream(conn.getInputStream());
                BufferedReader bufreader = new BufferedReader(new InputStreamReader(buf, "utf-8"));
                String line = null;
                String page = "";
                while((line = bufreader.readLine()) != null)
                    page += line;
                return page;
            } finally {
                conn.disconnect();
            }
        }
    }
    private class FindBusInfo extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {
            try {
                return (String)downloadUrl((String)urls[0]);
            } catch (IOException e) {
                return "다운로드 실패2";
            }
        }
        protected void onPostExecute(String result) {
            try {
                XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
                factory.setNamespaceAware(true);
                XmlPullParser xpp = factory.newPullParser();

                xpp.setInput(new StringReader(result));
                int eventType = xpp.getEventType();

                String headerCd = "";
                String busRouteId = "";
                String busRouteNm = "";

                boolean bSet_headerCd = false;
                boolean bSet_busRouteId = false;
                boolean bSet_busRouteNm = false;

                while (eventType != XmlPullParser.END_DOCUMENT) {
                    if(eventType == XmlPullParser.START_DOCUMENT) {
                        ;
                    } else if(eventType == XmlPullParser.START_TAG) {
                        String tag_name = xpp.getName();
                        if (tag_name.equals("headerCd"))
                            bSet_headerCd = true;
                        if (tag_name.equals("busRouteId"))
                            bSet_busRouteId = true;
                        if (tag_name.equals("busRouteNm"))
                            bSet_busRouteNm = true;
                    } else if(eventType == XmlPullParser.TEXT) {
                        if (bSet_headerCd) {
                            headerCd = xpp.getText();
                            Log.d("headerCd", headerCd);
                            bSet_headerCd = false;
                        }

                        if (headerCd.equals("0")) {
                            if (bSet_busRouteId) {
                                busRouteId = xpp.getText();
                                Log.d("busRouteId", busRouteId);
                                bSet_busRouteId = false;
                            }
                            if (bSet_busRouteNm) {
                                busRouteNm = xpp.getText();
                                Log.d("busRouteNm", busRouteNm);
                                bSet_busRouteNm = false;
                            }
                        }
                    } else if(eventType == XmlPullParser.END_TAG) {
                        ;
                    }
                    eventType = xpp.next();
                }
                String searchURL2 = serviceUrl2 + "?serviceKey=" + serviceKey + "&busRouteId=" + busRouteId;
                edit2.setText(busRouteId);
                Log.d("searchURL2", searchURL2);
                FindRoot fRoot = new FindRoot();
                fRoot.execute(searchURL2);
            } catch (Exception e) {
                Log.d("Error", e.getMessage());
            }
        }

        private String downloadUrl(String myurl) throws IOException {
            HttpURLConnection conn = null;
            try {
                URL url = new URL(myurl);
                conn = (HttpURLConnection) url.openConnection();
                BufferedInputStream buf = new BufferedInputStream(conn.getInputStream());
                BufferedReader bufreader = new BufferedReader(new InputStreamReader(buf, "utf-8"));
                String line = null;
                String page = "";
                while((line = bufreader.readLine()) != null) {
                    page += line;
                }
                return page;
            } finally {
                conn.disconnect();
            }
        }
    }
    // FireBase
    public void addBusstation(String no, String gpsX, String gpsY, String stationNm) {
        Bus bus = new Bus(no, gpsX, gpsY, stationNm);
        databaseReference.child("Member").child(no).setValue(bus).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(@NonNull Void unused) {
                Toast.makeText(MainActivity.this, "save succeed", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(MainActivity.this, "save failed", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void deleteBusstation(String no) {
        databaseReference.child("Member").child(no).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(@NonNull Void unused) {
                Toast.makeText(MainActivity.this, "delete succeed", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(MainActivity.this, "delete failed", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void deleteAllBusstation() {
        databaseReference.child("Member").removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(@NonNull Void unused) {
                Toast.makeText(MainActivity.this, "delete succeed", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(MainActivity.this, "delete failed", Toast.LENGTH_SHORT).show();
            }
        });
    }
}