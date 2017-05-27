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

import java.security.acl.Group;
import java.util.List;

/**
 * Created by xemina on 2017/3/9.
 */

public class GroupKindListFragment extends Fragment {
    private RecyclerView mGroupKindRecyclerView;
    private GroupListAdapter mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view  = inflater.inflate(R.layout.fragment_group_kind_list,container,false);
        mGroupKindRecyclerView = (RecyclerView) view.findViewById(R.id.group_kind_recycler_view);
        mGroupKindRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();
        return view;
    }

    private void updateUI(){
        GroupListLab groupListLab = GroupListLab.get(getActivity());
        List<GroupList> groupKindList = groupListLab.getGroupKindList();
        mAdapter = new GroupListAdapter(groupKindList);
        mGroupKindRecyclerView.setAdapter(mAdapter);
    }

    private class GroupListHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView mGroupNameTextView;
        private GroupList mGroupKindList;
        public GroupListHolder(View itemView){
            super(itemView);
            itemView.setOnClickListener(this);
            mGroupNameTextView = (TextView) itemView.findViewById(R.id.list_itemt_group_list_name_text_view);
        }
        @Override
        public void onClick(View v){
            Intent intent =new Intent(getActivity(),GroupListActivity.class);
            startActivity(intent);

        }
        public void bindGroupKindList(GroupList groupKindList){
            mGroupKindList = groupKindList;
            mGroupNameTextView.setText(mGroupKindList.getGroupKindName());

        }
    }
   private class GroupListAdapter extends RecyclerView.Adapter<GroupListHolder>{
        private List<GroupList> mGroupKindList;
        public GroupListAdapter(List<GroupList> groupLists){
            mGroupKindList = groupLists;

        }
       @Override
       public GroupListHolder onCreateViewHolder (ViewGroup parent,int viewType){
           LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
           View view = layoutInflater.inflate(R.layout.list_item_group,parent,false);
           return new GroupListHolder(view);
       }

       @Override
       public void onBindViewHolder(GroupListHolder holder,int position){
           GroupList groupKindList = mGroupKindList.get(position);
           holder.bindGroupKindList(groupKindList);

       }

       @Override
       public  int getItemCount(){
           return mGroupKindList.size();
       }

   }

    }



