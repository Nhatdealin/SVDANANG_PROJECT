package com.diendan.svdanang.Fragment;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.diendan.svdanang.Adapter.EventRecyclerviewAdapter;
import com.diendan.svdanang.Adapter.ProjectRecyclerviewAdapter;
import com.diendan.svdanang.Eventitem;
import com.diendan.svdanang.Projectitem;
import com.diendan.svdanang.R;
import com.diendan.svdanang.api.ApiListener;
import com.diendan.svdanang.api.models.ProjectsOutput;
import com.diendan.svdanang.models.ContentProject;
import com.diendan.svdanang.tasks.BaseTask;
import com.diendan.svdanang.tasks.GetProjectsTask;

import java.util.ArrayList;

public class ProjectFragment extends Fragment implements View.OnClickListener, ApiListener<ProjectsOutput> {
    RecyclerView mProjectRecyclerView;
    private ProjectRecyclerviewAdapter mAdapter;
    ArrayList<ContentProject> pjtitemlist;
    LinearLayoutManager mLayoutManager;
    protected ProgressDialog mProgressDialog;
    int mStart = 0;
    boolean isLoading;
    private boolean mIsLoadMore;
    private int pastVisiblesItems, visibleItemCount, totalItemCount;

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
        mProgressDialog = new ProgressDialog(getActivity());
        mProgressDialog.setCancelable(false);
        mProgressDialog.setMessage(getString(R.string.txt_waiting));
        mProjectRecyclerView.setLayoutManager(mLayoutManager);
        pjtitemlist = new ArrayList<>();
        mAdapter = new ProjectRecyclerviewAdapter(getActivity(), pjtitemlist);
        mProjectRecyclerView.setAdapter(mAdapter);
        loadData();
        addListener();
        return rootView;
    }

    private void loadData() {
        showLoading(true);
        new GetProjectsTask(getActivity(), mStart,5, this).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

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
        mProjectRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
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
                        if (pjtitemlist.size() > 0 && mIsLoadMore && !isLoading) {
                            isLoading = true;
                            mStart++;
                            loadData();
                        }
                    }
                }
            }
            });}


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
    public void onConnectionSuccess(BaseTask task, ProjectsOutput data) {
        if(task instanceof GetProjectsTask)
        {
            ProjectsOutput output = data;
            if(output.success)
            {
                mIsLoadMore = !output.getData().getLast();
                for (ContentProject item : output.getData().getContent()) {
                    pjtitemlist.add(item);
                }
                mAdapter.notifyDataSetChanged();
                isLoading = false;
                showLoading(false);

            }
        }

    }

    @Override
    public void onConnectionError(BaseTask task, Exception exception) {
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
