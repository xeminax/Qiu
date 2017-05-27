package com.android.qiu.model;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.FindCallback;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

/**
 * Created by xemina on 2017/3/27.
 */

public class EventLab {

    private static EventLab sEventLab;
    private List<Event> mEvents;
    private Bitmap image;
    private List<Event> events;
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
    /*public List<Event> getEvents(){

        return mEvents;

    }*/

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

    public List<Event> getEventsALLFromLean(){
        events = new ArrayList<>();
        //events = null;
        events.clear();
        AVQuery<Event> avQuery = AVObject.getQuery(Event.class);
        avQuery.orderByDescending("createdAt");
        avQuery.findInBackground(new FindCallback<Event>() {
            @Override
            public void done(List<Event> list, AVException e) {
                if (e == null) {
                    events.addAll(list);
                    //System.out.println(list.size()+" find");
                } else {
                    e.printStackTrace();
                }
                //System.out.println(events.size()+" find2");
            }
        });

        //Log.d("test", String.valueOf(events.size()));
        //System.out.println(events.size()+" EventLab");
        return events;
    }

    /*public List<Event> getNearEvents (AVGeoPoint point){
        //final List<Event> events = new ArrayList<>();
        final List<Event> events = null;
        AVQuery<AVObject> query = new AVQuery<>("Activity");
        query.limit(10);
        query.whereNear("whereCreated",point);
        query.findInBackground(new FindCallback<AVObject>() {
            @Override
            public void done(List<AVObject> list, AVException e) {
                //List<AVObject> nearbyTodos = list;// 离这个位置最近的 10 个 Todo 对象
                for(AVObject activity : list){
                    image = null;
                    Event event = new Event();
                    event.setTitle(activity.getString("title"));
                    event.setPlace(activity.getString("location_string"));
                    event.setContent(activity.getString("description"));
                    //bytes =  activity.getAVFile("image").getData();
                    AVFile avFile = activity.getAVFile("image");
                    avFile.getDataInBackground(new GetDataCallback(){
                        @Override
                        public void done(byte[] bytes, AVException e) {
                            // bytes 就是文件的数据流
                            if(bytes.length!=0){
                                image = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                            }
                            else{
                                image = null;
                            }

                        }
                    }, new ProgressCallback() {
                        @Override
                        public void done(Integer integer) {
                            // 下载进度数据，integer 介于 0 和 100。
                        }
                    });
                    event.setPicture(image);

                    events.add(event);
                }
            }
        });

        return  events;
    }*/


}
