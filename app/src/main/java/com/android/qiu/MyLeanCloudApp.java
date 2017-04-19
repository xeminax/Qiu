package com.android.qiu;

import android.app.Application;

import com.avos.avoscloud.AVOSCloud;

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
    }
}
