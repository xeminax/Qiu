package com.android.qiu.qiu;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by xemina on 2017/3/30.
 */

public class SectionsPagerAdapter extends FragmentPagerAdapter {

    public SectionsPagerAdapter(FragmentManager fm) {
        super(fm);


    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        switch (position) {
            case 0:
                return MyEventListFragment.newInstance(position);
            case 1:
                return HotEventListFragment.newInstance(position);
            case 2:
                return NearEventListFragment.newInstance(position);
        }
        return MyEventListFragment.newInstance(position);

    }

    @Override
    public int getCount() {
        // Show 3 total pages.
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "我关注的";
            case 1:
                return "热门活动";
            case 2:
                return "附近活动";
        }
        return null;
    }
}
