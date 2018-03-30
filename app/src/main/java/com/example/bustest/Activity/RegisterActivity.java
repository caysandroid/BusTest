package com.example.bustest.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.IdRes;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.bustest.Constant.Constant;
import com.example.bustest.MainActivity;
import com.example.bustest.Manager.AbsSuperApplication;
import com.example.bustest.R;
import com.example.bustest.Response.ResponseInfo;
import com.example.bustest.db.User;
import com.example.bustest.util.HttpUtil;
import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;

import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.Response;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText nameText;
    private EditText nickText;
    private EditText passwordText1;
    private EditText passwordText2;
    private RadioGroup sexRadioGroup;
    private User user=null;
    private ResponseInfo info=null;
    private static int status=-1;
    private ProgressDialog registerDialog;
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            registerDialog.dismiss();
            if(msg.what==1){
                Toast.makeText(RegisterActivity.this,""+info.getText()+" "+info.getResponseCode(),Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(RegisterActivity.this,""+info.getText()+" "+info.getResponseCode(),Toast.LENGTH_SHORT).show();
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        nameText=(EditText)findViewById(R.id.name_reg);
        nickText=(EditText)findViewById(R.id.nick_name_reg);
        passwordText1=(EditText)findViewById(R.id.password_reg);
        passwordText2=(EditText)findViewById(R.id.password_2_reg);
        sexRadioGroup=(RadioGroup) findViewById(R.id.sex_radiogroup_reg);
        Button submitBtn=(Button)findViewById(R.id.register_submit_btn);
        submitBtn.setOnClickListener(this);
        info=new ResponseInfo();
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        RegisterActivity.this.finish();
    }
    @Override
    public void onClick(View v) {
        String userName=nameText.getText().toString();
        String nickName=nickText.getText().toString();
        String password1=passwordText1.getText().toString();
        String password2=passwordText2.getText().toString();
        String sex=null;
        switch (v.getId()){
            case R.id.register_submit_btn:
                if(check(userName,nickName,password1,password2,sex)){
                    registerDialog=ProgressDialog.show(this,"注册","Please wait...");
                    for(int i=0;i<sexRadioGroup.getChildCount();i++){
                        RadioButton s=(RadioButton)sexRadioGroup.getChildAt(i);
                        if(s.isChecked()){
                            sex=s.getText().toString();break;
                        }
                    }
                    user=new User();
                    user.setName(userName);
                    user.setNickName(nickName);
                    user.setPassword(password1);
                    handleRegister(user);
                }
                break;
            default:break;
        }
    }
    private boolean check(String userName, String nickName, String password1, String password2, String sex){
        int count=0;
        for(int i=0;i<sexRadioGroup.getChildCount();i++){
            RadioButton s=(RadioButton)sexRadioGroup.getChildAt(i);
            if(s.isChecked()){
                count++;
                sex=s.getText().toString();
            }
        }
        if(count!=1){
            Toast.makeText(RegisterActivity.this,"汉子/妹子选择不对",Toast.LENGTH_SHORT).show();
            return false;
        }
        if(userName==null||userName.equals("")){
            Toast.makeText(RegisterActivity.this,"用户名不能为空",Toast.LENGTH_SHORT).show();
            return false;
        }
        if(nickName==null||nickName.equals("")){
            Toast.makeText(RegisterActivity.this,"昵称不能为空",Toast.LENGTH_SHORT).show();
            return false;
        }
        if(password1==null||password1.equals("")||password2==null||password2.equals("")){
            Toast.makeText(RegisterActivity.this,"密码不能为空",Toast.LENGTH_SHORT).show();
            return false;
        }
        if(password1.equals(password2));
        else{
            Toast.makeText(RegisterActivity.this,"两次密码不一致",Toast.LENGTH_SHORT).show();
            return false;
        }
        if(!isLawed(userName)){
            Toast.makeText(RegisterActivity.this,"用户名是数字、字母、下划线组合",Toast.LENGTH_SHORT).show();
            return false;
        }
        if(!isLawed(password1)){
            Toast.makeText(RegisterActivity.this,"密码是数字、字母、下划线组合",Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
    //检查用户名是否合法
    private boolean isLawed(String s){
        int count=0;
        for(int i=0;i<s.length();i++){
            char a=s.charAt(i);
            if((a>='0'&&a<='9')||(a>='a'&&a<='z')||(a>='A'&&a<='Z')||a=='_')count++;
        }
        if(count==s.length())return true;
        else return false;
    }
    private void handleRegister(User user){
        final Gson gson=new Gson();
        String userTest=gson.toJson(user);
        System.out.println(userTest);
        final RequestBody requestBody=RequestBody.create(Constant.JSON,userTest);
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpUtil.sendHttpRequest(Constant.URL_Register, requestBody,new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        info.error_db();
                        info.setText("连接失败");
                        status=0;
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {

                        String responseTest=response.body().string();
                        System.out.println(responseTest);
                        info=gson.fromJson(responseTest,ResponseInfo.class);
                        status=1;
                    }
                });
            }
        }).start();
        while(true){
            if(status!=-1)break;
        }
        Message message=handler.obtainMessage();
        if(status!=-1)message.what=1;
        else message.what=0;
        handler.sendMessage(message);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish(); // back button
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
