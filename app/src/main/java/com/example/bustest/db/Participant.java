package com.example.bustest.db;

import org.litepal.crud.DataSupport;

/**
 * Created by lenovo on 2018/3/1.
 */

public class Participant extends DataSupport {
    private int allHelpNum;//帮助总数
    private int succeedHelpNum;//成功帮助数
    private int unsucceedHelpNum;//未成功帮助数
    private int loveNum;//爱心值
    private float helpPersent;//帮助成功率
    public void setAllHelpNum(int allHelpNum){
        this.allHelpNum=allHelpNum;
    }
    public void setSucceedHelpNum(int succeedHelpNum){
        this.succeedHelpNum=succeedHelpNum;
    }
    public void setUnsucceedHelpNum(int unsucceedHelpNum){
        this.unsucceedHelpNum=unsucceedHelpNum;
    }
    public void setLoveNum(int loveNum){
        this.loveNum=loveNum;
    }
    public void setHelpPersent(float helpPersent){
        this.helpPersent=helpPersent;
    }
    public int getAllHelpNum(){
        return this.allHelpNum;
    }
    public int getSucceedHelpNum(){
        return this.succeedHelpNum;
    }
    public int getUnsucceedHelpNum(){
        return this.unsucceedHelpNum;
    }
    public int getLoveNum() {
        return this.loveNum;
    }
    public float getHelpPersent(){
        return this.helpPersent;
    }
}
