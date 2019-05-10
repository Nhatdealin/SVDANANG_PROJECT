package com.diendan.svdanang;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.diendan.svdanang.api.ApiListener;
import com.diendan.svdanang.api.models.ChangePasswordOutput;
import com.diendan.svdanang.api.models.ForgotPasswordOutput;
import com.diendan.svdanang.api.objects.ChangePasswordInput;
import com.diendan.svdanang.api.objects.ForgotPasswordInput;
import com.diendan.svdanang.tasks.BaseTask;
import com.diendan.svdanang.tasks.ChangePasswordTask;
import com.diendan.svdanang.tasks.ForgotPasswordTask;

public class ForgotPasswordActivity extends AppCompatActivity implements ApiListener<ForgotPasswordOutput>, View.OnClickListener {
    private Button btn_submit,btn_cancel;
    private ImageView md_nav_back;
    private EditText edtEmail;
    protected ProgressDialog mProgressDialog;
    public static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_forgot_password);

        btn_submit = findViewById(R.id.btn_submit);
        btn_cancel = findViewById(R.id.btn_cancel);
        md_nav_back = findViewById(R.id.imv_forgot_pass_back);
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setCancelable(false);
        mProgressDialog.setMessage(getString(R.string.txt_waiting));
        edtEmail = findViewById(R.id.edt_forgot_email);
        addListener();
    }

    protected void addListener() {
        btn_submit.setOnClickListener(this);
        btn_cancel.setOnClickListener(this);
        md_nav_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_submit :
                if((edtEmail.getText().toString().trim().equals("")))
                {
                    notify("Không thành công","Vui lòng nhập Email");
                }
                else{
                    showLoading(true);
                    ForgotPasswordInput forgotPasswordInput = new ForgotPasswordInput(edtEmail.getText().toString().trim());
                    new ForgotPasswordTask(this, forgotPasswordInput, this).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
                }
                break;
            case R.id.btn_cancel:
                exit();
                break;

            case R.id.imv_forgot_pass_back:
                exit();
                break;  }

    }

    private void exit() {
        Intent i = new Intent(ForgotPasswordActivity.this, LoginActivity.class);
        showLoading(false);
        startActivity(i);
    }

    @Override
    public void onConnectionOpen(BaseTask task) {

    }


    @Override
    public void onConnectionSuccess(BaseTask task, ForgotPasswordOutput data) {
        if(task instanceof ForgotPasswordTask){
            ForgotPasswordOutput output = data;
            if(output.success){
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setCancelable(true);
                builder.setTitle("Thành công");
                builder.setMessage(data.getMessage());
                builder.setNegativeButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        exit();
                        dialog.cancel();
                    }
                });
                builder.show();
            }
            else
            {
                showLoading(false);
                notify("Không thành công",data.getMessage());
            }

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

    @Override
    public void onConnectionError(BaseTask task, Exception exception) {

        Log.e(TAG, exception.getMessage());
    }
}
