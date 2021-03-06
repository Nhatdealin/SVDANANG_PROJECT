package com.diendan.svdanang;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.diendan.svdanang.api.ApiListener;
import com.diendan.svdanang.api.models.ProfileOutput;
import com.diendan.svdanang.api.objects.UpdateProfileInput;
import com.diendan.svdanang.models.DataProfile;
import com.diendan.svdanang.tasks.BaseTask;
import com.diendan.svdanang.tasks.GetProfileTask;
import com.squareup.picasso.Picasso;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class GetProfileActivity extends AppCompatActivity implements ApiListener<ProfileOutput>, View.OnClickListener {
    private Button btn_edit_infor;
    private TextView tvIfnorFullname, tvInforEmail, tvInforUsername, tvInforGender, tvInforAddress, tvInforCity, tvInforDepartment, tvInforPhone, tvInforBirthDate, tvInforFbLink;
    protected ProgressDialog mProgressDialog;
    private String firstname,lastname;
    private  boolean gender;
    private ImageView md_nav_back;
    String avatar;
    ImageView imvAvatar;
    public static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = this.getIntent();
        firstname = intent.getStringExtra("firstname");
        lastname = intent.getStringExtra("lastname");
        String address = intent.getStringExtra("address");
        Long birthdate =  intent.getLongExtra("birthdate",0);
        String city = intent.getStringExtra("city");
        String department = intent.getStringExtra("department");
        avatar = intent.getStringExtra("avatar");
        String email = intent.getStringExtra("email");
        String fblink = intent.getStringExtra("fblink");
        gender = intent.getBooleanExtra("gender", true);
        String phone = intent.getStringExtra("phone");
        String username = intent.getStringExtra("username");
        setContentView(R.layout.activity_get_profile);


        md_nav_back = findViewById(R.id.btn_back);
        btn_edit_infor = findViewById(R.id.btn_edit_profile);
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setCancelable(false);
        mProgressDialog.setMessage(getString(R.string.txt_waiting));
        tvIfnorFullname = findViewById(R.id.tv_infor_fullname);
        tvInforAddress = findViewById(R.id.tv_infor_address);
        tvInforBirthDate = findViewById(R.id.tv_infor_birthdate);
        tvInforCity = findViewById(R.id.tv_infor_city);
        tvInforDepartment = findViewById(R.id.tv_infor_department);
        tvInforEmail = findViewById(R.id.tv_infor_email);
        tvInforFbLink = findViewById(R.id.tv_infor_fblink);
        tvInforGender = findViewById(R.id.tv_infor_gender);
        tvInforPhone = findViewById(R.id.tv_infor_phonenumber);
        tvInforUsername = findViewById(R.id.tv_infor_username);
        imvAvatar = findViewById(R.id.imv_profile_avatar);

        tvIfnorFullname.setText(firstname+" "+lastname);
        tvInforAddress.setText(address);
        tvInforBirthDate.setText(new SimpleDateFormat("dd/MM/yyyy").format(new Date(birthdate)));
        tvInforCity.setText(city);
        tvInforDepartment.setText(department);
        tvInforEmail.setText(email);
        tvInforFbLink.setText(fblink);
        tvInforGender.setText(gender ? "Nam" : "Nữ");
        tvInforPhone.setText(phone);
        tvInforUsername.setText(username);
        Picasso.with(this).load(avatar).noPlaceholder().fit().centerCrop().into(imvAvatar);
        addListener();
    }

    protected void addListener() {
        btn_edit_infor.setOnClickListener(this);
        md_nav_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_edit_profile:
                Intent i = new Intent(GetProfileActivity.this, UpdateProfileActivity.class);
                i.putExtra("firstname",firstname);
                i.putExtra("lastname",lastname);
                i.putExtra("address",tvInforAddress.getText());
                i.putExtra("birthdate",tvInforBirthDate.getText());
                i.putExtra("avatar",avatar);
                i.putExtra("city",tvInforCity.getText());
                i.putExtra("email",tvInforEmail.getText());
                i.putExtra("fblink",tvInforFbLink.getText());
                i.putExtra("gender",gender);
                i.putExtra("phone",tvInforPhone.getText());
                i.putExtra("username",tvInforUsername.getText());
                startActivity(i);
                break;
            case  R.id.btn_back:
                exit();
                break;

        }


    }

    @Override
    public void onConnectionOpen(BaseTask task) {

    }


    @Override
    public void onConnectionSuccess(BaseTask task, ProfileOutput data) {
//        if(task instanceof GetProfileTask){
//            showLoading(true);
//            ProfileOutput output = data;
//            DataProfile getData = data.getData();
//            if(output.success){
//                tvIfnorFullname.setText(getData.getFirstName() +""+getData.getLastName());
//                tvInforAddress.setText(getData.getAddress());
//                tvInforBirthDate.setText(getData.getBirthDate());
//                tvInforCity.setText(getData.getCity());
//                tvInforDepartment.setText(getData.getDepartment());
//                tvInforEmail.setText(getData.getEmail());
//                tvInforFbLink.setText(getData.getFacebookLink());
//                tvInforGender.setText(getData.getGender()?"Nam":"Nữ");
//                tvInforPhone.setText(getData.getPhoneNumber());
//                tvInforUsername.setText(getData.getUserName());
//            }

//    }

}

    private void exit() {
        Intent i = new Intent(GetProfileActivity.this, MainActivity.class);
        showLoading(false);
        startActivity(i);
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
