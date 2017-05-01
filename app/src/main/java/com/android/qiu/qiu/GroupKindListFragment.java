package com.android.qiu.qiu;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.qiu.model.GroupList;

import java.util.List;

/**
 * Created by xemina on 2017/3/9.
 */

public class GroupKindListFragment extends Fragment {
    private RecyclerView mGroupKindRecyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view  = inflater.inflate(R.layout.fragment_group_kind_list,container,false);
        mGroupKindRecyclerView = (RecyclerView) view.findViewById(R.id.group_kind_recycler_view);
        mGroupKindRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return view;
    }
    private class GroupListHolder extends RecyclerView.ViewHolder{
        public TextView mGroupNameTextView;
        public GroupListHolder(View itemView){
            super(itemView);
            mGroupNameTextView = (TextView) itemView;
        }
    }
  /*  private class GroupListAdapter extends RecyclerView.Adapter<GroupListHolder>{
        private List<GroupList> mGroupKindList;
        public GroupListAdapter(List<GroupList> groupLists){
            mGroupKindList = groupLists;

        }

    }
*/
}
