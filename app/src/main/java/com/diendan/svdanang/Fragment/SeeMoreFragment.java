package com.diendan.svdanang.Fragment;

import android.app.Fragment;
import android.os.Bundle;
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
import com.diendan.svdanang.Viewpageitem;

import java.util.ArrayList;

public class SeeMoreFragment extends Fragment implements View.OnClickListener {
    RecyclerView mHomeRecyclerView, mViewpageRecyclerView;
    HomeRecyclerviewAdapter mAdapter;
    ViewpageRecyclerviewAdapter mViewPageAdapter;
    ArrayList<Homeitem> homeitemlist;
    ArrayList<Viewpageitem> pageviewitemlist;
    LinearLayoutManager mLayoutManager;
    LinearLayoutManager mLayoutManagerPageView;

    public static SeeMoreFragment newInstance() {
        SeeMoreFragment fragment = new SeeMoreFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.home_fragment, container, false);
        mHomeRecyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerview_home_fragment);
        mViewpageRecyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerview_viewpage_home_fragment);
        mLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        // set nằm ngang là
        mLayoutManagerPageView = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        mHomeRecyclerView.setLayoutManager(mLayoutManager);
        mViewpageRecyclerView.setLayoutManager(mLayoutManagerPageView);
        homeitemlist = new ArrayList<>();
        homeitemlist.add(new Homeitem("CUỘC THI", R.drawable.image_test_contest, R.drawable.image_test_contest, R.drawable.image_test_contest, R.drawable.image_test_contest, "Cuộc thi ABCXYZ", "Cuộc thi ABCXYZ", "Cuộc thi ABCXYZ", "Cuộc thi ABCXYZ"));
        homeitemlist.add(new Homeitem("SỰ KIỆN", R.drawable.image_test_contest, R.drawable.image_test_contest, R.drawable.image_test_contest, R.drawable.image_test_contest, "Cuộc thi ABCXYZ", "Cuộc thi ABCXYZ", "Cuộc thi ABCXYZ", "Cuộc thi ABCXYZ"));
        mAdapter = new HomeRecyclerviewAdapter(getActivity(), homeitemlist);
        mHomeRecyclerView.setAdapter(mAdapter);
        pageviewitemlist = new ArrayList<>();

        mViewPageAdapter = new ViewpageRecyclerviewAdapter(getActivity(), pageviewitemlist);
        mViewpageRecyclerView.setAdapter(mViewPageAdapter);
        SnapHelper helper = new LinearSnapHelper();
        helper.attachToRecyclerView(mViewpageRecyclerView);

        addListener();
        return rootView;
    }

    private void addListener() {
        //chỗ ni là m muốn onlick cái nào thì setOnClickListener(this) vào đây

    }


    @Override
    public void onClick(View view) {
        // khai báo nút detail findviewby id ở trên, rồi vô đây làm sự kiện chuyển sang trang detail
        switch (view.getId()) {

        }

    }
}
