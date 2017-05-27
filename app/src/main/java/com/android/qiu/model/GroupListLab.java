package com.android.qiu.model;

import android.content.Context;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by xemina on 2017/5/1.
 */

public class GroupListLab {
    private static GroupListLab sGroupListLab;
    private List<GroupList> mGroupKindList;
    private List<GroupList> mGroupList;
    private String[] mKindListStr = {"体育","音乐","艺术","娱乐","书籍","饮食","科技","旅行","其他"};
    private String[] mListStr= {"自行车","网球","足球","羽毛球","排球","跑步","拳击","篮球","爬山"};
    public static GroupListLab get(Context context){
        if(sGroupListLab == null){
            sGroupListLab = new GroupListLab(context);
        }
        return sGroupListLab;
    }
    private GroupListLab(Context context){
        mGroupKindList = new ArrayList<>();
        for(int i = 0;i<mKindListStr.length;i++){
            GroupList groupKindList = new GroupList();
            groupKindList.setGroupKindName(mKindListStr[i]);


            mGroupKindList.add(groupKindList);
        }
        mGroupList = new ArrayList<>();
        for(int i = 0;i<mListStr.length;i++){
            GroupList groupList = new GroupList();
            groupList.setGroupName(mListStr[i]);


            mGroupList.add(groupList);
        }

    }
    public List<GroupList> getGroupKindList(){

        return mGroupKindList;

    }

    public List<GroupList> getGroupLists(){
        return mGroupList;
    }
    public GroupList  getGroupLists(UUID id){
        for (GroupList  groupKindList :mGroupKindList){
            if(groupKindList.getGroupKindId().equals(id)){
                return groupKindList;
            }
        }
        return null;
    }
}
