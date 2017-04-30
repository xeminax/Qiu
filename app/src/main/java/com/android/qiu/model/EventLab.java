package com.android.qiu.model;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVFile;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.FindCallback;
import com.avos.avoscloud.GetDataCallback;
import com.avos.avoscloud.ProgressCallback;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by xemina on 2017/3/27.
 */

public class EventLab {

    private static EventLab sEventLab;
    private List<Event> mEvents;
    public static EventLab get(Context context){
        if(sEventLab == null){
            sEventLab = new EventLab(context);
        }
        return sEventLab;
    }
    private EventLab(Context context){
        /*mEvents = new ArrayList<>();
        for(int i = 0;i<100;i++){
            Event event = new Event();
            event.setTitle("Event #"+i);
            event.setPlace("earth");
            event.setContent("come and play");

            mEvents.add(event);
        }*/
        mEvents = getEventsALLFromLean();

    }
    public List<Event> getEvents(){

        return mEvents;

    }

    public Event getEvent(UUID id){
        for (Event event:mEvents){
            if (event.getId().equals(id)){
                return event;
            }
        }
        return null;
    }
    public void addEvent(Event event){
        mEvents.add(event);
    }

    private List<Event> getEventsALLFromLean(){
        final ArrayList<Event> Events = new ArrayList<Event>();
        final ArrayList<AVObject> mList = new ArrayList<>();
        AVQuery<AVObject> avQuery = new AVQuery<>("Activity");
        avQuery.orderByDescending("createdAt");
        avQuery.findInBackground(new FindCallback<AVObject>() {
            @Override
            public void done(List<AVObject> list, AVException e) {
                if (e == null) {
                    //int count = 1;
                    for(AVObject activity :  list){
                        //byte[] bytes = null;
                        final Bitmap[] image = {null};
                        Event event = new Event();
                        event.setTitle(activity.getString("title"));
                        //++count;
                        event.setPlace(activity.getString("location_string"));

                        event.setContent(activity.getString("description"));
                        //bytes =  activity.getAVFile("image").getData();
                        AVFile avFile = activity.getAVFile("image");
                        avFile.getDataInBackground(new GetDataCallback(){
                            @Override
                            public void done(byte[] bytes, AVException e) {
                                // bytes 就是文件的数据流
                                image[0] = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);

                            }
                        }, new ProgressCallback() {
                            @Override
                            public void done(Integer integer) {
                                // 下载进度数据，integer 介于 0 和 100。
                            }
                        });
                        event.setPicture(image[0]);
                        Events.add(event);
                    }
                } else {
                    e.printStackTrace();
                }
            }
        });
        return Events;
    }


}
