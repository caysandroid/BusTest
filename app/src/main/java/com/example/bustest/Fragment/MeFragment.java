package com.example.bustest.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.example.bustest.Activity.SettingActivity;
import com.example.bustest.Constant.Constant;
import com.example.bustest.MainActivity;
import com.example.bustest.MeUi.MeSetting;
import com.example.bustest.MeUi.MeSettingAdapter;
import com.example.bustest.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 2018/2/20.
 */

public class MeFragment extends BaseFragment implements AdapterView.OnItemClickListener{
    private List<MeSetting> settingList=new ArrayList<>();
    private ListView listView;
    private View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if(view==null){
            view=inflater.inflate(R.layout.me_layout,container,false);
            initSetting();
        }
        MeSettingAdapter adapter=new MeSettingAdapter(getActivity(),R.layout.me_item,settingList);
        listView=(ListView)view.findViewById(R.id.setting_list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
        return view;
    }
    public void initSetting(){
        MeSetting meSetting=new MeSetting(Constant.me_setting);
        settingList.add(meSetting);
        MeSetting meSetting1=new MeSetting(Constant.me_about);
        settingList.add(meSetting1);
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onResume() {
        super.onResume();
        MainActivity.currTag= Constant.fragment_me;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        MeSetting meSetting=settingList.get(position);
        if(meSetting.getName().equals(Constant.me_setting)){
            Intent intent=new Intent(getActivity(), SettingActivity.class);
            startActivity(intent);
        }else if(meSetting.getName().equals(Constant.me_about)){
            Toast.makeText(getActivity(),meSetting.getName(),Toast.LENGTH_SHORT).show();
        }
    }
}
