package com.android.qiu.qiu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.qiu.model.Event;
import com.android.qiu.model.EventLab;

import java.util.List;

/**
 * Created by xemina on 2017/3/29.
 */

public class NearEventListFragment extends Fragment {
    private static final String ARG_SECTION_NUMBER = "sectionNumber";
    private RecyclerView mEventRecyclerView;
    private NearEventListFragment.EventAdapter mEventAdapter;

    public static NearEventListFragment newInstance(int sectionNumber) {
        NearEventListFragment fragment = new NearEventListFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_event_near_list, container, false);
        mEventRecyclerView = (RecyclerView) view.findViewById(R.id.event_near_recycler_view);
        mEventRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        EventLab eventLab = EventLab.get(getActivity());
        List<Event> events = eventLab.getEvents();
        mEventAdapter = new NearEventListFragment.EventAdapter(events);
        mEventRecyclerView.setAdapter(mEventAdapter);
        return view;
    }



    private class EventHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView mTitleTextView;
        private Event mEvent;
        private TextView mDateTextView;
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

        public void bindEvent(Event event) {
            mEvent = event;
            mTitleTextView.setText(mEvent.getTitle());
            mPlaceTextView.setText(mEvent.getPlace());
            mDateTextView.setText(mEvent.getDate().toString());
            mContentTextView.setText(mEvent.getContent());
        }

        @Override
        public void onClick(View v) {
            Intent intent = EventActivity.newIntent(getActivity(), mEvent.getId());
            startActivity(intent);
        }

    }


    private class EventAdapter extends RecyclerView.Adapter<NearEventListFragment.EventHolder> {
        private List<Event> mEvents;

        public EventAdapter(List<Event> events) {
            mEvents = events;
        }

        @Override
        public NearEventListFragment.EventHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.list_item_event, parent, false);
            return new NearEventListFragment.EventHolder(view);
        }

        @Override
        public void onBindViewHolder(NearEventListFragment.EventHolder holder, int position) {
            Event event = mEvents.get(position);
            holder.bindEvent(event);
        }
        @Override
        public  int getItemCount(){
            return mEvents.size();
        }

    }
}
