package com.mtj.location;

import android.content.Context;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;

/**
 * 创建者: lenovo
 * 时间: 2019/12/31
 * 描述:
 */
public class LocationUtil {

    private ICallBack callBack;

    public void regist(ICallBack callBack) {
        callBack = callBack;
    }

    public void init(Context context) {
        //声明mlocationClient对象
        AMapLocationClient mlocationClient = new AMapLocationClient(context);
        //初始化定位参数
        //声明mLocationOption对象
        AMapLocationClientOption locationOption = new AMapLocationClientOption();
        //设置定位监听
        mlocationClient.setLocationListener(new AMapLocationListener() {
            @Override
            public void onLocationChanged(AMapLocation amapLocation) {
                if (amapLocation != null) {
                    if (amapLocation.getErrorCode() == 0) {
                        //定位成功回调信息，设置相关消息
                        //amapLocation.getLocationType();//获取当前定位结果来源，如网络定位结果，详见定位类型表
                        //amapLocation.getLatitude();//获取纬度
                        //amapLocation.getLongitude();//获取经度
                        //amapLocation.getAccuracy();//获取精度信息
                        //SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        //Date date = new Date(amapLocation.getTime());
                        //df.format(date);//定位时间
                        callBack.success(amapLocation);
                    } else {
                        callBack.error("location Error, ErrCode:" + amapLocation.getErrorCode() + ", errInfo:" + amapLocation.getErrorInfo());
                        //显示错误信息ErrCode是错误码，errInfo是错误信息，详见错误码表。
                        //Log.e("AmapError", "location Error, ErrCode:" + amapLocation.getErrorCode() + ", errInfo:" + amapLocation.getErrorInfo());
                    }
                }
            }
        });
        //设置定位模式为高精度模式，Battery_Saving为低功耗模式，Device_Sensors是仅设备模式
        locationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        //设置定位间隔,单位毫秒,默认为2000ms
        locationOption.setInterval(2000);
        //设置定位参数
        mlocationClient.setLocationOption(locationOption);
        // 此方法为每隔固定时间会发起一次定位请求，为了减少电量消耗或网络流量消耗，
        // 注意设置合适的定位时间的间隔（最小间隔支持为1000ms），并且在合适时间调用stopLocation()方法来取消定位请求
        // 在定位结束后，在合适的生命周期调用onDestroy()方法
        // 在单次定位情况下，定位无论成功与否，都无需调用stopLocation()方法移除请求，定位sdk内部会移除
        //启动定位
        mlocationClient.startLocation();
    }


}
