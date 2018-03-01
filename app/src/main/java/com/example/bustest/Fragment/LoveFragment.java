package com.example.bustest.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.bustest.Constant.Constant;
import com.example.bustest.MainActivity;
import com.example.bustest.R;

import java.util.zip.Inflater;

/**
 * Created by lenovo on 2018/2/20.
 */

public class LoveFragment extends BaseFragment {
    private TextView nothingText;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.love_fragment,container,false);
        nothingText=(TextView)view.findViewById(R.id.nothing_text);
        nothingText.setText("This is Nothing Fragment!");
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        MainActivity.currTag= Constant.fragment_love;
    }
}
