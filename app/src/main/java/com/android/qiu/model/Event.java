package com.android.qiu.model;

import android.graphics.Bitmap;
import android.widget.ImageView;

import java.util.Date;
import java.util.UUID;

/**
 * Created by xemina on 2017/3/9.
 */

public class Event {
    private UUID mId;
    private String mTitle;
    private Date mDate;
    private String mPlace;
    private String mContent;
    private Bitmap picture;
    public  Event(){
        mId = UUID.randomUUID();
        mDate = new Date();
    }
    public Event(UUID id){
        mId = id;
        mDate = new Date();
    }

    public UUID getId() {
        return mId;
    }

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date date) {
        mDate = date;
    }

    public String getPlace() {
        return mPlace;
    }

    public void setPlace(String place) {
        mPlace = place;
    }

    public String getContent() {
        return mContent;
    }

    public void setContent(String content) {
        mContent = content;
    }

    public Bitmap getPicture() {
        return picture;
    }

    public void setPicture(Bitmap picture) {
        this.picture = picture;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }
}
