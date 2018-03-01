package com.example.bustest.Fragment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bustest.Constant.Constant;

/**
 * Created by lenovo on 2018/2/18.
 */

public class BaseFragment extends Fragment{
    private static final String TAG="BaseFragment";
    protected FragmentManager mFragmentManager=null;
    protected FragmentTransaction mFragmentTransaction=null;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onStart() {
        super.onStart();
    }
    public static BaseFragment newInstance(Context context,String tag){
        BaseFragment baseFragment =  null;
        if(TextUtils.equals(tag, Constant.fragment_love)){
            baseFragment = new LoveFragment();
        }else if(TextUtils.equals(tag, Constant.fragment_help)){
            baseFragment = new HelpFragment();
        }else if(TextUtils.equals(tag, Constant.fragment_bus)){
            baseFragment = new BusFragment();
        }else if(TextUtils.equals(tag, Constant.fragment_map)){
            baseFragment = new MapFragment();
        } else if (TextUtils.equals(tag, Constant.fragment_me)) {
            baseFragment=new MeFragment();
        }
        return baseFragment;

    }
}
