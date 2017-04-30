package com.android.qiu.qiu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

public class GroupListActivity extends AppCompatActivity {
    private String[] mListStr = {"体育","音乐","艺术","娱乐","书籍","饮食","科技","旅行","其他"};
    ListView mGroupListView ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mGroupListView = new ListView(this);
        mGroupListView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1,mListStr));
        setContentView(mGroupListView);


    }
}
