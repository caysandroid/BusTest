package com.example.bustest.util;

import com.example.bustest.Json.Login;
import com.google.gson.Gson;

import org.json.JSONObject;

/**
 * Created by lenovo on 2018/2/23.
 */

public class Utility {
    //解析Json数据
    public static Login handleLoginRequest(String response){
        try{
            JSONObject jsonObject=new JSONObject(response);
            String loginContent=jsonObject.toString();
            System.out.println(loginContent);
            return new Gson().fromJson(loginContent,Login.class);
            //通过fromJson将json数据转换为Weather对象
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
