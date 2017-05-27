package com.android.qiu.qiu;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.LayoutInflaterCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.qiu.model.Event;
import com.android.qiu.model.EventLab;

import java.util.UUID;

/**
 * Created by xemina on 2017/3/9.
 */

public class EventFragment extends Fragment {
    private static final String ARG_EVENT_ID = "event_id";
    private Event mEvent = new Event();
    private TextView mTitleField;
    private TextView mDateField;
    private TextView mTimeField;
    private ImageView mImageView1;
    private TextView mPlaceField;
    private ImageView mPosterImage;
    private ImageView mGroupImage;
    private TextView mGroupName;



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
        //System.out.println("邵奇星"+eventId);
        mEvent = EventLab.get(getActivity()).getEvent(eventId);
        System.out.println(mEvent.getTitle());


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.fragment_event,container,false);

        mTitleField = (TextView) v.findViewById(R.id.event_title);

        //System.out.println(mEvent.getTitle());

        mTitleField.setText(mEvent.getTitle());
        mDateField = (TextView) v.findViewById(R.id.event_Date);
        mDateField.setText(mEvent.getDate().toString());
        //mTimeField = (TextView)v.findViewById(R.id.event_time);
        mPlaceField = (TextView)v.findViewById(R.id.event_place);
        mImageView1 = (ImageView)v.findViewById(R.id.imageView2);
        mPosterImage = (ImageView)v.findViewById(R.id.event_post_user_image);
        mGroupImage = (ImageView)v.findViewById(R.id.event_group_image);
        mGroupName = (TextView) v.findViewById(R.id.event_detail_group_name);
        mGroupImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),GroupActivity.class);
                startActivity(intent);
            }
        });
        mGroupName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),GroupActivity.class);
                startActivity(intent);
            }
        });

        mPosterImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("TAG"," ==== TEST ===");
                Intent intent = new Intent(getActivity(),UserinfoActivity.class);
                startActivity(intent);

            }

        });



        return v;
    }



}
