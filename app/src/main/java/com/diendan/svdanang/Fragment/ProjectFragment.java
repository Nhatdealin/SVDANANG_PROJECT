package com.diendan.svdanang.Fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.diendan.svdanang.Adapter.EventRecyclerviewAdapter;
import com.diendan.svdanang.Adapter.ProjectRecyclerviewAdapter;
import com.diendan.svdanang.Eventitem;
import com.diendan.svdanang.Projectitem;
import com.diendan.svdanang.R;

import java.util.ArrayList;

public class ProjectFragment extends Fragment implements View.OnClickListener {
    RecyclerView mProjectRecyclerView;
    private ProjectRecyclerviewAdapter mAdapter;

    ArrayList<Projectitem> pjtitemlist;
    LinearLayoutManager mLayoutManager;

    public static ProjectFragment newInstance() {

        ProjectFragment fragment = new ProjectFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.project_raisingfund_fragment, container, false);
        mProjectRecyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerview_project_fragment);
        mLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        // set nằm ngang là

        mProjectRecyclerView.setLayoutManager(mLayoutManager);
        pjtitemlist = new ArrayList<>();
        pjtitemlist.add(new Projectitem(1, R.drawable.cover6, "Hành Trình SV 2019", "Sự kiện là sân chơi cho các ..................", 500000,1000000 , "Hành tình"));
        pjtitemlist.add(new Projectitem(2, R.drawable.cover5, "Hành Trình SV 2019", "Sự kiện là sân chơi cho các ..................", 700000, 1000000, "Hành tình"));
        pjtitemlist.add(new Projectitem(3, R.drawable.cover7, "Hành Trình SV 2019", "Sự kiện là sân chơi cho các ..................", 700000, 1000000, "Hành tình"));
        mAdapter = new ProjectRecyclerviewAdapter(getActivity(), pjtitemlist);
        mProjectRecyclerView.setAdapter(mAdapter);
        addListener();
        return rootView;
    }

    private void addListener() {
        //chỗ ni là m muốn onlick cái nào thì setOnClickListener(this) vào đây
        mAdapter.setOnItemClickListener(new ProjectRecyclerviewAdapter.IOnItemClickedListener() {
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
