package com.example.bustest.Fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.LocationSource;
import com.amap.api.maps.MapView;
import com.amap.api.maps.UiSettings;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.MyLocationStyle;
import com.example.bustest.Constant.Constant;
import com.example.bustest.MainActivity;
import com.example.bustest.R;

import java.util.zip.Inflater;

/**
 * Created by lenovo on 2018/2/20.
 */

public class MapFragment extends BaseFragment implements LocationSource,AMapLocationListener{
    private AMap aMap=null;//地图对象
    //定位发起端
    private AMapLocationClient mLocationClient=null;
    //定位设置
    private AMapLocationClientOption aMapOption=null;
    //定位监听器
    private OnLocationChangedListener mListener=null;
    //第一次定位
    private boolean isFirstLoc=true;
    private MyLocationStyle myStyle;
    private MapView mapView;
    /*public static MapFragment newInstance(){
        MapFragment mapFragment=new MapFragment();
        return mapFragment;
    }
    public MapFragment(){

    }*/
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.map_layout, container, false);
        mapView = (MapView) view.findViewById(R.id.map);
        mapView.onCreate(savedInstanceState);
        aMap = mapView.getMap();
        myStyle = new MyLocationStyle();
        myStyle.strokeColor(Color.argb(0, 0, 0, 0));
        myStyle.radiusFillColor(Color.argb(0, 0, 0, 0));
        aMap.setMyLocationStyle(myStyle);
        UiSettings settings = aMap.getUiSettings();
        //设置显示定位按钮，并可以点击
        settings.setMyLocationButtonEnabled(true);//显示定位按钮
        aMap.setLocationSource(this);//定位监听
        aMap.setMyLocationEnabled(true);////显示定位层并且可以触发定位,默认是flase
        location();
        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public void activate(OnLocationChangedListener onLocationChangedListener) {
        mListener=onLocationChangedListener;
        if(mLocationClient==null)location();
    }

    public void location(){
        //初始化定位
        mLocationClient = new AMapLocationClient(getActivity());
        mLocationClient.setLocationListener(this);
        aMapOption = new AMapLocationClientOption();
        //设置定位模式为Hight_Accuracy高精度模式，Battery_Saving为低功耗模式，Device_Sensors是仅设备模式
        aMapOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        //设置是否返回地址信息（默认返回地址信息）
        aMapOption.setNeedAddress(true);
        //设置是否只定位一次,默认为false
        //aMapOption.setOnceLocation(true);
        aMapOption.setOnceLocationLatest(true);
        //设置是否允许模拟位置,默认为false，不允许模拟位置
        aMapOption.setMockEnable(true);
        //设置定位间隔,单位毫秒,默认为2000ms
        //aMapOption.setInterval(2000);
        //给定位客户端对象设置定位参数
        mLocationClient.setLocationOption(aMapOption);
        mLocationClient.startLocation();
    }

    @Override
    public void onResume() {
        super.onResume();
        MainActivity.currTag= Constant.fragment_map;
        mapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {
        if(aMapLocation!=null&&mListener!=null){
            if(aMapLocation.getErrorCode()==0){
                //定位成功回调信息
                int locationType=aMapLocation.getLocationType();
                double latitude = aMapLocation.getLatitude();
                double longitude=aMapLocation.getLongitude();
                //定位一次
                if(isFirstLoc){
                    //设置缩放级别
                    aMap.moveCamera(CameraUpdateFactory.zoomTo(17f));
                    //移动到定位点
                    aMap.moveCamera(CameraUpdateFactory.changeLatLng(new LatLng(latitude,longitude)));
                    mListener.onLocationChanged(aMapLocation);
                    isFirstLoc=true;
                }
            }
        }
    }

    @Override
    public void deactivate() {
        mListener=null;
        if(mLocationClient!=null){
            mLocationClient.stopLocation();
            mLocationClient.onDestroy();
        }
        mLocationClient=null;
    }
}
