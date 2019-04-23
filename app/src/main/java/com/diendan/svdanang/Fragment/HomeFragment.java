package com.diendan.svdanang.Fragment;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.diendan.svdanang.Adapter.HomeRecyclerviewAdapter;
import com.diendan.svdanang.Adapter.ViewpageRecyclerviewAdapter;
import com.diendan.svdanang.DataHomeitem;
import com.diendan.svdanang.Homeitem;
import com.diendan.svdanang.R;
import com.diendan.svdanang.SeeMoreActivity;
import com.diendan.svdanang.Viewpageitem;
import com.diendan.svdanang.api.ApiListener;
import com.diendan.svdanang.api.models.BaseOutput;
import com.diendan.svdanang.api.models.BlogPostTopicsOutput;
import com.diendan.svdanang.api.models.BlogPostsOutput;
import com.diendan.svdanang.models.BlogPostTopic;
import com.diendan.svdanang.models.ContentBlogPost;
import com.diendan.svdanang.tasks.BaseTask;
import com.diendan.svdanang.tasks.GetBlogPostsByTopicTask;
import com.diendan.svdanang.tasks.GetBlogPostsTopicsTask;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment implements View.OnClickListener, ApiListener {
    RecyclerView mHomeRecyclerView, mViewpageRecyclerView;
    private HomeRecyclerviewAdapter mAdapter;
    ViewpageRecyclerviewAdapter mViewPageAdapter;
    ArrayList<Homeitem> homeitems;
    ArrayList<BlogPostTopic> topicList;
    ArrayList<Viewpageitem> pageviewitemlist;
    LinearLayoutManager mLayoutManager;
    LinearLayoutManager mLayoutManagerPageView;
    ProgressDialog mProgressDialog;


    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.home_fragment, container, false);
        topicList = new ArrayList<>();
        homeitems = new ArrayList<>();
        mProgressDialog = new ProgressDialog(getActivity());
        mProgressDialog.setCancelable(false);
        mProgressDialog.setMessage(getString(R.string.txt_waiting));
        mHomeRecyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerview_home_fragment);
        mViewpageRecyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerview_viewpage_home_fragment);
        ViewCompat.setNestedScrollingEnabled(mHomeRecyclerView, false);
        ViewCompat.setNestedScrollingEnabled(mViewpageRecyclerView, false);
        mLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        // set nằm ngang là
        mLayoutManagerPageView = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        mHomeRecyclerView.setLayoutManager(mLayoutManager);
        mViewpageRecyclerView.setLayoutManager(mLayoutManagerPageView);
        mAdapter = new HomeRecyclerviewAdapter(getActivity(), homeitems);
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
        loadDataTopic();
        addListener();
        return rootView;
    }

    private void loadDataTopic() {
        showLoading(true);
        new GetBlogPostsTopicsTask(this.getActivity(), 0,1, this ).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    private  void addAdapter()
    {}
    private void addListener() {
        //chỗ ni là m muốn onlick cái nào thì setOnClickListener(this) vào đây
        mAdapter.setOnItemClickListener(new HomeRecyclerviewAdapter.IOnItemClickedListener() {
            @Override
            public void onItemClick(int id) {

            }

            @Override
            public void onItemClickComment(BlogPostTopic topic) {
                Intent i = new Intent(getActivity(), SeeMoreActivity.class);
                i.putExtra("idTopic",topic.getId());
                i.putExtra("nameTopic",topic.getName());
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

    @Override
    public void onConnectionOpen(BaseTask task) {

    }

    @Override
    public void onConnectionSuccess(BaseTask task, Object data) {
        if(task instanceof GetBlogPostsTopicsTask)
        {
            BlogPostTopicsOutput output = (BlogPostTopicsOutput)data;
            if(output.success)
            {
                for (BlogPostTopic item : output.getData().getContent()) {
                    new GetBlogPostsByTopicTask(this.getActivity(),item.getId(), 0,4, this).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
                    topicList.add(item);
                }
            }
        }
        if(task instanceof GetBlogPostsByTopicTask)
        {
            BlogPostsOutput output = (BlogPostsOutput)data;
            if(output.success)
            {
                List<ContentBlogPost> listdata = output.getData().getContent();
                    if(output.getData().getContent().size() >= 4 )
                    {
                        DataHomeitem d1 = new DataHomeitem(listdata.get(0).getId(),listdata.get(0).getTitle(),listdata.get(0).getThumbnailImage());
                        DataHomeitem d2 = new DataHomeitem(listdata.get(1).getId(),listdata.get(1).getTitle(),listdata.get(1).getThumbnailImage());
                        DataHomeitem d3 = new DataHomeitem(listdata.get(2).getId(),listdata.get(2).getTitle(),listdata.get(2).getThumbnailImage());
                        DataHomeitem d4 = new DataHomeitem(listdata.get(3).getId(),listdata.get(3).getTitle(),listdata.get(3).getThumbnailImage());
                        homeitems.add(new Homeitem(listdata.get(0).getBlogPostTopic(),d1,d2,d3,d4,listdata.get(0).getBlogPostTopic().getName()));
                        mAdapter.notifyDataSetChanged();
                        showLoading(false);
                    }
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
