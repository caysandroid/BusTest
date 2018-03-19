package com.example.bustest.Activity;

import android.app.Activity;
import android.app.Application;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bustest.Constant.Constant;
import com.example.bustest.Json.Login;
import com.example.bustest.MainActivity;
import com.example.bustest.Manager.AbsSuperApplication;
import com.example.bustest.R;
import com.example.bustest.util.HttpUtil;
import com.example.bustest.util.Utility;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText accountEdit,passwordEdit;
    private CheckBox rememberPassword;
    private Button loginBtn,registerBtn;
    private static int status=0;
    private ProgressDialog dialog;
    SharedPreferences sp=null,loginSp=null;
    public static boolean autoLogin=true;
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if(msg.what==1){
                if(loginSp.getBoolean("status",false)){
                    if(dialog.isShowing()){
                        dialog.dismiss();
                    }
                    Intent intent=new Intent(getApplication(),MainActivity.class);
                    startActivity(intent);
                    AbsSuperApplication.finishActivity(LoginActivity.this);
                }
            }else{
                if(dialog.isShowing()){
                    dialog.dismiss();
                }
                Toast.makeText(getApplication(),"用户名或者密码错误",Toast.LENGTH_SHORT).show();
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent=getIntent();
        Bundle bundle=intent.getBundleExtra("autoLoginBundle");
        if(bundle!=null){
            autoLogin=bundle.getBoolean("autoLogin");
        }
        setContentView(R.layout.activity_login);
        accountEdit=(EditText)findViewById(R.id.account);
        passwordEdit=(EditText)findViewById(R.id.password);
        rememberPassword=(CheckBox)findViewById(R.id.remember_password);
        loginBtn=(Button)findViewById(R.id.login_btn);
        registerBtn=(Button)findViewById(R.id.register_btn);
        sp=this.getSharedPreferences("userinfo", Context.MODE_PRIVATE);
        loginSp=this.getSharedPreferences("user_login",Context.MODE_PRIVATE);
        if(sp.getBoolean("remember_password",false)==true){
            accountEdit.setText(sp.getString("account",null));
            passwordEdit.setText(sp.getString("password",null));
            rememberPassword.setChecked(true);
            if(autoLogin) {
                status = 0;
                dialog = ProgressDialog.show(this, "login...", "Please wait...");
                new loginDialog().start();
            }
        }
        loginBtn.setOnClickListener(this);
        registerBtn.setOnClickListener(this);
        AbsSuperApplication.activities.add(LoginActivity.this);
    }
    @Override
    public void onClick(View v) {
        String account=accountEdit.getText().toString();
        String password=passwordEdit.getText().toString();
        if(account==null||password==null||account.equals("")||password.equals("")){
            wrong();
            return;
        }
        switch (v.getId()){
            case R.id.login_btn:
                status=0;
                dialog=ProgressDialog.show(this,"登录","Please wait...");
                new loginDialog().start();
                break;
            case R.id.register_btn:
                break;
            default:break;
        }
    }
    public void login(final String account, final String password){
        String loginUrl= Constant.URL_Login+"?account="+account+"&password="+password;
        final Login login=null;
        HttpUtil.sendHttpRequest(loginUrl, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        status=1;
                        SharedPreferences.Editor editor=loginSp.edit();
                        editor.putBoolean("status",false);
                        editor.apply();
                        //Toast.makeText(getApplicationContext(), "未知错误", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String responseText=response.body().string();
                final Login login= Utility.handleLoginRequest(responseText);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if(login.responseCode.equals("404")){
                            SharedPreferences.Editor editor=loginSp.edit();
                            editor.putBoolean("status",false);
                            editor.apply();
                            status=1;
                        }else if(login.responseCode.equals("200")){
                            SharedPreferences.Editor editor1=loginSp.edit();
                            editor1.putBoolean("status",true);
                            editor1.apply();
                            status=1;;
                        }
                        if(rememberPassword.isChecked()){
                            SharedPreferences.Editor editor= sp.edit();
                            editor.putBoolean("remember_password",true);
                            editor.putString("account",account);
                            editor.putString("password",password);
                            editor.apply();
                        }else{
                            SharedPreferences.Editor editor= sp.edit();
                            editor.putBoolean("remember_password",false);
                            editor.putString("account",null);
                            editor.putString("password",null);
                            editor.apply();
                        }
                    }
                });
            }
        });
    }
    class loginDialog extends Thread{
        @Override
        public void run() {
            try{
                String account=accountEdit.getText().toString();
                String password=passwordEdit.getText().toString();
                login(account,password);
                while(true){
                    if(status==1)break;
                }
                Message message=handler.obtainMessage();
                if(loginSp.getBoolean("status",false))message.what=1;
                else message.what=0;
                handler.sendMessage(message);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
    public void wrong(){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getApplicationContext(), "用户名或者密码为空", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
