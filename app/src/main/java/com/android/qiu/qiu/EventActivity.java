package com.android.qiu.qiu;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.UUID;

public class EventActivity extends SingleFragmentActivity {
    private static final String EXTRA_EVENT_ID = "com.xemina.qiu.event_id";

    public static Intent newIntent(Context packageContext, UUID eventId){
        Intent intent = new Intent(packageContext,EventActivity.class);
        intent.putExtra(EXTRA_EVENT_ID,eventId);
        return intent;
    }

    @Override
    protected Fragment createFragment() {
            UUID eventId = (UUID) getIntent().getSerializableExtra(EXTRA_EVENT_ID);
        return EventFragment.newInstance(eventId);

    }
    }

