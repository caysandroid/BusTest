package com.example.bustest.Manager;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by lenovo on 2018/3/18.
 */

public class AbsSuperApplication extends Application{
    protected static Context context;
    protected static String activityName;
    /**
     * 维护Activity的list
     */
    public static List<Activity> activities= Collections.synchronizedList(new ArrayList<Activity>());

    @Override
    public void onCreate() {
        super.onCreate();
        context=this.getApplicationContext();
    }
    public static String getAppName() {
        return activityName;
    }
    public static Context getContext() {
        return context;
    }
    public static void setActivityName(String name){
        activityName=name;
    }
    /**
     * @param activity 作用说明 ：添加一个activity到管理里
     */
    public void pushActivity(Activity activity){
        activities.add(activity);
    }
    /**
     * @param activity 作用说明 ：删除一个activity在管理里
     */
    public void popActivity(Activity activity){
        activities.remove(activity);
    }
    /**
     * get current Activity 获取当前Activity（栈中最后一个压入的）
     */
    public static Activity currentActivity(){
        if(activities==null||activities.isEmpty()){
            return null;
        }
        Activity activity=activities.get(activities.size()-1);
        return activity;
    }
    /**
     * 结束指定的Activity
     */
    public static void finishActivity(Activity activity){
        if(activities==null||activities.isEmpty()){
            return;
        }
        if(activity!=null){
            activities.remove(activity);
            activity.finish();
            activity=null;
        }
    }
    /**
     * 按照指定类名找到activity
     *
     * @param cls
     * @return
     */
    public static Activity findActivity(Class<?> cls) {
        Activity targetActivity = null;
        if (activities != null) {
            for (Activity activity : activities) {
                if (activity.getClass().equals(cls)) {
                    targetActivity = activity;
                    break;
                }
            }
        }
        return targetActivity;
    }
    /**
     * @return 作用说明 ：获取当前最顶部activity的实例
     */
    public Activity getTopActivity() {
        Activity mBaseActivity = null;
        synchronized (activities) {
            final int size = activities.size() - 1;
            if (size < 0) {
                return null;
            }
            mBaseActivity = activities.get(size);
        }
        return mBaseActivity;
    }
    /**
     * @return 作用说明 ：获取当前最顶部的acitivity 名字
     */
    public String getTopActivityName(){
        Activity baseActivity=null;
        synchronized (activities){
            final int size=activities.size()-1;
            if(size<0)return null;
            baseActivity=activities.get(size);
        }
        return baseActivity.getClass().getName();
    }
    /**
     * 结束所有Activity
     */
    public static void finishAllActivity() {
        if (activities == null) {
            return;
        }
        for (Activity activity : activities) {
            activity.finish();
        }
        activities.clear();
    }
    /**
     * 退出应用程序
     */
    public  static void appExit() {
        try {
            finishAllActivity();
        } catch (Exception e) {
        }
    }
}
