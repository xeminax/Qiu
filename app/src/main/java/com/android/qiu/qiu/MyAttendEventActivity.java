package com.android.qiu.qiu;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by xemina on 2017/5/28.
 */

public class MyAttendEventActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_attend_event);

        FragmentManager fm=getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragment_container_myattend);
        if(fragment==null){
            fragment = new MyPostEventFragment();
            fm.beginTransaction().add(R.id.fragment_container_myattend,fragment).commit();
        }

    }
}
