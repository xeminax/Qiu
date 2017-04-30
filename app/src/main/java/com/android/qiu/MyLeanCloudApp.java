package com.android.qiu;

import android.app.Application;

import com.avos.avoscloud.AVOSCloud;
import com.baidu.mapapi.SDKInitializer;

/**
 * Created by xemina on 2017/4/19.
 */

public class MyLeanCloudApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        // 初始化参数依次为 this, AppId, AppKey
        AVOSCloud.initialize(this,"uDdJDzoKsgSXI0qfGvEV3XgA-gzGzoHsz","ESQKHvj6mEHiuISfsGHTl4xp");
        AVOSCloud.setDebugLogEnabled(true);

        // 在使用 SDK 各组间之前初始化 context 信息，传入 ApplicationContext
        SDKInitializer.initialize(this);
    }
}
