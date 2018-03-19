package com.example.bustest.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.bustest.MainActivity;
import com.example.bustest.Manager.AbsSuperApplication;
import com.example.bustest.R;

public class SettingActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        Button settingBtn=(Button)findViewById(R.id.setting_btn);
        settingBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.setting_btn:
                Intent intent=new Intent(SettingActivity.this,LoginActivity.class);
                Bundle bundle=new Bundle();
                bundle.putBoolean("autoLogin",false);
                intent.putExtra("autoLoginBundle",bundle);
                startActivity(intent);
                finish();
                AbsSuperApplication.finishActivity(MainActivity.activity);
                break;
            default:
                break;
        }
    }
}
