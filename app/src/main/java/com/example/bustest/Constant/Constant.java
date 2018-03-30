package com.example.bustest.Constant;

import okhttp3.MediaType;

/**
 * Created by lenovo on 2018/2/20.
 */

public class Constant {
    //Btn的标识
    public static final int btn_love = 0;
    public static final int btn_help = 1;
    public static final int btn_bus = 2;
    public static final int btn_map = 3;
    public static final int btn_me = 4;

    //Fragment的标识
    public static final String fragment_love = "爱心站";
    public static final String fragment_help = "帮助厅";
    public static final String fragment_bus = "班车";
    public static final String fragment_map = "地图";
    public static final String fragment_me = "我的";
    //服务器URL
    public static String URL="http://111.230.5.110/busregister/";//自己电脑的ip地址
    public static String URL_Register="http://111.230.5.110/busregister/register";
    public static String URL_Login=URL+"login";
    //MeSetting
    public static String me_setting="设置";
    public static String me_about="关于";
    public static String man="男";
    public static String woman="女";
    public static MediaType JSON= MediaType.parse("application/json; charset=utf-8");
}
