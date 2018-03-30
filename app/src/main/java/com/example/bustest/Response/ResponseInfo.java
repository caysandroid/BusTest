package com.example.bustest.Response;

/**
 * Created by lenovo on 2018/3/29.
 */

public class ResponseInfo {
    private String responseCode;
    private String responseMsg;
    private String text;

    public String getResponseCode() {
        return responseCode;
    }

    public String getResponseMsg() {
        return responseMsg;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public void setResponseMsg(String responseMsg) {
        this.responseMsg = responseMsg;
    }
    public void error_db(){
        responseCode="404";
        responseMsg="失败";
        text="未知错误";
    }
    public void right_db(){
        responseCode="202";
        responseMsg="成功";
        text="注册成功";
    }
}
