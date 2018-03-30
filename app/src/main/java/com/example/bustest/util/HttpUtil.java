package com.example.bustest.util;

import java.util.concurrent.TimeUnit;

import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by lenovo on 2018/2/23.
 */

public class HttpUtil {
    public static void sendHttpRequest(String address,Callback callback){
        OkHttpClient client=new OkHttpClient();
        Request request=new Request.Builder()
                .url(address)
                .build();
        client.newBuilder()
                .connectTimeout(5, TimeUnit.SECONDS)
                .readTimeout(5,TimeUnit.SECONDS)
                .writeTimeout(5,TimeUnit.SECONDS);
        client.newCall(request).enqueue(callback);
    }
    public static void sendHttpRequest(String address, RequestBody requestBody, Callback callback){
        OkHttpClient client=new OkHttpClient();
        Request request=new Request.Builder()
                .url(address)
                .post(requestBody)
                .build();
        client.newBuilder()
                .connectTimeout(5, TimeUnit.SECONDS)
                .readTimeout(5,TimeUnit.SECONDS)
                .writeTimeout(5,TimeUnit.SECONDS);
        client.newCall(request).enqueue(callback);
    }
}
