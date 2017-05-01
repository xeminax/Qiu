package com.android.qiu.model;

import android.content.Context;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by xemina on 2017/5/1.
 */

public class GroupListLab {
    private static GroupListLab sGroupListLab;
    private List<GroupList> mGroupKindList;
    private List<GroupList> mGroupList;
    private String[] mKindListStr = {"体育","音乐","艺术","娱乐","书籍","饮食","科技","旅行","其他"};
    public static GroupListLab get(Context context){
        if(sGroupListLab == null){
            sGroupListLab = new GroupListLab(context);
        }
        return sGroupListLab;
    }
    private GroupListLab(Context context){
        mGroupKindList = new ArrayList<>();
        for(int i = 0;i<10;i++){
            GroupList groupKindList = new GroupList();
            groupKindList.setGroupKindName(mKindListStr[i]);


            mGroupKindList.add(groupKindList);
        }

    }
    public List<GroupList> getGroupKindList(){

        return mGroupKindList;

    }
    public List<GroupList> getGroupLists(){
        return mGroupList;
    }
}
