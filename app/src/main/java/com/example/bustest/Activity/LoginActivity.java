package com.example.bustest.Activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bustest.Constant.Constant;
import com.example.bustest.Json.Login;
import com.example.bustest.MainActivity;
import com.example.bustest.R;
import com.example.bustest.util.HttpUtil;
import com.example.bustest.util.Utility;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class LoginActivity extends Activity implements View.OnClickListener{

    private EditText accountEdit,passwordEdit;
    private CheckBox rememberPassword;
    private Button loginBtn,registerBtn;
    SharedPreferences sp=null,loginSp=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        }
        loginBtn.setOnClickListener(this);
        registerBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String account=accountEdit.getText().toString();
        String password=passwordEdit.getText().toString();
        if(account==null||password==null||account.equals("")||password.equals("")){
            wrong();
            return;
        }
        login(account,password);
        Toast.makeText(this, "status:"+loginSp.getBoolean("status",false), Toast.LENGTH_SHORT).show();
        switch (v.getId()){
            case R.id.login_btn:
                if(loginSp.getBoolean("status",false)){
                    Toast.makeText(this, "跳转", Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(this, MainActivity.class);
                    startActivity(intent);
                }
                break;
            case R.id.register_btn:
                break;
            default:break;
        }
    }
    public void login(final String account, final String password){
        String loginUrl= Constant.URL_Login+"?account="+account+"&password="+password;
        final Login login;
        HttpUtil.sendHttpRequest(loginUrl, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                /*runOnUiThread(new Runnable() {
                    @Override
                    public void run() {*/
                        SharedPreferences.Editor editor=loginSp.edit();
                        editor.putBoolean("status",false);
                        editor.apply();
                        Toast.makeText(getApplicationContext(), "未知错误", Toast.LENGTH_SHORT).show();
                    /*}
                });*/
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String responseText=response.body().string();
                final Login login= Utility.handleLoginRequest(responseText);
                /*runOnUiThread(new Runnable() {
                    @Override
                    public void run() {*/
                        if(login.responseCode.equals("404")){
                            SharedPreferences.Editor editor=loginSp.edit();
                            editor.putBoolean("status",false);
                            editor.apply();
                            Toast.makeText(getApplicationContext(), "登录失败", Toast.LENGTH_SHORT).show();
                        }else if(login.responseCode.equals("200")){
                            SharedPreferences.Editor editor1=loginSp.edit();
                            editor1.putBoolean("status",true);
                            editor1.apply();
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
                            //Toast.makeText(getApplicationContext(), "登陆成功", Toast.LENGTH_SHORT).show();
                        }
                    /*}
                });*/
            }
        });
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
