package com.example.bustest;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.bustest.Activity.SettingActivity;
import com.example.bustest.Constant.Constant;
import com.example.bustest.Fragment.BaseFragment;
import com.example.bustest.Manager.AbsSuperApplication;
import com.example.bustest.UI.BottomControlPanel.BottomPanelCallback;
import com.example.bustest.UI.BottomControlPanel;
import com.example.bustest.UI.HeadControlPanel;

public class MainActivity extends Activity implements BottomPanelCallback{
    public static Activity activity=null;
    BottomControlPanel bottomPanel;
    HeadControlPanel headPanel;
    private ImageView rightImage;
    private FragmentManager fragmentManager=null;
    private FragmentTransaction fragmentTransaction=null;
    public static String currTag="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rightImage=(ImageView)findViewById(R.id.right_image);
        initUI();
        fragmentManager = getFragmentManager();
        setDefaultFirstFragment(Constant.fragment_love);
        activity=MainActivity.this;
        AbsSuperApplication.activities.add(MainActivity.this);
    }
    private void initUI(){
        bottomPanel = (BottomControlPanel)findViewById(R.id.bottom_layout);
        if(bottomPanel != null){
            bottomPanel.initBottomPanel();
            bottomPanel.setBottomCallback(this);
        }
        headPanel = (HeadControlPanel)findViewById(R.id.head_layout);
        if(headPanel != null){
            headPanel.initHeadPanel();
        }
        rightImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"task",Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override
    public void onBottomPanelClick(int itemId) {
        String tag = "";
        if(itemId==Constant.btn_love){
            tag = Constant.fragment_love;
            Toast.makeText(getApplicationContext(), "love", Toast.LENGTH_SHORT).show();
        }else if(itemId==Constant.btn_help){
            tag = Constant.fragment_help;
            Toast.makeText(getApplicationContext(), "help", Toast.LENGTH_SHORT).show();
        }else if(itemId==Constant.btn_bus){
            tag = Constant.fragment_bus;
            Toast.makeText(getApplicationContext(), "bus", Toast.LENGTH_SHORT).show();
        }else if(itemId==Constant.btn_map){
            tag = Constant.fragment_map;
            Toast.makeText(getApplicationContext(), "map", Toast.LENGTH_SHORT).show();
            //headPanel.setMiddleTitle(tag);
        }else if(itemId==Constant.btn_me){
            tag=Constant.fragment_me;
            Toast.makeText(getApplicationContext(), "me", Toast.LENGTH_SHORT).show();
        }
        setTabSelection(tag); //切换Fragment
        headPanel.setMiddleTitle(tag);//切换标题
        if(tag.equals(Constant.fragment_love)){
            headPanel.setRightImage(R.drawable.ic_right_image);
            headPanel.setRightImageVisibility(true);
        }
        else headPanel.setRightImageVisibility(false);
    }
    private void setDefaultFirstFragment(String tag){
        setTabSelection(tag);
        bottomPanel.defaultBtnChecked();
    }

    private void commitTransactions(String tag){
        if (fragmentTransaction != null && !fragmentTransaction.isEmpty()) {
            fragmentTransaction.commit();
            currTag = tag;
            fragmentTransaction = null;
        }
    }

    private FragmentTransaction ensureTransaction( ){
        if(fragmentTransaction == null){
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        }
        return fragmentTransaction;
    }
    private void attachFragment(int layout, Fragment f, String tag){
        if(f != null){
            if(f.isDetached()){
                ensureTransaction();
                fragmentTransaction.attach(f);
            }else if(!f.isAdded()){
                ensureTransaction();
                fragmentTransaction.add(layout, f, tag);
            }
        }
    }
    private Fragment getFragment(String tag){

        Fragment f = fragmentManager.findFragmentByTag(tag);

        if(f==null){
            f = BaseFragment.newInstance(getApplicationContext(), tag);
        }
        return f;
    }
    private void detachFragment(Fragment f){

        if(f != null && !f.isDetached()){
            ensureTransaction();
            fragmentTransaction.detach(f);
        }
    }
    /**切换fragment
     * @param tag
     */
    private  void switchFragment(String tag){
        if(TextUtils.equals(tag, currTag)){
            return;
        }
        //把上一个fragment detach掉
        if(currTag != null && !currTag.equals("")){
            detachFragment(getFragment(currTag));
        }
        attachFragment(R.id.fragment_content, getFragment(tag), tag);
        commitTransactions(tag);
    }
    /**设置选中的Tag
     * @param tag
     */
    public  void setTabSelection(String tag) {
        // 开启一个Fragment事务
        fragmentTransaction = fragmentManager.beginTransaction();
        switchFragment(tag);
    }
    @Override
    protected void onStop() {
        super.onStop();
        currTag = "";
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {

    }
}
