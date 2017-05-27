package com.android.qiu.qiu;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.qiu.model.Event;
import com.android.qiu.model.EventLab;
import com.squareup.picasso.Picasso;

import java.util.UUID;

public class EventActivity extends AppCompatActivity {
    /*private static final String EXTRA_EVENT_ID = "com.xemina.qiu.event_id";

    public static Intent newIntent(Context packageContext, UUID eventId){
        Intent intent = new Intent(packageContext,EventActivity.class);
        intent.putExtra(EXTRA_EVENT_ID,eventId);
        return intent;
    }

    @Override
    protected Fragment createFragment() {
        UUID eventId = (UUID) getIntent().getSerializableExtra(EXTRA_EVENT_ID);
        return EventFragment.newInstance(eventId);

    }*/
    private Event mEvent = new Event();
    private TextView mTitleField;
    private TextView mDateField;
    //private TextView mTimeField;
    private ImageView mImageView1;
    private TextView mPlaceField;
    private TextView mContentTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_event);
        setTitle("详情");

        mTitleField = (TextView) findViewById(R.id.event_title);
        mDateField = (TextView) findViewById(R.id.event_Date);
        //mTimeField = (TextView)findViewById(R.id.event_time);
        mPlaceField = (TextView)findViewById(R.id.event_place);
        mImageView1 = (ImageView)findViewById(R.id.imageView2);
        mContentTextView = (TextView) findViewById(R.id.event_content);

        /*String id = getIntent().getStringExtra("EventId");
        System.out.println(id);
        UUID eventId = UUID.fromString(getIntent().getStringExtra("EventId"));
        //mEvent = EventLab.get(this).getEvent(eventId);*/
        mEvent = (Event)getIntent().getSerializableExtra("event");

        mTitleField.setText(mEvent.getTitle());
        mDateField.setText(mEvent.getDate().toString());
        mPlaceField.setText(mEvent.getPlace());
        mContentTextView.setText(mEvent.getContent());
        Picasso.with(this).load(mEvent.getAVFile("image") == null ? "www" : mEvent.getAVFile("image").getUrl()).transform(new RoundedTransformation(9, 0)).into(mImageView1);

    }
}

