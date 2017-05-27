package com.android.qiu.qiu;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MyFollowGroupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_follow_group);

        FragmentManager fm=getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragment_container_group_list);
        if(fragment==null){
            fragment = new MyFollowGroupFragment();
            fm.beginTransaction().add(R.id.fragment_container_group_list,fragment).commit();
        }

    }
}
