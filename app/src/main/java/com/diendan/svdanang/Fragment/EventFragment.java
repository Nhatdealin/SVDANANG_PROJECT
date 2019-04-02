package com.diendan.svdanang.Fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import android.support.v7.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.diendan.svdanang.Adapter.EventRecyclerviewAdapter;

import com.diendan.svdanang.Eventitem;

import com.diendan.svdanang.R;


import java.util.ArrayList;

public class EventFragment extends Fragment implements View.OnClickListener {
    RecyclerView mEventRecyclerView;
    private EventRecyclerviewAdapter mAdapter;

    ArrayList<Eventitem> eventitemlist;
    LinearLayoutManager mLayoutManager;

    public static EventFragment newInstance() {

        EventFragment fragment = new EventFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.event_fragment, container, false);
        mEventRecyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerview_event_fragment);
        mLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        // set nằm ngang là

        mEventRecyclerView.setLayoutManager(mLayoutManager);
        eventitemlist = new ArrayList<>();
        eventitemlist.add(new Eventitem(1, R.drawable.cover1, "Hành Trình SV 2019", "Sự kiện là sân chơi cho các ..................", 100000, "Thành phố Đà Nẵng", "Hành tình"));
        eventitemlist.add(new Eventitem(2, R.drawable.cover2, "Hành Trình SV 2019", "Sự kiện là sân chơi cho các ..................", 50000, "Thành phố Đà Nẵng", "Hành tình"));
        mAdapter = new EventRecyclerviewAdapter(getActivity(), eventitemlist);
        mEventRecyclerView.setAdapter(mAdapter);
        addListener();
        return rootView;
    }

    private void addListener() {
        //chỗ ni là m muốn onlick cái nào thì setOnClickListener(this) vào đây
        mAdapter.setOnItemClickListener(new EventRecyclerviewAdapter.IOnItemClickedListener() {
            @Override
            public void onItemClick(int id) {

            }

            @Override
            public void onItemClickComment(int id) {
            }

            @Override
            public void userSelectedAValue(String value) {

            }
        });
    }


    @Override
    public void onClick(View view) {
        // khai báo nút detail findviewby id ở trên, rồi vô đây làm sự kiện chuyển sang trang detail
        switch (view.getId()) {

        }

    }

}
