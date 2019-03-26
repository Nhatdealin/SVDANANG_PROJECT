package com.diendan.svdanang.Fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.diendan.svdanang.Adapter.HomeRecyclerviewAdapter;
import com.diendan.svdanang.Adapter.ViewpageRecyclerviewAdapter;
import com.diendan.svdanang.Homeitem;
import com.diendan.svdanang.R;
import com.diendan.svdanang.SeeMoreActivity;
import com.diendan.svdanang.Viewpageitem;

import java.util.ArrayList;

public class HomeFragment extends Fragment implements View.OnClickListener {
    RecyclerView mHomeRecyclerView, mViewpageRecyclerView;
    private HomeRecyclerviewAdapter mAdapter;
    ViewpageRecyclerviewAdapter mViewPageAdapter;
    ArrayList<Homeitem> homeitemlist;
    ArrayList<Viewpageitem> pageviewitemlist;
    LinearLayoutManager mLayoutManager;
    LinearLayoutManager mLayoutManagerPageView;


    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.home_fragment, container, false);
        mHomeRecyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerview_home_fragment);
        mViewpageRecyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerview_viewpage_home_fragment);
        ViewCompat.setNestedScrollingEnabled(mHomeRecyclerView, false);
        ViewCompat.setNestedScrollingEnabled(mViewpageRecyclerView, false);
        mLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        // set nằm ngang là
        mLayoutManagerPageView = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        mHomeRecyclerView.setLayoutManager(mLayoutManager);
        mViewpageRecyclerView.setLayoutManager(mLayoutManagerPageView);
        homeitemlist = new ArrayList<>();
        homeitemlist.add(new Homeitem(1, "CUỘC THI", R.drawable.image_test_contest, R.drawable.image_test_contest, R.drawable.image_test_contest, R.drawable.image_test_contest, "Cuộc thi ABCXYZ", "Cuộc thi ABCXYZ", "Cuộc thi ABCXYZ", "Cuộc thi ABCXYZ"));
        homeitemlist.add(new Homeitem(2, "SỰ KIỆN", R.drawable.image_test_contest, R.drawable.image_test_contest, R.drawable.image_test_contest, R.drawable.image_test_contest, "Cuộc thi ABCXYZ", "Cuộc thi ABCXYZ", "Cuộc thi ABCXYZ", "Cuộc thi ABCXYZ"));
        mAdapter = new HomeRecyclerviewAdapter(getActivity(), homeitemlist);
        mHomeRecyclerView.setAdapter(mAdapter);
        pageviewitemlist = new ArrayList<>();
        pageviewitemlist.add(new Viewpageitem(R.drawable.cover1));
        pageviewitemlist.add(new Viewpageitem(R.drawable.cover2));
        pageviewitemlist.add(new Viewpageitem(R.drawable.cover3));
        pageviewitemlist.add(new Viewpageitem(R.drawable.cover4));
        mViewPageAdapter = new ViewpageRecyclerviewAdapter(getActivity(), pageviewitemlist);
        mViewpageRecyclerView.setAdapter(mViewPageAdapter);
        SnapHelper helper = new LinearSnapHelper();
        helper.attachToRecyclerView(mViewpageRecyclerView);
        addListener();
        return rootView;
    }

    private void addListener() {
        //chỗ ni là m muốn onlick cái nào thì setOnClickListener(this) vào đây
        mAdapter.setOnItemClickListener(new HomeRecyclerviewAdapter.IOnItemClickedListener() {
            @Override
            public void onItemClick(int id) {

            }

            @Override
            public void onItemClickComment(int id) {
                Intent i = new Intent(getActivity(), SeeMoreActivity.class);
                startActivity(i);
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
