package com.example.memorymanager.location;

import android.content.ContentResolver;
import android.content.Context;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.location.LocationClientOption.*;
import com.example.memorymanager.model.Location;

public class LocationService {
    LocationClient locationClient;
    private final OnLocationReceivedCallback callback;
    public LocationService(Context context, OnLocationReceivedCallback callback) {
        this.callback = callback;

        LocationClient.setAgreePrivacy(true);
        try {
            locationClient = new LocationClient(context);
        }catch (Exception e) {
            e.printStackTrace();
        }
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationMode.Hight_Accuracy);
        option.setCoorType("bd09ll");
        option.setFirstLocType(FirstLocType.SPEED_IN_FIRST_LOC);
        option.setOnceLocation(true);
        option.setScanSpan(0);
        option.setOpenGnss(true);
        option.setLocationNotify(true);
        option.setIgnoreKillProcess(false);
        option.setIsNeedAddress(true);
        option.setIsNeedLocationDescribe(true);
        locationClient.setLocOption(option);

        locationClient.registerLocationListener(new MyLocationListener());
    }
    public void start() {
        locationClient.start();
    }

    public void stop() {
        if(locationClient != null) {
            locationClient.stop();
        }
    }
    private class MyLocationListener extends BDAbstractLocationListener {
        @Override
        public void onReceiveLocation(BDLocation location){
            //此处的BDLocation为定位结果信息类，通过它的各种get方法可获取定位相关的全部结果
            //以下只列举部分获取经纬度相关（常用）的结果信息
            //更多结果信息获取说明，请参照类参考中BDLocation类中的说明
            if (location == null) return;
            double latitude = location.getLatitude();    //获取纬度信息
            double longitude = location.getLongitude();    //获取经度信息
            String city = location.getCity();    //获取城市
            String locationDescribe = location.getLocationDescribe();    //获取位置描述信息
            String coorType = location.getCoorType();
            //获取经纬度坐标类型，以LocationClientOption中设置过的坐标类型为准
            int errorCode = location.getLocType();
            //获取定位类型、定位错误返回码，具体信息可参照类参考中BDLocation类中的说明

            if (callback != null) {
                callback.onLocationReceived(new Location(latitude, longitude, city+' '+locationDescribe));
            }

            stop();
        }
    }
}

