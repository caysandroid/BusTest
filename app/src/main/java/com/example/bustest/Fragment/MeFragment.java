package com.example.bustest.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bustest.Constant.Constant;
import com.example.bustest.MainActivity;

/**
 * Created by lenovo on 2018/2/20.
 */

public class MeFragment extends BaseFragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        MainActivity.currTag= Constant.fragment_me;
    }
}
