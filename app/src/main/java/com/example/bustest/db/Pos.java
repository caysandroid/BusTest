package com.example.bustest.db;

import org.litepal.crud.DataSupport;

/**
 * Created by lenovo on 2018/3/1.
 */

public class Pos extends DataSupport {
    private float longitude;//经度
    private float latitude;//纬度
    private float x;//x坐标
    private float y;//y坐标
    public void setLongitude(float longitude){
        this.longitude=longitude;
    }
    public float getLongitude(){
        return this.longitude;
    }
    public void setLatitude(float latitude){
        this.latitude=latitude;
    }
    public float getLatitude(){
        return this.latitude;
    }
    public void setX(float x){
        this.x=x;
    }
    public float getX(){
        return this.x;
    }
    public void setY(float y){
        this.y=y;
    }
    public float getY(){
        return this.y;
    }
}
