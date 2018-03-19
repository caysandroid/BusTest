package com.example.bustest.MeUi;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.bustest.R;

import java.util.List;

/**
 * Created by lenovo on 2018/3/4.
 */

public class MeSettingAdapter extends ArrayAdapter<MeSetting>{

    private int resourceId;
    public MeSettingAdapter(Context context, int resource, List<MeSetting> objects) {
        super(context, resource, objects);
        resourceId=resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view;
        MeSetting meSetting=getItem(position);
        if(convertView==null){
            view= LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
        }else{
            view=convertView;
        }
        TextView settingTitle=(TextView) view.findViewById(R.id.setting_title);
        settingTitle.setText(meSetting.getName());
        return view;
    }
}
