package com.diendan.svdanang;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.diendan.svdanang.api.ApiListener;
import com.diendan.svdanang.api.models.ProfileOutput;
import com.diendan.svdanang.api.models.ResultDonateOutput;
import com.diendan.svdanang.models.DataDonationDetailResult;
import com.diendan.svdanang.models.DataProfile;
import com.diendan.svdanang.tasks.BaseTask;
import com.diendan.svdanang.tasks.DonatePaypalResultTask;
import com.diendan.svdanang.tasks.GetProfileTask;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class RedirectDonationActivity extends AppCompatActivity implements ApiListener<ResultDonateOutput>, View.OnClickListener {
    WebView wvDonation;
    String[] subUrl = null ;
    String[] subDetail = null ;
    String[] subMoreDetail = null ;
    String[] subPaymentId = null ;
    String[] subToken = null ;
    String[] subPayerID = null ;
    Long id,projectIdSub;
    ProgressDialog mProgressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_donation_redirect);
        Intent intent = this.getIntent();
        String url = intent.getStringExtra("url");
        id = intent.getLongExtra("id",0);
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setCancelable(false);
        mProgressDialog.setMessage(getString(R.string.txt_waiting));
        wvDonation = findViewById(R.id.webview_donation_redirect);
        wvDonation.getSettings().setDomStorageEnabled(true);
        wvDonation.getSettings().setAppCacheMaxSize( 5 * 1024 * 1024 ); // 5MB
        wvDonation.getSettings().setAppCachePath( getApplicationContext().getCacheDir().getAbsolutePath() );
        wvDonation.getSettings().setAllowFileAccess( true );
        wvDonation.getSettings().setAppCacheEnabled( true );
        wvDonation.getSettings().setPluginState(WebSettings.PluginState.ON);
        wvDonation.getSettings().setJavaScriptEnabled( true );
        wvDonation.getSettings().setCacheMode( wvDonation.getSettings().LOAD_DEFAULT ); // load online by default
        wvDonation.setWebChromeClient(new WebChromeClient());
        wvDonation.clearCache(true);
        wvDonation.clearHistory();
        wvDonation.setWebViewClient(new WebViewClient(){
            public boolean shouldOverrideUrlLoading(WebView view, String url){
                // do your handling codes here, which url is the requested url
                // probably you need to open that url rather than redirect:
                showLoading(true);
                subUrl = url.split("[/]");

                if(subUrl[2].equals("svdanang.com"))
                {
                    subDetail = subUrl[5].split("[?]");
                    subMoreDetail = subDetail[1].split("[&]");
                    subPaymentId = subMoreDetail[0].split("[=]") ;
                    subToken = subMoreDetail[1].split("[=]") ;
                    subPayerID = subMoreDetail[2].split("[=]") ;
                    projectIdSub= Long.getLong(subDetail[0].toString());
                    showLoading(true);
                    new DonatePaypalResultTask(RedirectDonationActivity.this,id,subPaymentId[1],subToken[1],subPayerID[1],RedirectDonationActivity.this).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
                }
                else {

                    view.loadUrl(url);


                }
                showLoading(false);
                return false;// then it is not handled by default action
            }
        });
        wvDonation.loadUrl(url);

    }


    protected void addListener() {

    }

    @Override
    public void onClick(View view) {


    }


    @Override
    public void onConnectionOpen(BaseTask task) {

    }

    @Override
    public void onConnectionSuccess(BaseTask task, ResultDonateOutput data) {
        if (task instanceof DonatePaypalResultTask) {
            ResultDonateOutput output = (ResultDonateOutput) data;
            if (output.success) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setCancelable(true);
                    builder.setTitle("Ủng hộ thành công");
                    builder.setMessage(output.getMessage());
                    builder.setNegativeButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                            Intent intent = new Intent(RedirectDonationActivity.this, ShowDetailProjectActivity.class);
                            intent.putExtra("id",id);
                            startActivity(intent);
                        }
                    });
                    showLoading(false);
                    builder.show();

            }
            else
            {
                showLoading(false);
                notify("Không thành công",data.getMessage());
            }

        }
    }


    private void showLoading(boolean isShow) {
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
    @Override
    public void onConnectionError(BaseTask task, Exception exception) {

    }
}
