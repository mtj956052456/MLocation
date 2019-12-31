package com.mtj.location;

import com.amap.api.location.AMapLocation;

/**
 * 创建者: lenovo
 * 时间: 2019/12/31
 * 描述:
 */
public interface ICallBack {

    void success(AMapLocation amapLocation);

    void error(String errorMsg);
}
