package com.example.bustest.Json;

import com.google.gson.annotations.SerializedName;

/**
 * Created by lenovo on 2018/2/23.
 */

public class Login {
    @SerializedName("responseCode")
    public String responseCode;
    @SerializedName("info")
    public String info;
    @SerializedName("text")
    public String text;
}
