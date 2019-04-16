package com.diendan.svdanang;

import android.app.ProgressDialog;
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
import com.diendan.svdanang.api.models.ProfileOutput;
import com.diendan.svdanang.api.objects.UpdateProfileInput;
import com.diendan.svdanang.models.DataProfile;
import com.diendan.svdanang.tasks.BaseTask;
import com.diendan.svdanang.tasks.GetProfileTask;
import com.diendan.svdanang.tasks.UpdateProfileTask;

public class UpdateProfileActivity extends AppCompatActivity implements ApiListener<ProfileOutput>, View.OnClickListener {
    private Button btn_update_submit,btn_update_cancel;
    private ImageView md_nav_back;
    private TextView tvUsername,tvUpdateEmail;
    private  boolean gender;
    private EditText edtUpdateFirstname,edtUpdateLastname, edtUpdateGender, edtUpdateAddress, edtUpdateCity, edtUpdatePhone, edtUpdateBirthDate, edtUpdateFbLink;
    protected ProgressDialog mProgressDialog;
    protected RadioButton rbMale,rbFemale;
    protected RadioGroup rbgGender;
    String username,department,email,firstname,lastname,address,birthdate,city,fblink,phone;
    public static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_update_profile);
        Intent intent = this.getIntent();
        firstname = intent.getStringExtra("firstname");
        lastname = intent.getStringExtra("lastname");
        address = intent.getStringExtra("address");
        birthdate = intent.getStringExtra("birthdate");
        city = intent.getStringExtra("city");
        email = intent.getStringExtra("email");
        fblink = intent.getStringExtra("fblink");
        gender = intent.getBooleanExtra("gender", true);
        phone = intent.getStringExtra("phone");
        username = intent.getStringExtra("username");
        department = intent.getStringExtra("department");


        btn_update_submit = findViewById(R.id.btn_update_submit);
        btn_update_cancel = findViewById(R.id.btn_update_cancel);
        md_nav_back = findViewById(R.id.imv_back);
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setCancelable(false);
        mProgressDialog.setMessage(getString(R.string.txt_waiting));
        edtUpdateFirstname = findViewById(R.id.edt_update_firstname);
        edtUpdateLastname = findViewById(R.id.edt_update_lastname);
        edtUpdateAddress = findViewById(R.id.edt_update_address);
        edtUpdateBirthDate = findViewById(R.id.edt_update_birthdate);
        edtUpdateCity = findViewById(R.id.edt_update_city);
        tvUpdateEmail = findViewById(R.id.tv_update_email);
        edtUpdateFbLink = findViewById(R.id.edt_update_fblink);
        rbMale = findViewById(R.id.rb_update_male_gender);
        rbFemale = findViewById(R.id.rb_update_female_gender);
        rbgGender = findViewById(R.id.rbg_gender);
        edtUpdatePhone = findViewById(R.id.edt_update_phonenumber);
        tvUsername = findViewById(R.id.tv_infor_username);

        edtUpdateFirstname.setText(firstname);
        edtUpdateLastname.setText(lastname);
        edtUpdateAddress.setText(address);
        edtUpdateBirthDate.setText(birthdate);
        edtUpdateCity.setText(city);
        tvUpdateEmail.setText(email);
        edtUpdateFbLink.setText(fblink);
        if (gender) {
            rbMale.setChecked(true);
        } else {
            rbFemale.setChecked(true);
        }
        edtUpdatePhone.setText(phone);
        tvUsername.setText(username);

        addListener();
    }

    protected void addListener() {
        btn_update_submit.setOnClickListener(this);
        btn_update_cancel.setOnClickListener(this);
        md_nav_back.setOnClickListener(this);
        rbgGender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int checkedRadioId = group.getCheckedRadioButtonId();
                if(checkedRadioId == R.id.rb_update_female_gender) gender = false;
                else  gender = true;
            }

        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_update_submit :
                showLoading(true);
                UpdateProfileInput update = new UpdateProfileInput(edtUpdateAddress.getText().toString()," ",new Long(Integer.parseInt(edtUpdateBirthDate.getText().toString())),edtUpdateCity.getText().toString(),edtUpdateFbLink.getText().toString(),edtUpdateFirstname.getText().toString(),gender,edtUpdateLastname.getText().toString(),edtUpdatePhone.getText().toString());
//        UpdateProfileInput update = new UpdateProfileInput("08 Hà văn tính"," ", new Long(111),"Đà Nẵng","bkbknbkn.com","Trần Châu",true,"Lệ Trinh","0126680577");
                new UpdateProfileTask(this,update,this).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
                break;
            case R.id.btn_update_cancel:
                exit();
                break;

            case R.id.imv_back:
                exit();
                break;  }

    }

    private void exit() {
        Intent i = new Intent(UpdateProfileActivity.this, GetProfileActivity.class);
        i.putExtra("firstname",firstname);
        i.putExtra("lastname",lastname);
        i.putExtra("address",address);
        i.putExtra("birthdate",birthdate);
        i.putExtra("city",city);
        i.putExtra("email",email);
        i.putExtra("fblink",fblink);
        i.putExtra("gender",gender);
        i.putExtra("phone",phone);
        i.putExtra("username",username);
        i.putExtra("department",department);
        showLoading(false);
        startActivity(i);
    }

    @Override
    public void onConnectionOpen(BaseTask task) {

    }


    @Override
    public void onConnectionSuccess(BaseTask task, ProfileOutput data) {
        if(task instanceof UpdateProfileTask){
            ProfileOutput output = data;
            DataProfile getData = data.getData();
            if(output.success){
                Intent i = new Intent(UpdateProfileActivity.this, GetProfileActivity.class);
                i.putExtra("firstname",getData.getFirstName());
                i.putExtra("lastname",getData.getLastName());
                i.putExtra("address",getData.getAddress());
                i.putExtra("birthdate",getData.getBirthDate().toString());
                i.putExtra("city",getData.getCity());
                i.putExtra("email",email);
                i.putExtra("fblink",getData.getFacebookLink());
                i.putExtra("gender",getData.getGender());
                i.putExtra("phone",getData.getPhoneNumber());
                i.putExtra("username",username);
                i.putExtra("department",department);
                showLoading(false);
                startActivity(i);
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

    @Override
    public void onConnectionError(BaseTask task, Exception exception) {

        Log.e(TAG, exception.getMessage());
    }
}
