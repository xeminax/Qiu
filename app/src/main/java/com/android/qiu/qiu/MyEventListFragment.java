package com.android.qiu.qiu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.qiu.model.Event;
import com.android.qiu.model.EventLab;
import com.avos.avoscloud.AVAnalytics;
import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.FindCallback;
import com.cjj.MaterialRefreshLayout;
import com.cjj.MaterialRefreshListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xemina on 2017/3/27.
 */

public class MyEventListFragment extends Fragment {
    private static final String ARG_SECTION_NUMBER = "sectionNumber";

    private RecyclerView mEventRecyclerView;
    //private EventAdapter mEventAdapter;
    private EventListAdapter mEventAdapter;

    private MaterialRefreshLayout mMaterialRefreshLayout;
    private List<Event> events = new ArrayList<>();





    public static MyEventListFragment newInstance(int sectionNumber) {
        MyEventListFragment fragment = new MyEventListFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_myevent_list, container, false);
        mEventRecyclerView = (RecyclerView) view.findViewById(R.id.event_recycler_view);
        mMaterialRefreshLayout = (MaterialRefreshLayout) view.findViewById(R.id.refresh);

        mEventRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mEventAdapter = new EventListAdapter(events,getActivity());
        mEventRecyclerView.setAdapter(mEventAdapter);
        //initData();
        //System.out.println(events.size()+" My onCreate");


        mMaterialRefreshLayout.setMaterialRefreshListener(new MaterialRefreshListener() {
            @Override
            public void onRefresh(final MaterialRefreshLayout materialRefreshLayout) {
                //get data from could
                initData();
                //System.out.println(events.size()+" My onRefresh");
                //mEventAdapter.notifyDataSetChanged();
                mMaterialRefreshLayout.finishRefresh();
                Log.v("Test","onRefresh------------");

            }

            @Override
            public void onRefreshLoadMore(final MaterialRefreshLayout materialRefreshLayout) {

                Log.v("Test","onRefreshLoadMore------------");
                /*EventLab eventLab = EventLab.get(getActivity());
                events = eventLab.getEventsALLFromLean();
                //events.addAll(eventLab.moreEvents(events.size()));
                System.out.println(events.size()+" My onRefreshLoadMore");*/
                //initData();
                loadMore(events.size());
                materialRefreshLayout.finishRefreshLoadMore();
            }


        });

        return view;
    }


    private void initData() {
        events.clear();
       /* EventLab eventLab = EventLab.get(getActivity());
        events = eventLab.getEventsALLFromLean();*/
        AVQuery<Event> avQuery = AVObject.getQuery(Event.class);
        avQuery.orderByDescending("createdAt");
        avQuery.limit(10);
        avQuery.findInBackground(new FindCallback<Event>() {
            @Override
            public void done(List<Event> list, AVException e) {
                if (e == null) {
                    events.addAll(list);
                    mEventAdapter.notifyDataSetChanged();
                    //System.out.println(list.size()+" f");
                } else {
                    e.printStackTrace();
                }
                //System.out.println(events.size()+" f2");
            }
        });

    }

    private void loadMore(int size){
        AVQuery<Event> avQuery = AVObject.getQuery(Event.class);
        avQuery.orderByDescending("createdAt");
        avQuery.limit(10);
        avQuery.skip(size);
        avQuery.findInBackground(new FindCallback<Event>() {
            @Override
            public void done(List<Event> list, AVException e) {
                if (e == null) {
                    events.addAll(list);
                    mEventAdapter.notifyDataSetChanged();
                } else {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        AVAnalytics.onResume(getActivity());
        initData();
    }

    @Override
    public void onPause() {
        super.onPause();
        AVAnalytics.onPause(getActivity());
    }

  /* @Override
    public void onResume(){
        super.onResume();
        updateUI();
    }

    private void updateUI() {
        EventLab eventLab = EventLab.get(getActivity());
        List<Event> events = eventLab.getEventsALLFromLean();
        if (mEventAdapter == null) {
            mEventAdapter = new EventListAdapter(events);
            mEventRecyclerView.setAdapter(mEventAdapter);
        } else {
            mEventAdapter.notifyDataSetChanged();
        }
    }*/
        /*private class EventHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
            private TextView mTitleTextView;
            private Event mEvent;
            private  TextView mDateTextView;
            private TextView mPlaceTextView;
            private TextView mContentTextView;
            private ImageView mImageView;

            public EventHolder(View itemView) {
                super(itemView);
                itemView.setOnClickListener(this);
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
            @Override
            public void onClick(View v){
                Intent intent = EventActivity.newIntent(getActivity(),mEvent.getId());
                startActivity(intent);
            }

        }*/


    /*private class EventAdapter extends RecyclerView.Adapter<EventHolder>{
        private List<Event> mEvents;
        public  EventAdapter(List<Event> events){
            mEvents = events;
        }
        @Override
        public EventHolder onCreateViewHolder(ViewGroup parent,int viewType){
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.list_item_event,parent,false);
            return new EventHolder(view);
        }

        @Override
        public void onBindViewHolder(EventHolder holder,int position){
            Event event = mEvents.get(position);
            holder.bindEvent(event);

        }

        @Override
        public  int getItemCount(){
            return mEvents.size();
        }

    }*/
}






