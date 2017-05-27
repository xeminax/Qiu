package com.android.qiu.model;

import java.util.List;
import java.util.UUID;

/**
 * Created by xemina on 2017/5/1.
 */

public class GroupList {
    private UUID mId;
    private String mGroupKindName;
    private String mGroupName;
    public GroupList() {
        mId = UUID.randomUUID();

    }
    public void setGroupKindName(String name) {
        mGroupKindName = name;
    }
    public String getGroupKindName() {
        return mGroupKindName;
    }
    public void setGroupName(String name){mGroupName = name;}
    public String getGroupName(){return mGroupName;}
    public UUID getGroupKindId(){return mId; }
    public UUID getGroupId(){return mId;}



}
