package com.android.qiu.qiu;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Created by xemina on 2017/3/29.
 */

public class AddEventFragment extends Fragment {
    private ImageView mAddEventImage;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_event_add, container, false);
        mAddEventImage = (ImageView)v.findViewById(R.id.event_add_Image);

    return v;
    }
    }
