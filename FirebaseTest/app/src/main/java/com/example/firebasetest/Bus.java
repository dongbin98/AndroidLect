package com.example.firebasetest;

public class Bus {
    String no;          // no
    String gpsX;        // gpsX
    String gpsY;        // gpsY
    String stationNm;   // stationName

    public Bus() {}
    public Bus(String no, String gpsX, String gpsY, String stationNm) {
        this.no = no;
        this.gpsX = gpsX;
        this.gpsY = gpsY;
        this.stationNm = stationNm;
    }
    public String getno() { return no; }
    public String getgpsX() {
        return gpsX;
    }
    public String getgpsY() {
        return gpsY;
    }
    public String getStationNm() {
        return stationNm;
    }
    public void setno(String no) {
        this.no = no;
    }
    public void setgpsX(String gpsX) {
        this.gpsX = gpsX;
    }
    public void setgpsY(String gpsY) { this.gpsY = gpsY; }
    public void setStationNm(String stationNm) { this.stationNm = stationNm; }
    @Override
    public String toString() {
        return "User{" +
                "no='" + no + '\'' +
                ", gpsX='" + gpsX + '\'' +
                ", gpsY='" + gpsY + '\'' +
                ", stationNm='" + stationNm + '\'' +
                '}';
    }
}
