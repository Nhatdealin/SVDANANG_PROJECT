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
import android.widget.TextView;
import com.diendan.svdanang.api.ApiListener;
import com.diendan.svdanang.api.models.LoginOutput;
import com.diendan.svdanang.api.objects.LoginInput;
import com.diendan.svdanang.tasks.BaseTask;
import com.diendan.svdanang.tasks.LoginTask;
import com.diendan.svdanang.utils.Constants;
import com.diendan.svdanang.utils.SharedPreferenceHelper;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener, ApiListener{
    private Button btn_login;
    private TextView tv_sign_up,tv_forgot_pass;
    private EditText edt_user,edt_password;
    protected ProgressDialog mProgressDialog;
    public static final String TAG = MainActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btn_login = findViewById(R.id.btn_login);
        edt_password = findViewById(R.id.edt_password);
        edt_user = findViewById(R.id.edt_username);
        tv_sign_up = findViewById(R.id.tv_sign_up);
        tv_forgot_pass = findViewById(R.id.tv_forgot_password);
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setCancelable(false);
        mProgressDialog.setMessage(getString(R.string.txt_waiting));
        if(SharedPreferenceHelper.getInstance(LoginActivity.this).get(Constants.EXTRAX_TOKEN_CODE) != null && SharedPreferenceHelper.getInstance(LoginActivity.this).get(Constants.EXTRAX_TOKEN_CODE).length() > 0)
        {
            Intent intent = new Intent(LoginActivity.this,MainActivity.class);
            startActivity(intent);
        }
        addListener();
    }
    protected void addListener() {
        btn_login.setOnClickListener(this);
        tv_sign_up.setOnClickListener(this);
        tv_forgot_pass.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {


        switch (view.getId()) {
            case R.id.btn_login:
                if(edt_user.getText().toString().trim().equals("") || edt_password.getText().toString().trim().equals(""))
                {
                    notify("Không thành công","Chưa nhập tài khoản hoặc mật khẩu");
                }
                else
                {
                    showLoading(true);
                    LoginInput login = new LoginInput(edt_user.getText().toString(), edt_password.getText().toString());
                    new LoginTask(this, login, this).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
                }

                break;
            case R.id.tv_sign_up:
                Intent intent1 = new Intent(LoginActivity.this,SignUpActivity.class);
                startActivity(intent1);
                break;
            case R.id.tv_forgot_password:
                Intent intent2 = new Intent(LoginActivity.this,ForgotPasswordActivity.class);
                startActivity(intent2);
                break;
        }

    }

    @Override
    public void onConnectionOpen(BaseTask task) {

    }

    @Override
    public void onConnectionSuccess(BaseTask task, Object data) {
        if(task instanceof LoginTask){
            showLoading(false);
            LoginOutput output = (LoginOutput) data;
            if(output.success)
            {
                if(output.getData().getTokenType() != ""){
                    SharedPreferenceHelper.getInstance(this).set(Constants.PREF_PERSON_NAME, String.valueOf(output.getData().getUser().getLastName() + " " + output.getData().getUser().getFirstName()));
                    SharedPreferenceHelper.getInstance(this).set(Constants.EXTRAX_EMAIL, output.getData().getUser().getEmail());
                    SharedPreferenceHelper.getInstance(this).set(Constants.EXTRAX_TOKEN_CODE, output.getData().getAccessToken());
                    SharedPreferenceHelper.getInstance(this).set(Constants.PREF_USERNAME, output.getData().getUser().getUserName());
                    SharedPreferenceHelper.getInstance(this).set(Constants.PREF_AVATAR, output.getData().getUser().getAvatar());
                    Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                    startActivity(intent);
                }
            }
            else
            {
                showLoading(false);
                notify("Không thành công",((LoginOutput) data).getMessage());
            }
        }

    }

    @Override
    public void onConnectionError(BaseTask task, Exception exception) {
        Log.e(TAG,exception.getMessage());
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
