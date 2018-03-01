package com.example.bustest.UI;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.bustest.Constant.Constant;
import com.example.bustest.R;

/**
 * Created by lenovo on 2018/2/19.
 */

public class ImageText extends LinearLayout{
    private Context mContext=null;
    private ImageView mImageView=null;
    private TextView mTextView=null;
    private final static int image_width=64;
    private final static int image_height=64;
    private int checked_color= Color.rgb(29,118,199);//选中蓝色
    private int unchecked_color=Color.GRAY;
    public ImageText(Context context){
        super(context);
        mContext=context;init();
    }
    public ImageText(Context context,AttributeSet attrs){
        super(context,attrs);mContext=context;init();
    }
    public ImageText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);mContext=context;init();
    }
    public void init(){
        LayoutInflater inflater=(LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View parentView=inflater.inflate(R.layout.image_text_layout,this,true);
        mImageView=(ImageView)findViewById(R.id.image_image_text);
        mTextView=(TextView)findViewById(R.id.text_image_text);
    }
    public void setImage(int id){
        if(mImageView!=null){
            mImageView.setImageResource(id);setImageSize(image_width,image_height);
        }
    }
    public void setText(String text){
        if(mTextView!=null){
            mTextView.setText(text);
            mTextView.setTextColor(unchecked_color);
        }
    }
    public void setImageSize(int width,int height){
        if(mImageView!=null){
            ViewGroup.LayoutParams params=mImageView.getLayoutParams();
            params.width=width;params.height=height;
            mImageView.setLayoutParams(params);
        }
    }
    public void setChecked(int itemID){
        if(mTextView != null){
            mTextView.setTextColor(checked_color);
        }
        int checkDrawableId = -1;
        switch (itemID){
            case Constant.btn_love:
                checkDrawableId = R.drawable.ic_love;
                break;
            case Constant.btn_help:
                checkDrawableId = R.drawable.ic_help;
                break;
            case Constant.btn_bus:
                checkDrawableId = R.drawable.ic_bush;
                break;
            case Constant.btn_map:
                checkDrawableId = R.drawable.ic_map;
                break;
            case Constant.btn_me:
                checkDrawableId=R.drawable.ic_me;
                break;
            default:break;
        }
        if(mImageView != null){
            mImageView.setImageResource(checkDrawableId);
        }
    }
}

