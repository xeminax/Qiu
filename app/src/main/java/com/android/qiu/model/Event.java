package com.android.qiu.model;

import android.graphics.Bitmap;
import android.widget.ImageView;

import com.avos.avoscloud.AVClassName;
import com.avos.avoscloud.AVFile;
import com.avos.avoscloud.AVGeoPoint;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVUser;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

/**
 * Created by xemina on 2017/3/9.
 */
@AVClassName("Activity")
public class Event extends AVObject implements Serializable {
    private UUID mId;
    private String mTitle;
    private Date mDate;
    private String mPlace;
    private String mContent;
    //private Bitmap picture;
    private AVGeoPoint point;
    private AVFile image;
    private AVUser owner;
    private int joiner_num;

    public  Event(){
        mId = UUID.randomUUID();
        mDate = new Date();
    }

    public AVUser getOwner() {
        return getAVUser("owner");
    }

    public void setOwner(AVUser owner) {
        put("owner",owner);
    }

    public int getJoiner_num() {
        return getInt("joiner_num");
    }

    public void setJoiner_num(int joiner_num) {
        put("joiner_num",joiner_num);
    }
    //public Event(){}


    public AVGeoPoint getPoint() {
        return getAVGeoPoint("location_coordinate");
    }

    public void setPoint(AVGeoPoint point) {
        put("location_coordinate",point);
    }

   /* public Event(UUID id){
        mId = id;
        mDate = new Date();
    }*/


    public UUID getId() {

        return mId;
    }

    public Date getDate() {

        return getDate("time");
    }

    public void setDate(Date date) {

        put("time",date);
    }

    public String getPlace() {

        return getString("location_string");
    }

    public AVFile getImage() {
        return getAVFile("image");
    }

    public void setImage(AVFile image) {
        put("image",image);
    }

    public void setPlace(String place) {

       put("location_string",place);
    }

    public String getContent() {

        return getString("description");
    }

    public void setContent(String content) {

        put("description",content);
    }

    /*public Bitmap getPicture() {
        return picture;
    }

    public void setPicture(Bitmap picture) {

        this.picture = picture;
    }*/

    public String getTitle() {
        return getString("title");
    }

    public void setTitle(String title) {
        put("title",title);
    }
}
