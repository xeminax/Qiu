package com.android.qiu.qiu;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.LayoutInflaterCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.android.qiu.model.Event;
import com.android.qiu.model.EventLab;

import java.util.UUID;

/**
 * Created by xemina on 2017/3/9.
 */

public class EventFragment extends Fragment {
    private static final String ARG_EVENT_ID = "event_id";
    private Event mEvent;
    private TextView mTitleField;
    private TextView mDateField;
    private TextView mTimeField;


    public static EventFragment newInstance(UUID eventId){
        Bundle args = new Bundle();
        args.putSerializable(ARG_EVENT_ID, eventId);

        EventFragment fragment = new EventFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        UUID eventId = (UUID) getArguments().getSerializable(ARG_EVENT_ID);
        mEvent = EventLab.get(getActivity()).getEvent(eventId);



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.fragment_event,container,false);

    mTitleField = (TextView) v.findViewById(R.id.event_title);

        mTitleField.setText(mEvent.getTitle());

        mDateField = (TextView) v.findViewById(R.id.event_Date);
        mDateField.setText(mEvent.getDate().toString());

        mTimeField = (TextView)v.findViewById(R.id.event_time);

        return v;
    }


}
