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
import com.diendan.svdanang.api.models.ProfileOutput;
import com.diendan.svdanang.api.objects.ChangePasswordInput;
import com.diendan.svdanang.api.objects.UpdateProfileInput;
import com.diendan.svdanang.models.DataProfile;
import com.diendan.svdanang.tasks.BaseTask;
import com.diendan.svdanang.tasks.ChangePasswordTask;
import com.diendan.svdanang.tasks.UpdateProfileTask;

public class ChangePasswordActivity extends AppCompatActivity implements ApiListener<ChangePasswordOutput>, View.OnClickListener {
    private Button btn_change_submit,btn_change_cancel;
    private ImageView md_nav_back;
    private TextView tvUsername,tvEmail,tvFbLink;
    private  boolean gender;
    private EditText edtOldPass,edtNewPass,edtReNewPass;
    protected ProgressDialog mProgressDialog;
    protected RadioButton rbMale,rbFemale;
    protected RadioGroup rbgGender;
    String username,department,email,firstname,lastname,address,birthdate,city,fblink,phone;
    public static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_change_password);
        Intent intent = this.getIntent();
        email = intent.getStringExtra("email");
        fblink = intent.getStringExtra("fblink");
        username = intent.getStringExtra("username");


        btn_change_submit = findViewById(R.id.btn_change_submit);
        btn_change_cancel = findViewById(R.id.btn_change_cancel);
        md_nav_back = findViewById(R.id.imv_change_pass_back);
        tvEmail = findViewById(R.id.tv_change_pass_email);
        tvFbLink = findViewById(R.id.tv_change_pass_fblink);
        tvUsername = findViewById(R.id.tv_change_pass_username);
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setCancelable(false);
        mProgressDialog.setMessage(getString(R.string.txt_waiting));
        edtOldPass = findViewById(R.id.edt_change_oldpass);
        edtReNewPass = findViewById(R.id.edt_change_renewpass);
        edtNewPass = findViewById(R.id.edt_change_newpass);

        tvUsername.setText(username);
        tvEmail.setText(email);
        tvFbLink.setText(fblink);
        addListener();
    }

    protected void addListener() {
        btn_change_submit.setOnClickListener(this);
        btn_change_cancel.setOnClickListener(this);
        md_nav_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_change_submit :
                if((edtNewPass.getText().toString().trim().equals(edtReNewPass.getText().toString().trim()) == false))
                {
                    notify("Không thành công","Mật khẩu mới không trùng khớp");
                }
                else{
                    showLoading(true);
                    ChangePasswordInput login = new ChangePasswordInput(edtNewPass.getText().toString().trim(), edtOldPass.getText().toString().trim());
                    new ChangePasswordTask(this, login, this).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
                }
                break;
            case R.id.btn_change_cancel:
                exit();
                break;

            case R.id.imv_change_pass_back:
                exit();
                break;  }

    }

    private void exit() {
        Intent i = new Intent(ChangePasswordActivity.this, MainActivity.class);
        showLoading(false);
        startActivity(i);
    }

    @Override
    public void onConnectionOpen(BaseTask task) {

    }


    @Override
    public void onConnectionSuccess(BaseTask task, ChangePasswordOutput data) {
        if(task instanceof ChangePasswordTask){
            ChangePasswordOutput output = data;
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
