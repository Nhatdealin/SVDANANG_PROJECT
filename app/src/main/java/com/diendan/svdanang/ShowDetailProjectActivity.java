package com.diendan.svdanang;


import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.diendan.svdanang.api.ApiListener;
import com.diendan.svdanang.api.models.BlogPostOutput;
import com.diendan.svdanang.api.models.PaypalDonationOutput;
import com.diendan.svdanang.api.models.ProjectOutput;
import com.diendan.svdanang.api.objects.PaypalDonationInput;
import com.diendan.svdanang.models.ContentBlogPost;
import com.diendan.svdanang.tasks.BaseTask;
import com.diendan.svdanang.tasks.DonatePaypalTask;
import com.diendan.svdanang.tasks.GetBlogPostsByIdTask;
import com.diendan.svdanang.tasks.GetProjectByIdTask;
import com.diendan.svdanang.utils.Constants;
import com.squareup.picasso.Picasso;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class ShowDetailProjectActivity extends AppCompatActivity implements View.OnClickListener, ApiListener {
    ProgressDialog mProgressDialog;
    Long id;
    boolean isDonating;
    private ImageView md_nav_back;
    TextView tvTitleProject,tvCreatedBy,tvTopic,tvCreatedDate,tvStartTime,tvEndTime,tvGoal,tvRaised,tvCurrency1,tvCurrency2;
    Button btnDonate,btnSubmit,btnCancel;
    WebView wvContent;
    LinearLayout mProgressBar;
    ImageView imvImage;
    View formDonation;
    EditText edtAmount,edtNote;
    NestedScrollView projectScrollView;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_detail);
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setCancelable(false);
        mProgressDialog.setMessage(getString(R.string.txt_waiting));
        Intent intent = this.getIntent();
        id = intent.getLongExtra("id",0);
        isDonating = intent.getBooleanExtra("isDonating",false);
        tvTitleProject = findViewById(R.id.tv_detail_project_title);
        tvCreatedBy = findViewById(R.id.tv_detail_project_created_by);
        tvTopic = findViewById(R.id.tv_detail_project_topic);
        tvCreatedDate = findViewById(R.id.tv_createdat_detail_project);
        tvStartTime = findViewById(R.id.tv_starttime_detail_project);
        tvEndTime = findViewById(R.id.tv_endtime_detail_project);
        tvRaised = findViewById(R.id.tv_raised_detail_project);
        tvGoal = findViewById(R.id.tv_goal_detail_project);
        tvCurrency1 = findViewById(R.id.tv_currency1_detail_project);
        tvCurrency2 = findViewById(R.id.tv_currency2_detail_project);
        btnDonate = findViewById(R.id.btn_donate_detail_project);
        imvImage = findViewById(R.id.imv_detail_project_image);
        mProgressBar = findViewById(R.id.progress_bar_cr);
        wvContent = findViewById(R.id.webview_content_project);
        edtAmount = findViewById(R.id.edt_donation_amount);
        edtNote = findViewById(R.id.edt_donation_note);
        btnSubmit = findViewById(R.id.btn_donation_submit);
        btnCancel = findViewById(R.id.btn_donation_cancel);
        formDonation = findViewById(R.id.form_donation);
        projectScrollView = findViewById(R.id.project_scrollview);
        md_nav_back = findViewById(R.id.imv_back);
        if(isDonating)
        {
            formDonation.setVisibility(View.VISIBLE);
            projectScrollView.post(new Runnable() {
                public void run() {
                    projectScrollView.fullScroll(View.FOCUS_DOWN);
                }
            });
        }
        else formDonation.setVisibility(View.GONE);
        loadData();
        addListener();

    }

    private void loadData() {
        showLoading(true);
        new GetProjectByIdTask(this,id, this).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    private void addListener() {
            //chỗ ni là m muốn onlick cái nào thì setOnClickListener(this) vào đây
        btnDonate.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
        btnSubmit.setOnClickListener(this);
        md_nav_back.setOnClickListener(this);


        }



    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_donate_detail_project:
                formDonation.setVisibility(View.VISIBLE);
                projectScrollView.post(new Runnable() {
                    public void run() {
                        projectScrollView.fullScroll(View.FOCUS_DOWN);
                    }
                });
                break;
            case R.id.btn_donation_submit:
                SubmitDataDonation();
                break;
            case R.id.btn_donation_cancel:
                formDonation.setVisibility(View.GONE);
                break;
            case R.id.imv_back:
                finish();
                break;
        }

    }

    private void SubmitDataDonation() {
        if(!edtAmount.getText().toString().equals("") && Double.parseDouble(edtAmount.getText().toString()) > 0)
        {
            PaypalDonationInput donation = new PaypalDonationInput(edtAmount.getText().toString(),new Long(1),new Long(0),edtNote.getText().toString(),id);
            showLoading(true);
            new DonatePaypalTask(this,donation, this).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        }
        else
        {
            notify("Không thành công", "Nhập lại số tiền ủng hộ");
        }

    }

    @Override
    public void onConnectionOpen(BaseTask task) {

    }

    @Override
    public void onConnectionSuccess(BaseTask task, Object data)  {
        if(task instanceof GetProjectByIdTask)
        {
            ProjectOutput output =  (ProjectOutput)data;
            if(output.success)
            {
                setValue(output);
                showLoading(false);
                }
            }

        if(task instanceof DonatePaypalTask)
        {
            PaypalDonationOutput output =  (PaypalDonationOutput)data;
            if(output.getStatus().equals("success"))
            {
                Intent browserIntent = new Intent(ShowDetailProjectActivity.this, RedirectDonationActivity.class);
                browserIntent.putExtra("url",output.getRedirectUrl().toString());
                browserIntent.putExtra("id",id);
                showLoading(false);
                startActivity(browserIntent);

            }
        }
    }

    private void setValue(ProjectOutput output) {
        tvTitleProject.setText(output.getData().getName());
        Picasso.with(this).load(output.getData().getImage()).noPlaceholder().fit().centerCrop().into(imvImage);
        tvCreatedBy.setText(output.getData().getCreatedBy().getLastName() +" "+output.getData().getCreatedBy().getFirstName());
        tvTopic.setText(output.getData().getProjectTopic().getName());
        tvCreatedDate.setText(new SimpleDateFormat("dd/MM/yyyy").format(new Date(output.getData().getCreatedAt())));
        tvStartTime.setText(new SimpleDateFormat("dd/MM/yyyy").format(new Date(output.getData().getStartTime())));
        tvEndTime.setText(new SimpleDateFormat("dd/MM/yyyy").format(new Date(output.getData().getEndTime())));
        tvCurrency2.setText(output.getData().getCurrency().getName());
        tvCurrency1.setText(output.getData().getCurrency().getName());
        BigDecimal raised;
        if (output.getData().getRaised() != null) raised = output.getData().getRaised();
        else raised = new BigDecimal(0);
        tvGoal.setText(String.valueOf(output.getData().getGoal()));
        tvRaised.setText(String.valueOf(raised));

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
        wvContent.loadDataWithBaseURL("https://www.youtube.com", output.getData().getDescription(),
                "text/html", "UTF-8", null);
        float factor = this.getResources().getDisplayMetrics().density;
        ViewGroup.LayoutParams progresBarLayoutParams = mProgressBar.getLayoutParams();
        float divide = 1;
        if (raised.compareTo(output.getData().getGoal()) == 1) divide = 1;
        else divide = (raised.divide(output.getData().getGoal(), 3, RoundingMode.CEILING).floatValue());
        progresBarLayoutParams.width = (int) (Constants.WITDH * divide * factor);
        mProgressBar.setLayoutParams(progresBarLayoutParams);
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
    private void notify(String result,String mess)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(result);
        builder.setMessage(mess);
        builder.setNegativeButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();
    }
}
