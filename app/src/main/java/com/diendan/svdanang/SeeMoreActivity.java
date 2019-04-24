package com.diendan.svdanang;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.diendan.svdanang.Adapter.SeemorePageRecyclerviewAdapter;
import com.diendan.svdanang.Adapter.ViewpageRecyclerviewAdapter;

import com.diendan.svdanang.R;
import com.diendan.svdanang.Seemoreitem;
import com.diendan.svdanang.api.ApiListener;
import com.diendan.svdanang.api.models.BlogPostsOutput;
import com.diendan.svdanang.models.ContentBlogPost;
import com.diendan.svdanang.tasks.BaseTask;
import com.diendan.svdanang.tasks.GetBlogPostsByTopicTask;


import java.util.ArrayList;
import java.util.List;

public class SeeMoreActivity extends AppCompatActivity implements View.OnClickListener, ApiListener<BlogPostsOutput> {
    RecyclerView mSeemoreRecyclerView;
    SeemorePageRecyclerviewAdapter mAdapter;
    ArrayList<ContentBlogPost> seemorelist;
    ProgressDialog mProgressDialog;
    LinearLayoutManager mLayoutManager;
    Long idTopic;
    String nameTopic;
    TextView titleActivity;




    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.see_more_fragment);
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setCancelable(false);
        mProgressDialog.setMessage(getString(R.string.txt_waiting));
        Intent intent = this.getIntent();
        idTopic = intent.getLongExtra("idTopic",0);
        nameTopic = intent.getStringExtra("nameTopic");
        titleActivity = findViewById(R.id.tv_title_see_more);
        titleActivity.setText(nameTopic);
        mSeemoreRecyclerView = (RecyclerView) findViewById(R.id.recyclerview_see_more_fragment);
        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mSeemoreRecyclerView.setLayoutManager(mLayoutManager);
        seemorelist = new ArrayList<>();
        mAdapter = new SeemorePageRecyclerviewAdapter(this, seemorelist);
        mSeemoreRecyclerView.setAdapter(mAdapter);
        loadData();
        addListener();

    }

    private void loadData() {
        new GetBlogPostsByTopicTask(this,idTopic, 0,5, this).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    private void addListener() {
            //chỗ ni là m muốn onlick cái nào thì setOnClickListener(this) vào đây
            mAdapter.setOnItemClickListener(new SeemorePageRecyclerviewAdapter.IOnItemClickedListener() {
                @Override
                public void onItemClick(int id) {

                }

                @Override
                public void onItemClickComment(Long id) {
                    Intent i = new Intent(SeeMoreActivity.this, ShowDetailBlogPostActivity.class);
                    i.putExtra("id",id);
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
    public void onConnectionSuccess(BaseTask task, BlogPostsOutput data) {
        if(task instanceof GetBlogPostsByTopicTask)
        {
            BlogPostsOutput output = data;
            if(output.success)
            {
                seemorelist.addAll(output.getData().getContent());
                mAdapter.notifyDataSetChanged();
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
