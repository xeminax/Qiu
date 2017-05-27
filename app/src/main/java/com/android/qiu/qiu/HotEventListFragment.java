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
 * Created by xemina on 2017/3/29.
 */

public class HotEventListFragment extends Fragment {
    private static final String ARG_SECTION_NUMBER = "sectionNumber";
    private RecyclerView mEventRecyclerView;
    private EventListAdapter mEventAdapter;


    private MaterialRefreshLayout mMaterialRefreshLayout;
    private List<Event> events = new ArrayList<>();

    public static HotEventListFragment newInstance(int sectionNumber) {
        HotEventListFragment fragment = new HotEventListFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_event_hot_list, container, false);
        mEventRecyclerView = (RecyclerView) view.findViewById(R.id.event_hot_recycler_view);
        mMaterialRefreshLayout = (MaterialRefreshLayout) view.findViewById(R.id.refresh);
        mEventRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mEventAdapter = new EventListAdapter(events,getActivity());
        mEventRecyclerView.setAdapter(mEventAdapter);

        //initData();
        //System.out.println(events.size()+" Hot onCreate");
        mMaterialRefreshLayout.setMaterialRefreshListener(new MaterialRefreshListener() {
            @Override
            public void onRefresh(final MaterialRefreshLayout materialRefreshLayout) {
                //get data from could
                initData();
                //System.out.println(events.size()+" Hot onRefresh");
                mMaterialRefreshLayout.finishRefresh();
                //mEventAdapter.notifyDataSetChanged();
                Log.v("Test","onRefresh------------");

            }

            @Override
            public void onRefreshLoadMore(final MaterialRefreshLayout materialRefreshLayout) {

                Log.v("Test","onRefreshLoadMore------------");
                /*EventLab eventLab = EventLab.get(getActivity());
                events = eventLab.getEventsALLFromLean();
                //events.addAll(eventLab.moreEvents(events.size()));
                System.out.println(events.size()+" Hot onRefreshLoadMore");*/
                //mEventAdapter.notifyDataSetChanged();
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
        avQuery.orderByDescending("joiner_num");
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

}