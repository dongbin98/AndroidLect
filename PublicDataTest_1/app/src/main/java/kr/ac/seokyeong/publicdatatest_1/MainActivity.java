package kr.ac.seokyeong.publicdatatest_1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity
{
    TextView tv;
    private String busRouteId;
    private String serviceUrl;
    private String serviceUrl2;
    private String serviceKey;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = (TextView) findViewById(R.id.data);

        serviceUrl = "http://ws.bus.go.kr/api/rest/busRouteInfo/getBusRouteList";
        serviceUrl2 = "http://ws.bus.go.kr/api/rest/busRouteInfo/getRoutePath";
        serviceKey = "6QzxyxwgIYyrLx303Ylnud8BZMmZ4Caw%2Ble0fsW8oc9lsv0KHGZWV5jhZ%2BLgzGbESVdaYzjRmnnwdzWychAaeA%3D%3D";
        String BusNumber = "2115";

        String searchURL = serviceUrl + "?serviceKey=" + serviceKey + "&strSrch=" + BusNumber;
        Log.d("SearchURL", searchURL);

        FindBusInfo DWT = new FindBusInfo();
        DWT.execute(searchURL);
    }

    private class FindBusRoot extends AsyncTask<String, Void, String>
    {

        @Override
        protected String doInBackground(String... strings)
        {
            try
            {
                return(String) downloadUrl((String) strings[0]);
            } catch(IOException e)
            {
                return "다운로드 실패";
            }
        }

        protected void onPostExecute(String result)
        {
            try {
                Log.d("onPostExecute", result);
                XmlPullParserFactory factory = XmlPullParserFactory.newInstance();

                factory.setNamespaceAware(true);
                XmlPullParser xpp = factory.newPullParser();

                xpp.setInput(new StringReader(result));
                int eventType = xpp.getEventType();

                String headerCd = "";
                String gpsX = "";
                String gpsY = "";
                String plainNo = "";

                boolean bSet_headerCd = false;
                boolean bSet_gpsX = false;
                boolean bSet_gpsY = false;
                boolean bSet_plainNo = false;

                while(eventType != XmlPullParser.END_DOCUMENT)
                {
                    if(eventType == XmlPullParser.START_DOCUMENT)
                    {
                        ;
                    }
                    else if(eventType == XmlPullParser.START_TAG)
                    {
                        String tag_name = xpp.getName();
                        if(tag_name.equals("no"))
                            bSet_plainNo = true;
                        if(tag_name.equals("headerCd"))
                            bSet_headerCd = true;
                        if(tag_name.equals("gpsX"))
                            bSet_gpsX = true;
                        if(tag_name.equals("gpsY"))
                            bSet_gpsY = true;

                    }
                    else if(eventType == XmlPullParser.TEXT)
                    {
                        if(bSet_headerCd)
                        {
                            headerCd = xpp.getText();
                            bSet_headerCd = false;
                        }

                        if(headerCd.equals("0"))
                        {
                            if(bSet_plainNo)
                            {
                                plainNo = xpp.getText();
                                tv.append("plainNo: " + plainNo + "\n\n");
                                bSet_plainNo = false;
                            }

                            if(bSet_gpsX)
                            {
                                gpsX = xpp.getText();
                                tv.append("gpsX: " + gpsX + "\n");
                                bSet_gpsX = false;
                            }

                            if(bSet_gpsY)
                            {
                                gpsY = xpp.getText();
                                tv.append("gpsY: " + gpsY + "\n");
                                bSet_gpsY = false;
                            }
                        }
                    }

                    else if(eventType == XmlPullParser.END_TAG)
                    {
                        ;
                    }
                    eventType = xpp.next();
                }

            }catch (Exception e){tv.setText(e.getMessage());}
        }

        private String downloadUrl(String myurl) throws IOException
        {
            HttpURLConnection conn = null;
            try
            {
                URL url = new URL(myurl);
                conn = (HttpURLConnection) url.openConnection();
                BufferedInputStream buf = new BufferedInputStream(conn.getInputStream());
                BufferedReader bufreader = new BufferedReader(new InputStreamReader(buf, "utf-8"));
                String line = null;
                String page = "";
                while((line = bufreader.readLine()) != null)
                    page += line;
                return page;
            }
            finally
            {
                conn.disconnect();
            }
        }
    }

    private class FindBusInfo extends AsyncTask<String, Void, String>
    {
        private String result;

        public String getResult()
        {
            return result;
        }

        @Override
        protected String doInBackground(String... strings)
        {
            try
            {
                return(String) downloadUrl((String) strings[0]);
            } catch(IOException e)
            {
                return "다운로드 실패";
            }
        }

        protected void onPostExecute(String result)
        {
            try {
                    Log.d("onPostExecute", result);
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

                    while(eventType != XmlPullParser.END_DOCUMENT)
                    {
                        if(eventType == XmlPullParser.START_DOCUMENT)
                        {
                            ;
                        }
                        else if(eventType == XmlPullParser.START_TAG)
                        {
                            String tag_name = xpp.getName();
                            if(tag_name.equals("headerCd"))
                                bSet_headerCd = true;
                            if(tag_name.equals("busRouteId"))
                                bSet_busRouteId = true;
                            if(tag_name.equals("busRouteNm"))
                                bSet_busRouteNm = true;
                        }
                        else if(eventType == XmlPullParser.TEXT)
                        {
                            if(bSet_headerCd)
                            {
                                headerCd = xpp.getText();
                                tv.append("headerCd: " + headerCd + "\n");
                                bSet_headerCd = false;
                            }

                            if(headerCd.equals("0"))
                            {
                                if(bSet_busRouteId)
                                {
                                    busRouteId = xpp.getText();
                                    result = busRouteId;
                                    tv.append("busRouteId: " + busRouteId + "\n");
                                    bSet_busRouteId = false;
                                }

                                if(bSet_busRouteNm)
                                {
                                    busRouteNm = xpp.getText();
                                    tv.append("busRouteNm: " + busRouteNm + "\n\n");
                                    bSet_busRouteNm = false;
                                }
                            }
                        }

                        else if(eventType == XmlPullParser.END_TAG)
                        {
                            ;
                        }
                        eventType = xpp.next();
                    }

                String searchURL2 = serviceUrl2 + "?serviceKey=" + serviceKey + "&busRouteId=" + busRouteId;
                Log.d("searchURL2", searchURL2);
                FindBusRoot FBR = new FindBusRoot();
                FBR.execute(searchURL2);

            }catch (Exception e){tv.setText(e.getMessage());}
        }

        private String downloadUrl(String myurl) throws IOException
        {
            HttpURLConnection conn = null;
            try
            {
                URL url = new URL(myurl);
                conn = (HttpURLConnection) url.openConnection();
                BufferedInputStream buf = new BufferedInputStream(conn.getInputStream());
                BufferedReader bufreader = new BufferedReader(new InputStreamReader(buf, "utf-8"));
                String line = null;
                String page = "";
                while((line = bufreader.readLine()) != null)
                {
                    page += line;
                }

                return page;
            }
            finally
            {
                conn.disconnect();
            }
        }
    }
}