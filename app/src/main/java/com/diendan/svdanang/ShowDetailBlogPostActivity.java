package com.diendan.svdanang;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.diendan.svdanang.Adapter.SeemorePageRecyclerviewAdapter;
import com.diendan.svdanang.api.ApiListener;
import com.diendan.svdanang.api.models.BlogPostOutput;
import com.diendan.svdanang.api.models.BlogPostsOutput;
import com.diendan.svdanang.models.ContentBlogPost;
import com.diendan.svdanang.tasks.BaseTask;
import com.diendan.svdanang.tasks.GetBlogPostsByIdTask;
import com.diendan.svdanang.tasks.GetBlogPostsByTopicTask;
import com.squareup.picasso.Picasso;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class ShowDetailBlogPostActivity extends AppCompatActivity implements View.OnClickListener, ApiListener<BlogPostOutput> {
    ProgressDialog mProgressDialog;
    Long id;
    TextView tvTitleBlogPost,tvCreatedBy,tvTopic,tvCreatedDate;
    WebView wvContent;
    ImageView imvImage;
    private ImageView md_nav_back;




    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blogpost_detail);
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setCancelable(false);
        mProgressDialog.setMessage(getString(R.string.txt_waiting));
        Intent intent = this.getIntent();
        id = intent.getLongExtra("id",0);
        tvTitleBlogPost = findViewById(R.id.tv_detail_blogpost_title);
        tvCreatedBy = findViewById(R.id.tv_detail_blogpost_created_by);
        tvTopic = findViewById(R.id.tv_detail_blogpost_topic);
        imvImage = findViewById(R.id.imv_detail_blogpost_image);
        tvCreatedDate = findViewById(R.id.tv_createdat_detail_blogposst);
        wvContent = findViewById(R.id.webview_content_blogpost);
        md_nav_back =  findViewById(R.id.btn_back);
        loadData();
        addListener();

    }

    private void loadData() {
        showLoading(true);
        new GetBlogPostsByIdTask(this,id, this).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    private void addListener() {
        md_nav_back.setOnClickListener(this);


        }



    @Override
    public void onClick(View view) {
        // khai báo nút detail findviewby id ở trên, rồi vô đây làm sự kiện chuyển sang trang detail
        switch (view.getId()) {
            case R.id.btn_back:
                finish();
                break;
        }

    }

    @Override
    public void onConnectionOpen(BaseTask task) {

    }

    @Override
    public void onConnectionSuccess(BaseTask task, BlogPostOutput data) {
        if(task instanceof GetBlogPostsByIdTask)
        {
            BlogPostOutput output = data;
            if(output.success)
            {
                setValue(output);
                showLoading(false);
                }
            }
    }

    private void setValue(BlogPostOutput output) {
        Picasso.with(this).load(output.getData().getThumbnailImage()).fit().centerCrop().noPlaceholder().into(imvImage);
        tvTitleBlogPost.setText(output.getData().getTitle());
        tvCreatedBy.setText(output.getData().getCreatedBy().getFirstName() +" "+output.getData().getCreatedBy().getLastName());
        tvTopic.setText(output.getData().getBlogPostTopic().getName());
        tvCreatedDate.setText(new SimpleDateFormat("dd/MM/yyyy").format(new Date(output.getData().getCreatedAt())));
        WebChromeClient mWebChromeClient = new WebChromeClient(){
            public void onProgressChanged(WebView view, int newProgress) {
            }
        };

        wvContent.setWebChromeClient(new WebChromeClient());
        wvContent.getSettings().setPluginState(WebSettings.PluginState.ON);
        wvContent.getSettings().setPluginState(WebSettings.PluginState.ON_DEMAND);
        wvContent.setWebViewClient(new WebViewClient());
        wvContent.getSettings().setJavaScriptEnabled(true);
        wvContent.getSettings().setAllowFileAccess(true);
        wvContent.getSettings().setDomStorageEnabled(true);
        wvContent.getSettings().setAppCacheMaxSize(1024*1024*8);
        wvContent.getSettings().setSupportZoom(true);
        wvContent.getSettings().setBuiltInZoomControls(true);
        wvContent.getSettings().setAppCachePath("/data/data/"+ getPackageName() +"/cache");
        wvContent.getSettings().setAllowFileAccess(true);
        wvContent.getSettings().setAppCacheEnabled(true);
        wvContent.getSettings().setDefaultFontSize(14);
        wvContent.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);
        wvContent.loadDataWithBaseURL("https://www.youtube.com", output.getData().getContent(),
                "text/html", "UTF-8", null);
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

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
