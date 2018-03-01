package com.example.bustest.db;

import org.litepal.crud.DataSupport;

/**
 * Created by lenovo on 2018/3/1.
 */

public class Requester extends DataSupport {
    private int allTaskNum;//发布任务总数
    private int succeedTask;//成功完成的任务数
    private int unsucceedTask;//未成功完成的任务数
    public void setAllTaskNum(int allTaskNum){
        this.allTaskNum=allTaskNum;
    }
    public void setSucceedTask(int succeedTask){
        this.succeedTask=succeedTask;
    }
    public void setUnsucceedTask(int unsucceedTask){
        this.unsucceedTask=unsucceedTask;
    }
    public int getAllTaskNum(){
        return this.allTaskNum;
    }
    public int getSucceedTask(){
        return this.succeedTask;
    }
    public int getUnsucceedTask(){
        return this.unsucceedTask;
    }
}
