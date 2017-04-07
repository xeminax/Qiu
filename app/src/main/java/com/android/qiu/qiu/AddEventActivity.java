package com.android.qiu.qiu;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class AddEventActivity extends SingleFragmentActivity {


    protected Fragment createFragment() {
        return new AddEventFragment();
    }

}
