package com.android.qiu.qiu;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.qiu.model.Event;

import java.util.List;


/**
 * Created by xemina on 2017/3/30.
 */

public class EventListAdapter extends RecyclerView.Adapter<EventListAdapter.EventHolder> {

    private List<Event> mEvents;

    public class EventHolder extends RecyclerView.ViewHolder {
        private TextView mTitleTextView;
        private Event mEvent;
        private  TextView mDateTextView;
        private TextView mPlaceTextView;
        private TextView mContentTextView;
        private ImageView mImageView;

        public EventHolder(View itemView) {
            super(itemView);

            mTitleTextView = (TextView) itemView.findViewById(R.id.list_item_event_title_view);
            mDateTextView = (TextView) itemView.findViewById(R.id.list_item_event_date_view);
            mPlaceTextView = (TextView) itemView.findViewById(R.id.list_item_event_place_view);
            mContentTextView = (TextView) itemView.findViewById(R.id.list_item_event_content_view);
            mImageView = (ImageView) itemView.findViewById(R.id.list_item_event_image_view);
        }
        public void bindEvent(Event event){
            mEvent = event;
            mTitleTextView.setText(mEvent.getTitle());
            mPlaceTextView.setText(mEvent.getPlace());
            mDateTextView.setText(mEvent.getDate().toString());
            mContentTextView.setText(mEvent.getContent());
        }


    }
    public  EventListAdapter(List<Event> events){
        mEvents = events;
    }
    @Override
    public EventListAdapter.EventHolder onCreateViewHolder(ViewGroup parent, int viewType){
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.list_item_event,parent,false);
        return new EventListAdapter.EventHolder(view);
    }

    @Override
    public void onBindViewHolder(EventListAdapter.EventHolder holder, int position){
        Event event = mEvents.get(position);
        holder.bindEvent(event);

    }

    @Override
    public  int getItemCount(){
        return mEvents.size();
    }


}
