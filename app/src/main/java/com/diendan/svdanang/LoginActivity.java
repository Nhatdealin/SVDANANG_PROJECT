package com.diendan.svdanang;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.diendan.svdanang.api.ApiListener;
import com.diendan.svdanang.api.models.LoginOutput;
import com.diendan.svdanang.api.objects.LoginInput;
import com.diendan.svdanang.tasks.BaseTask;
import com.diendan.svdanang.tasks.LoginTask;
import com.diendan.svdanang.utils.Constants;
import com.diendan.svdanang.utils.SharedPreferenceHelper;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener, ApiListener<LoginOutput> {
    private Button btn_login;
    private TextView tv_sign_up;
    protected ProgressDialog mProgressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btn_login = findViewById(R.id.btn_login);
        tv_sign_up = findViewById(R.id.tv_sign_up);
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setCancelable(false);
        mProgressDialog.setMessage(getString(R.string.txt_waiting));
        addListener();
    }
    protected void addListener() {
        btn_login.setOnClickListener(this);
        tv_sign_up.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {


        switch (view.getId()) {
            case R.id.btn_login:
                showLoading(true);
                LoginInput login = new LoginInput("ducvan1", "goodboy");
                new LoginTask(this, login, this).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

                break;
            case R.id.tv_sign_up:
                Intent intent1 = new Intent(LoginActivity.this,SignUpActivity.class);
                startActivity(intent1);
                break;
        }

    }

    @Override
    public void onConnectionOpen(BaseTask task) {

    }

    @Override
    public void onConnectionSuccess(BaseTask task, LoginOutput data) {
        if(task instanceof LoginTask){
            showLoading(false);
            LoginOutput output = data;
            if(output.getTokenType() != ""){
                SharedPreferenceHelper.getInstance(this).set(Constants.PREF_PERSON_NAME, String.valueOf(output.getFirstName() + " " + output.getLastName()));
                SharedPreferenceHelper.getInstance(this).set(Constants.EXTRAX_EMAIL, output.getEmail());
                Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                startActivity(intent);
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
