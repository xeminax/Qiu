package com.android.qiu.qiu;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.android.qiu.model.GroupList;
import com.android.qiu.model.GroupListLab;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xemina on 2017/5/26.
 */

public class GroupPickerFragment extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        View v = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_select_group,null);


        ArrayList<String>  mMylist = new ArrayList<String> ();

        mMylist.add("hhaha");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, mMylist);
        ListView listView = (ListView) v.findViewById(R.id.dialog_group_picker);
        listView.setAdapter(adapter);
        return new AlertDialog.Builder(getActivity()).setTitle(R.string.group_picker_title)
                .setView(v)
                .setPositiveButton(android.R.string.ok,null)
                .create();

    }
}
