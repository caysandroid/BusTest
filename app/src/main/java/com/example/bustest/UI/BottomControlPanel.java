package com.example.bustest.UI;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.bustest.Constant.Constant;
import com.example.bustest.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 2018/2/20.
 */

public class BottomControlPanel extends RelativeLayout implements View.OnClickListener {
    private Context mContext=null;
    private ImageText loveBtn=null,helpBtn=null,busBtn=null,mapBtn=null,meBtn=null;
    private int background_color= Color.rgb(243,243,243);
    private BottomPanelCallback bottomCallback=null;
    private List<ImageText> viewList=new ArrayList<ImageText>();
    public interface BottomPanelCallback{
        public void onBottomPanelClick(int itemId);
    }
    public BottomControlPanel(Context context){
        super(context);
    }
    public BottomControlPanel(Context context,AttributeSet attrs){
        super(context,attrs);
    }
    public BottomControlPanel(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        loveBtn=(ImageText)findViewById(R.id.btn_love);
        helpBtn=(ImageText)findViewById(R.id.btn_help);
        busBtn=(ImageText)findViewById(R.id.btn_bus);
        mapBtn=(ImageText)findViewById(R.id.btn_map);
        meBtn=(ImageText)findViewById(R.id.btn_me);
        setBackgroundColor(background_color);
        viewList.add(loveBtn);viewList.add(helpBtn);
        viewList.add(busBtn);viewList.add(mapBtn);
        viewList.add(meBtn);
    }
    public void initBottomPanel(){
        if(loveBtn != null){
            loveBtn.setImage(R.drawable.ic_love);
            loveBtn.setText("爱心站");
        }
        if(helpBtn != null){
            helpBtn.setImage(R.drawable.ic_help);
            helpBtn.setText("帮助厅");
        }
        if(busBtn != null){
            busBtn.setImage(R.drawable.ic_bush);
            busBtn.setText("班车");
        }
        if(mapBtn != null){
            mapBtn.setImage(R.drawable.ic_map);
            mapBtn.setText("地图");
        }
        if(meBtn != null){
            meBtn.setImage(R.drawable.ic_me);
            meBtn.setText("我的");
        }
        setBtnListener();

    }
    private void setBtnListener(){
        int num = this.getChildCount();
        for(int i = 0; i < num; i++){
            View v = getChildAt(i);
            if(v != null){
                v.setOnClickListener(this);
            }
        }
    }
    public void setBottomCallback(BottomPanelCallback mBottomCallback){
        bottomCallback = mBottomCallback;
    }
    @Override
    public void onClick(View v) {
        initBottomPanel();
        int index = -1;
        switch(v.getId()){
            case R.id.btn_love:
                index = Constant.btn_love;
                loveBtn.setChecked(Constant.btn_love);
                break;
            case R.id.btn_help:
                index = Constant.btn_help;
                helpBtn.setChecked(Constant.btn_help);
                break;
            case R.id.btn_bus:
                index = Constant.btn_bus;
                busBtn.setChecked(Constant.btn_bus);
                break;
            case R.id.btn_map:
                index = Constant.btn_map;
                mapBtn.setChecked(Constant.btn_map);
                break;
            case R.id.btn_me:
                index=Constant.btn_me;
                meBtn.setChecked(Constant.btn_me);
            default:break;
        }
        if(bottomCallback != null){
            bottomCallback.onBottomPanelClick(index);
        }
    }
    public void defaultBtnChecked(){
        if(loveBtn != null){
            loveBtn.setChecked(Constant.btn_love);
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        layoutItems(l,t,r,b);
    }
    private void layoutItems(int left, int top, int right, int bottom){
        int n = getChildCount();
        if(n == 0){
            return;
        }
        int paddingLeft = getPaddingLeft();
        int paddingRight = getPaddingRight();
        int width = right - left;
        int height = bottom - top;
        int allViewWidth = 0;
        for(int i = 0; i< n; i++){
            View v = getChildAt(i);
            allViewWidth += v.getWidth();
        }
        int blankWidth = (width - allViewWidth - paddingLeft - paddingRight) / (n + 1);
        int i;
        for(i=0;i<n-1;i++) {
            LayoutParams params1 = (LayoutParams) viewList.get(i).getLayoutParams();
            params1.leftMargin = blankWidth;
            viewList.get(i).setLayoutParams(params1);
        }

        LayoutParams params2 = (LayoutParams) viewList.get(i).getLayoutParams();
        params2.rightMargin = blankWidth;
        viewList.get(i).setLayoutParams(params2);
    }
}
