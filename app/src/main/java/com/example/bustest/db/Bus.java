package com.example.bustest.db;

import org.litepal.crud.DataSupport;

/**
 * Created by lenovo on 2018/3/1.
 */

public class Bus extends DataSupport {
    private String licensePlateNum;//车牌号
    private int busVolume;//可载人数
    private int settedNum;//已上车人数
    private int unsettedNum;//剩余座位
    private String startStation;//起始站点
    private String endStation;//结束站点
    private String startTime;//发车时间
    private Pos pos=new Pos();//坐标
    public void setLicensePlateNum(String licensePlateNum){
        this.licensePlateNum=licensePlateNum;
    }
    public void setBusVolume(int busVolume){
        this.busVolume=busVolume;
    }
    public void setSettedNum(int settedNum){
        this.settedNum=settedNum;
    }
    public void setUnsettedNum(int unsettedNum){
        this.unsettedNum=unsettedNum;
    }
    public void setStartStation(String startStation){
        this.startStation=startStation;
    }
    public void setEndStation(String endStation){
        this.endStation=endStation;
    }
    private void setStartTime(String startTime){
        this.startTime=startTime;
    }
    public String getLicensePlateNum(){
        return licensePlateNum;
    }
    public int getBusVolume(){
        return busVolume;
    }
    public int getSettedNum(){
        return settedNum;
    }
    public int getUnsettedNum(){
        return unsettedNum;
    }
    public String getStartStation(){
        return startStation;
    }
    public String getEndStation(){
        return endStation;
    }
    public String getStartTime(){
        return startTime;
    }
}
