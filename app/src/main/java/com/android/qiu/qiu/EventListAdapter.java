package com.android.qiu.qiu;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.qiu.model.Event;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by xemina on 2017/3/30.
 */

public class EventListAdapter extends RecyclerView.Adapter<EventListAdapter.EventHolder> {

    private List<Event> mEvents = new ArrayList<>();
    private Context mContext;

    public  EventListAdapter(List<Event> events , Context context){
        this.mEvents = events;
        this.mContext = context;
    }



    @Override
    public EventHolder onCreateViewHolder (ViewGroup parent, int viewType){
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.list_item_event,parent,false);
        return new EventHolder(view);
    }

    @Override
    public void onBindViewHolder(EventHolder holder, int position){
        Event event = mEvents.get(position);
        holder.bindEvent(event);

    }

    @Override
    public  int getItemCount(){
        return mEvents.size();
    }

    class EventHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView mTitleTextView;
        private Event mEvent;
        private TextView mDateTextView;
        private TextView mPlaceTextView;
        private TextView mContentTextView;
        private ImageView mImageView;
        private ImageView mGroupImage;
        private TextView mGroupName;

        public EventHolder(View itemView)  {
            super(itemView);
            itemView.setOnClickListener(this);
            mTitleTextView = (TextView) itemView.findViewById(R.id.list_item_event_title_view);
            mDateTextView = (TextView) itemView.findViewById(R.id.list_item_event_date_view);
            mPlaceTextView = (TextView) itemView.findViewById(R.id.list_item_event_place_view);
            mContentTextView = (TextView) itemView.findViewById(R.id.list_item_event_content_view);
            mImageView = (ImageView) itemView.findViewById(R.id.list_item_event_image_view);
            mGroupImage = (ImageView) itemView.findViewById(R.id.list_item_group_profile);
            mGroupName = (TextView) itemView.findViewById(R.id.list_item_group_name);
            mGroupImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(mContext,GroupActivity.class);
                    mContext.startActivity(intent);
                }
            });
            mGroupName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(mContext,GroupActivity.class);
                    mContext.startActivity(intent);
                }
            });


        }

        public void bindEvent(Event event) {
            mEvent = event;
            mTitleTextView.setText(mEvent.getTitle());
            mPlaceTextView.setText(mEvent.getPlace());
            mDateTextView.setText(mEvent.getDate().toString());
            mContentTextView.setText(mEvent.getContent());
            Picasso.with(mContext).load(event.getAVFile("image") == null ? "www" : event.getAVFile("image").getUrl()).transform(new RoundedTransformation(9, 0)).into(mImageView);
        }

        @Override
        public void onClick(View v){

            //Intent intent = EventActivity.newIntent(mContext,mEvent.getId());
            Intent intent = new Intent(mContext,EventActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("event",mEvent);
            intent.putExtras(bundle);
            //intent.putExtra("EventId",mEvent.getId().toString());

            mContext.startActivity(intent);
        }
    }
}
