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

}
