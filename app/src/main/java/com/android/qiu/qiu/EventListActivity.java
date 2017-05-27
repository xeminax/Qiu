package com.android.qiu.qiu;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.qiu.model.Event;
import com.android.qiu.model.EventLab;
import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.GetCallback;
import com.avos.avoscloud.im.v2.AVIMClient;
import com.avos.avoscloud.im.v2.AVIMException;
import com.avos.avoscloud.im.v2.callback.AVIMClientCallback;

import cn.leancloud.chatkit.LCChatKit;
import cn.leancloud.chatkit.LCChatKitUser;
import cn.leancloud.chatkit.activity.LCIMConversationActivity;
import cn.leancloud.chatkit.utils.LCIMConstants;


public class EventListActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;
    private Button mloginButton;
    private ImageView mMyprofile;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_list);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());


        mViewPager = (ViewPager) findViewById(R.id.viewpage_container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Event event = new Event();
                EventLab.get(EventListActivity.this).addEvent(event);*/
                Intent intent = new Intent(EventListActivity.this, AddEventActivity.class);
                startActivity(intent);
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);

        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setItemIconTintList(null);


        navigationView.setNavigationItemSelectedListener(this);

        View headerView = navigationView.getHeaderView(0);
        mMyprofile = (ImageView) headerView.findViewById(R.id.userprofile);
        mMyprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(EventListActivity.this, UserinfoActivity.class);
                startActivity(i);
            }
        });
        mloginButton = (Button) headerView.findViewById(R.id.login);
        mloginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(EventListActivity.this, LoginActivity.class);
                startActivity(i);
            }
        });



        // 开启聊天服务
        connectChatServe();


    }
 /* private void setDefaultFragment(){
        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragment_container);
        if(fragment == null){
            fragment = new MyEventListFragment();
            fm.beginTransaction()
                    .add(R.id.fragment_container,fragment)
                    .commit();

        }



    } */

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.menu_item_discovery) {
            Toast.makeText(this, "you clicked Add", Toast.LENGTH_SHORT).show();

        }

        return true;
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.menu_item_discovery) {
            Intent i = new Intent(EventListActivity.this, GroupKindListActivity.class);
            startActivity(i);


        } else if (id == R.id.menu_item_message) {

            Intent i = new Intent(EventListActivity.this, ChatActivity.class);
            startActivity(i);


        } else if (id == R.id.menu_item_mysubs) {
            Intent i = new Intent(EventListActivity.this,MyFollowGroupActivity.class);
            startActivity(i);

        } else if (id == R.id.menu_item_mypost) {
            Intent i = new Intent(EventListActivity.this,MyPostEventActivity.class);
            startActivity(i);

        } else if (id == R.id.logout) {
            AVUser.getCurrentUser().logOut();
            startActivity(new Intent(EventListActivity.this, LoginActivity.class));
            EventListActivity.this.finish();
        }
        else if (id == R.id.menu_item_myattend) {
            Intent i = new Intent(EventListActivity.this,MyAttendEventActivity.class);
            startActivity(i);

        }
        else if (id == R.id.setting) {
            Intent i = new Intent(EventListActivity.this,SettingsActivity.class);
            startActivity(i);

        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    private void connectChatServe() {
        /*String clientId = AVUser.getCurrentUser().getObjectId();
        if (TextUtils.isEmpty(clientId)) {
            return;
        }*/
        AVUser user = AVUser.getCurrentUser();
        if (user == null) {
            return;
        }
        String clientId = user.getObjectId();

        if (TextUtils.isEmpty(clientId)) {
            return;
        }


        LCChatKit.getInstance().open(clientId, new AVIMClientCallback() {
            @Override
            public void done(AVIMClient avimClient, AVIMException e) {


                if (null == e) {
                    Toast.makeText(EventListActivity.this, "聊天服务启动成功！", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(EventListActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}