package com.android.qiu.qiu;

import android.util.Log;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.GetCallback;

import java.util.ArrayList;
import java.util.List;

import cn.leancloud.chatkit.LCChatKitUser;
import cn.leancloud.chatkit.LCChatProfileProvider;
import cn.leancloud.chatkit.LCChatProfilesCallBack;

/**
 * Created by Lee on 17/5/10.
 * 实现自定义用户体系
 */
public class CustomUserProvider implements LCChatProfileProvider {

    private static CustomUserProvider customUserProvider;
    private static List<LCChatKitUser> partUsers = new ArrayList<LCChatKitUser>();

    public synchronized static CustomUserProvider getInstance() {
        if (null == customUserProvider) {
            customUserProvider = new CustomUserProvider();
        }
        return customUserProvider;
    }

    private CustomUserProvider() {
    }


    @Override
    public void fetchProfiles(List<String> list_userId, LCChatProfilesCallBack callBack) {

        List<LCChatKitUser> userList = new ArrayList<LCChatKitUser>();
        for (String userId : list_userId) {
            for (LCChatKitUser user : partUsers) {
                if (user.getUserId().equals(userId)) {
                    userList.add(user);
                    break;
                }
            }
        }
        callBack.done(userList, null);


    }


    public void addUsers(LCChatKitUser avuser) {
        partUsers.add(avuser);
    }
}
