package com.android.qiu.model;

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
    private ImageView mImageView;
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

    public ImageView getImageView() {
        return mImageView;
    }

    public void setImageView(ImageView imageView) {
        mImageView = imageView;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }
}
