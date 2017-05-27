package com.android.qiu.qiu;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;
import java.util.UUID;

public class GroupKindListActivity extends AppCompatActivity {
    public static final String EXTRA_KIND_ID="com.qiu.android.groupKind_id";
    public static Intent newIntent(Context packageContext, UUID groupKindId){
        Intent intent =new Intent (packageContext,GroupKindListActivity.class);
        return intent;
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_list);

        FragmentManager fm=getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragment_container_group_list);
        if(fragment==null){
            fragment = new GroupKindListFragment();
            fm.beginTransaction().add(R.id.fragment_container_group_list,fragment).commit();
        }





    }
}
