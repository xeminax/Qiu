package com.android.qiu.model;

import android.content.Context;

import java.util.ArrayList;
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
        mEvents = new ArrayList<>();
        for(int i = 0;i<100;i++){
            Event event = new Event();
            event.setTitle("Event #"+i);
            event.setPlace("earth");
            event.setContent("come and play");

            mEvents.add(event);
        }

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


}
