package com.android.qiu;

import android.app.Application;

import com.android.qiu.model.Event;
import com.android.qiu.qiu.CustomUserProvider;
import com.avos.avoscloud.AVOSCloud;
import com.avos.avoscloud.AVObject;
import com.baidu.mapapi.SDKInitializer;

import cn.leancloud.chatkit.LCChatKit;

/**
 * Created by xemina on 2017/4/19.
 */

public class MyLeanCloudApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        AVObject.registerSubclass(Event.class);
        // 初始化参数依次为 this, AppId, AppKey
        AVOSCloud.initialize(this, "uDdJDzoKsgSXI0qfGvEV3XgA-gzGzoHsz", "ESQKHvj6mEHiuISfsGHTl4xp");
        AVOSCloud.setDebugLogEnabled(true);

        // 在使用 SDK 各组间之前初始化 context 信息，传入 ApplicationContext
        SDKInitializer.initialize(this);

        LCChatKit.getInstance().setProfileProvider(CustomUserProvider.getInstance());
        LCChatKit.getInstance().init(this, "uDdJDzoKsgSXI0qfGvEV3XgA-gzGzoHsz", "ESQKHvj6mEHiuISfsGHTl4xp");
    }
}
