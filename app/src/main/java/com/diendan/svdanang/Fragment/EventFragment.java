package com.diendan.svdanang.Fragment;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import android.support.v7.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.diendan.svdanang.Adapter.EventRecyclerviewAdapter;

import com.diendan.svdanang.Eventitem;

import com.diendan.svdanang.R;
import com.diendan.svdanang.api.ApiListener;
import com.diendan.svdanang.api.models.EventsOutput;
import com.diendan.svdanang.models.ContentEvent;
import com.diendan.svdanang.tasks.BaseTask;
import com.diendan.svdanang.tasks.GetEventsTask;
import com.diendan.svdanang.tasks.GetProjectsTask;


import java.util.ArrayList;

public class EventFragment extends Fragment implements View.OnClickListener, ApiListener<EventsOutput> {
    RecyclerView mEventRecyclerView;
    private EventRecyclerviewAdapter mAdapter;
    int mStart = 0;
    boolean isLoading;
    protected ProgressDialog mProgressDialog;
    ArrayList<ContentEvent> eventitemlist;
    LinearLayoutManager mLayoutManager;
    private int pastVisiblesItems, visibleItemCount, totalItemCount;
    private boolean mIsLoadMore;
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
        mProgressDialog = new ProgressDialog(getActivity());
        mProgressDialog.setCancelable(false);
        mProgressDialog.setMessage(getString(R.string.txt_waiting));
        mEventRecyclerView.setLayoutManager(mLayoutManager);
        eventitemlist = new ArrayList<>();
        mAdapter = new EventRecyclerviewAdapter(getActivity(), eventitemlist);
        mEventRecyclerView.setAdapter(mAdapter);
        loadData();
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
        mEventRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (dy > 0) //check for scroll down
                {
                    visibleItemCount = mLayoutManager.getChildCount();
                    totalItemCount = mLayoutManager.getItemCount();
                    pastVisiblesItems = mLayoutManager.findFirstVisibleItemPosition();

                    if ((visibleItemCount + pastVisiblesItems) >= totalItemCount) {
                        if (eventitemlist.size() > 0 && mIsLoadMore && !isLoading) {
                            isLoading = true;
                            mStart++;
                            loadData();
                        }
                    }
                }
            }
        });}
    private void loadData() {
        showLoading(true);
        new GetEventsTask(getActivity(), mStart,5, this).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

    }



    @Override
    public void onClick(View view) {
        // khai báo nút detail findviewby id ở trên, rồi vô đây làm sự kiện chuyển sang trang detail
        switch (view.getId()) {

        }

    }

    @Override
    public void onConnectionOpen(BaseTask task) {

    }

    @Override
    public void onConnectionSuccess(BaseTask task, EventsOutput data) {
        if(task instanceof GetEventsTask)
        {
            showLoading(false);
            EventsOutput output = data;
            if(output.success)
            {
                mIsLoadMore = !output.getData().getLast();
                eventitemlist.addAll(output.getData().getContent());
                mAdapter.notifyDataSetChanged();
                isLoading = false;
                showLoading(false);
            }

        }
    }

    @Override
    public void onConnectionError(BaseTask task, Exception exception) {
        Log.i("aaaaaaaaaa",exception.getMessage());
    }
    public void showLoading(boolean isShow) {
        try {
            if (isShow) {
                mProgressDialog.show();
            } else {
                if (mProgressDialog.isShowing()) {
                    mProgressDialog.dismiss();
                }
            }
        } catch (IllegalArgumentException ex) {
        }
    }
}
