package com.example.bustest.db;

import org.litepal.crud.DataSupport;

/**
 * Created by lenovo on 2018/3/1.
 */

public class User extends DataSupport {
    //id
    private int id;
    private long num;//学号、工号
    private String name;//姓名
    private String password;//密码
    private String nickName;//昵称
    private Pos pos=new Pos();//经纬度，坐标
    private Participant participant=new Participant();//参与者
    private Requester requester=new Requester();//请求者
    public void setId(int id){
        this.id=id;
    }
    public void setNum(long num){
        this.num=num;
    }
    public void setName(String name){
        this.name=name;
    }
    public void setPassword(String password){
        this.password=password;
    }
    public void setNickName(String nickName){
        this.nickName=nickName;
    }
    public int getId(){
        return this.id;
    }
    public long getNum(){
        return this.num;
    }
    public String getName(){
        return this.name;
    }
}
