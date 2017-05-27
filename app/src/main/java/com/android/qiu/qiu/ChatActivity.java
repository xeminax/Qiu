package com.android.qiu.qiu;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import cn.leancloud.chatkit.activity.LCIMConversationFragment;
import cn.leancloud.chatkit.activity.LCIMConversationListFragment;


public class ChatActivity extends SingleFragmentActivity {

    protected Fragment createFragment() {
        return new LCIMConversationListFragment();
    }
}


