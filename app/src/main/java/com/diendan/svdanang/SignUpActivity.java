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
import com.diendan.svdanang.api.models.SignupOutput;
import com.diendan.svdanang.api.objects.SignupInput;
import com.diendan.svdanang.tasks.BaseTask;
import com.diendan.svdanang.tasks.SignupTask;

public class SignUpActivity extends AppCompatActivity implements ApiListener<SignupOutput>,View.OnClickListener {
    private Button btn_signup;
    private EditText edt_email,edt_firstname,edt_lastname,edt_username,edt_password;
    protected ProgressDialog mProgressDialog;
    public static final String TAG = MainActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        btn_signup = findViewById(R.id.btn_signup);

        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setCancelable(false);
        mProgressDialog.setMessage(getString(R.string.txt_waiting));
        edt_email = findViewById(R.id.edt_signup_email);
        edt_firstname = findViewById(R.id.edt_signup_firstname);
        edt_lastname = findViewById(R.id.edt_signup_lastname);
        edt_username = findViewById(R.id.edt_signup_username);
        edt_password = findViewById(R.id.edt_signup_password);
        addListener();
    }
    protected void addListener() {
        btn_signup.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        showLoading(true);
        SignupInput signup = new SignupInput(edt_email.getText().toString(),edt_firstname.getText().toString(),edt_lastname.getText().toString(),edt_password.getText().toString(),edt_username.getText().toString());
//        SignupInput signup = new SignupInput("dominhnhat2311971@gmail.com","DoDÔDODOD","Nhat","123123","nhatnhat231197");
        new SignupTask(this, signup, this).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    @Override
    public void onConnectionOpen(BaseTask task) {

    }

    @Override
    public void onConnectionSuccess(BaseTask task, SignupOutput data) {
        if(task instanceof SignupTask){
            showLoading(false);
            SignupOutput output = data;
            if(output.success){
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setCancelable(true);
                builder.setTitle("Đăng kí thành công");
                builder.setMessage(output.getMessage());
                builder.setNegativeButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        Intent intent = new Intent(SignUpActivity.this,LoginActivity.class);
                        startActivity(intent);
                    }
                });
                builder.show();

            }
            else {
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


    }
}
