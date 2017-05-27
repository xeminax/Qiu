package com.android.qiu.qiu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.qiu.model.GroupList;
import com.android.qiu.model.GroupListLab;

import java.util.List;

/**
 * Created by xemina on 2017/5/27.
 */

public class MyFollowGroupFragment extends Fragment {
    private RecyclerView mGroupRecyclerView;
    private MyFollowListAdapter mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view  = inflater.inflate(R.layout.fragment_group_kind_list,container,false);
        mGroupRecyclerView = (RecyclerView) view.findViewById(R.id.group_kind_recycler_view);
        mGroupRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));



        updateUI();


        return view;
    }

    private void updateUI(){
        GroupListLab groupListLab = GroupListLab.get(getActivity());
        List<GroupList> groupList = groupListLab.getGroupLists();

        mAdapter = new MyFollowListAdapter(groupList);
        mGroupRecyclerView.setAdapter(mAdapter);
    }

    private class MyFollowListHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView mGroupNameTextView;
        private GroupList mGroupList;
        public MyFollowListHolder(View itemView){
            super(itemView);
            itemView.setOnClickListener(this);
            mGroupNameTextView = (TextView) itemView.findViewById(R.id.list_itemt_group_list_name_text_view);
        }
        @Override
        public void onClick(View v){
            Intent intent =new Intent(getActivity(),GroupActivity.class);
            startActivity(intent);

        }
        public void bindGroupList(GroupList groupList){
            mGroupList = groupList;
            mGroupNameTextView.setText(mGroupList.getGroupName());

        }

    }
    private class MyFollowListAdapter extends RecyclerView.Adapter<MyFollowListHolder>{
        private List<GroupList> mGroupList;
        public MyFollowListAdapter(List<GroupList> groupLists){
            mGroupList = groupLists;

        }
        @Override
        public MyFollowListHolder onCreateViewHolder(ViewGroup parent, int viewType){
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.list_item_group,parent,false);
            return new MyFollowListHolder(view);
        }

        @Override
        public void onBindViewHolder( MyFollowListHolder holder, int position){
            GroupList groupList = mGroupList.get(position);
            holder.bindGroupList(groupList);

        }

        @Override
        public  int getItemCount(){
            return mGroupList.size();
        }

    }

}
