package com.example.bustest.UI;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.bustest.Constant.Constant;
import com.example.bustest.R;

import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by lenovo on 2018/2/20.
 */

public class HeadControlPanel extends RelativeLayout{
    private Context mContext;
    private TextView middleTitle;
    private TextView rightTitle;
    private static final float middle_title_size = 20f;
    private static final float right_title_size = 17f;
    private static final int default_background_color = Color.rgb(23, 124, 202);
    public HeadControlPanel(Context context,AttributeSet attrs){
        super(context,attrs);
    }
    public HeadControlPanel(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    @Override
    protected void onFinishInflate() {
        middleTitle = (TextView)findViewById(R.id.midle_title);
        rightTitle = (TextView)findViewById(R.id.right_title);
        setBackgroundColor(default_background_color);
    }
    public void initHeadPanel(){

        if(middleTitle != null){
            setMiddleTitle(Constant.fragment_love);
        }
    }
    public void setMiddleTitle(String s){
        middleTitle.setText(s);
        middleTitle.setTextSize(middle_title_size);
    }
    public void setRightTitle(String s){
        rightTitle.setText(s);
        rightTitle.setTextSize(right_title_size);
    }
}
