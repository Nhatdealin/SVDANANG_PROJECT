package com.diendan.svdanang;


import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
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
import android.widget.Toast;

import com.baoyachi.stepview.HorizontalStepView;
import com.baoyachi.stepview.VerticalStepView;
import com.baoyachi.stepview.bean.StepBean;
import com.diendan.svdanang.api.ApiListener;
import com.diendan.svdanang.api.models.EventOutput;
import com.diendan.svdanang.api.models.ProfileOutput;
import com.diendan.svdanang.api.models.SignupOutput;
import com.diendan.svdanang.api.objects.VolunteerInput;
import com.diendan.svdanang.models.DataProfile;
import com.diendan.svdanang.models.EventSchedule;
import com.diendan.svdanang.tasks.BaseTask;
import com.diendan.svdanang.tasks.GetEventByIdTask;
import com.diendan.svdanang.tasks.GetProfileTask;
import com.diendan.svdanang.tasks.SignupVolunteerTask;
import com.squareup.picasso.Picasso;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class ShowDetailEventActivity extends AppCompatActivity implements View.OnClickListener, ApiListener {
    ProgressDialog mProgressDialog;
    Long id;
    boolean isRegister;
    TextView tvTitleEventt, tvFee,tvCreatedBy, tvTopic, tvCreatedDate, tvContent, tvStartTime, tvEndTime, tvQuantity, tvCurrency1;
    private EditText edtVolunteerFullName, edtVolunteerAddress, edtVolunteerCity, edtVolunteerPhone, edtVolunteerBirthDate, edtVolunteerFbLink, edtVolunteerEmail, edtVolunteerDepartment, edtVolunteerSkills, edtVolunteerNote;
    Button btnApply, btnSubmit,btnCancel;
    protected RadioButton rbMale, rbFemale;
    protected RadioGroup rbgGender;
    View formVolunteer;
    LinearLayout mProgressBar;
    NestedScrollView eventScrollView;
    ImageView imvImage;
    WebView wvContent;
    private ImageView md_nav_back;
    ArrayList<String> steps = new ArrayList<>();
    VerticalStepView setpview5;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_detail);
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setCancelable(false);
        mProgressDialog.setMessage(getString(R.string.txt_waiting));
        Intent intent = this.getIntent();
        id = intent.getLongExtra("id", 0);
        isRegister = intent.getBooleanExtra("isRegister", false);
        tvTitleEventt = findViewById(R.id.tv_detail_event_title);
        tvCreatedBy = findViewById(R.id.tv_detail_event_created_by);
        tvTopic = findViewById(R.id.tv_detail_event_topic);
        tvCreatedDate = findViewById(R.id.tv_createdat_detail_event);
        tvStartTime = findViewById(R.id.tv_starttime_detail_event);
        tvEndTime = findViewById(R.id.tv_endtime_detail_event);
        tvQuantity = findViewById(R.id.tv_detail_event_quantity);
        tvFee = findViewById(R.id.tv_detail_event_fee);
        tvCurrency1 = findViewById(R.id.tv_detail_event_currency1);
        btnApply = findViewById(R.id.btn_apply_detail_event);
        imvImage = findViewById(R.id.imv_detail_event_image);
        edtVolunteerFullName = findViewById(R.id.edt_volunteer_fullname);
        edtVolunteerAddress = findViewById(R.id.edt_volunteer_address);
        edtVolunteerBirthDate = findViewById(R.id.edt_volunteer_birthdate);
        edtVolunteerCity = findViewById(R.id.edt_volunteer_city);
        edtVolunteerEmail = findViewById(R.id.edt_volunteer_email);
        edtVolunteerFbLink = findViewById(R.id.edt_volunteer_fblink);
        rbMale = findViewById(R.id.rb_volunteer_male_gender);
        rbFemale = findViewById(R.id.rb_volunteer_female_gender);
        rbgGender = findViewById(R.id.rbg_volunteer_gender);
        edtVolunteerPhone = findViewById(R.id.edt_volunteer_phonenumber);
        edtVolunteerDepartment = findViewById(R.id.edt_volunteer_department);
        edtVolunteerNote = findViewById(R.id.edt_volunteer_note);
        edtVolunteerSkills = findViewById(R.id.edt_volunteer_skill);
        formVolunteer = (View) findViewById(R.id.form_register_volunteer);
        btnSubmit = findViewById(R.id.btn_volunteer_submit);
        btnCancel = findViewById(R.id.btn_volunteer_cancel);
        wvContent = findViewById(R.id.webview_content_event);
        setpview5 =  (VerticalStepView) findViewById(R.id.step_view);
        eventScrollView =  (NestedScrollView) findViewById(R.id.event_scrollview);
        if(isRegister)
        {
            formVolunteer.setVisibility(View.VISIBLE);
            eventScrollView.post(new Runnable() {
                public void run() {
                    eventScrollView.fullScroll(View.FOCUS_DOWN);
                }
            });
        }
        md_nav_back = findViewById(R.id.imv_back);
//        stepView.setSteps(steps);
        loadData();
        addListener();

    }

    private void loadData() {
        showLoading(true);
        new GetEventByIdTask(this, id, this).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    private void addListener() {
        //chỗ ni là m muốn onlick cái nào thì setOnClickListener(this) vào đây
        btnApply.setOnClickListener(this);
        btnSubmit.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
        md_nav_back.setOnClickListener(this);


    }


    @Override
    public void onClick(View view) {
        // khai báo nút detail findviewby id ở trên, rồi vô đây làm sự kiện chuyển sang trang detail
        switch (view.getId()) {
            case R.id.btn_apply_detail_event:
                formVolunteer.setVisibility(View.VISIBLE);
                eventScrollView.post(new Runnable() {
                    public void run() {
                        eventScrollView.fullScroll(View.FOCUS_DOWN);
                    }
                });
                LoadDataToForm();
                break;
            case R.id.btn_volunteer_submit:
                SubmitDataVolunteer();
                break;
            case R.id.btn_volunteer_cancel:
                formVolunteer.setVisibility(View.GONE);
                break;
            case R.id.imv_back:
                finish();
                break;
        }
    }

    private void LoadDataToForm() {
        showLoading(true);
        new GetProfileTask(this,this).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    private void SubmitDataVolunteer() {
        String andress = edtVolunteerAddress.getText().toString();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        java.util.Date date = null;
        try {
            date = sdf.parse(edtVolunteerBirthDate.getText().toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Long millis = date.getTime();
        Long birthDate = millis;
        String email = edtVolunteerEmail.getText().toString();
        String facebook = edtVolunteerFbLink.getText().toString();
        String fullName = edtVolunteerFullName.getText().toString();
        Boolean gender = null;
        if (rbFemale.isChecked()) gender = false;
        if (rbMale.isChecked()) gender = true;
        String note = edtVolunteerNote.getText().toString();
        String office = edtVolunteerDepartment.getText().toString();
        String phoneNumber = edtVolunteerPhone.getText().toString();
        String skills = edtVolunteerSkills.getText().toString();
        if(andress == "" || birthDate == null ||email == "" ||facebook =="" || fullName == "" || gender == null || office =="" || phoneNumber =="" || skills == "")
        {
            notify("Không thành công", "Phải nhập đầy đủ thông tin");
        }
        else {
            VolunteerInput volunteerInput = new VolunteerInput(andress, birthDate, email, facebook, fullName, gender, note, office, phoneNumber, skills);
            showLoading(true);
            new SignupVolunteerTask(this, volunteerInput, id, this).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        }
    }


    @Override
    public void onConnectionOpen(BaseTask task) {

    }

    @Override
    public void onConnectionSuccess(BaseTask task, Object data) {
        if (task instanceof GetEventByIdTask) {
            EventOutput output = (EventOutput) data;
            if (output.success) {
                setValue(output);
                showLoading(false);
            }
            else notify("Không thành công", output.getMessage());
        }
        if (task instanceof SignupVolunteerTask) {
            SignupOutput output = (SignupOutput) data;
            if (output.success) {
                Toast.makeText(ShowDetailEventActivity.this, "Đăng kí thành công", Toast.LENGTH_LONG).show();
                showLoading(false);
                formVolunteer.setVisibility(View.GONE);
            }
            else
            {
                 notify("Không thành công", output.getMessage());
            }
        }
        if (task instanceof GetProfileTask) {
            ProfileOutput output = (ProfileOutput) data;
            if (output.success) {
                setValueProfile(output);
                showLoading(false);
            }
            else notify("Không thành công", output.getMessage());
        }
    }

    private void setValueProfile(ProfileOutput output) {
        DataProfile getData = output.getData();
        edtVolunteerFullName.setText(getData.getLastName() + " "+getData.getFirstName());
        edtVolunteerAddress.setText(getData.getAddress().toString());
        edtVolunteerBirthDate.setText(new SimpleDateFormat("dd/MM/yyyy").format(new java.sql.Date(getData.getBirthDate())));
        edtVolunteerCity.setText(getData.getCity().toString());
        edtVolunteerEmail.setText(getData.getEmail().toString());
        edtVolunteerFbLink.setText(getData.getFacebookLink().toString());
        if (getData.getGender()) {
            rbMale.setChecked(true);
        } else {
            rbFemale.setChecked(true);
        }
        edtVolunteerPhone.setText(getData.getPhoneNumber().toString());
        final Calendar myCalendar = Calendar.getInstance();
        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }
            private void updateLabel() {
                String myFormat = "dd/MM/yyyy"; //In which you need put here
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
                edtVolunteerBirthDate.setText(sdf.format(myCalendar.getTime()));
            }
        };

        edtVolunteerBirthDate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                java.util.Date birth = null;
                try {
                    birth = sdf.parse(edtVolunteerBirthDate.getText().toString());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                int year = birth.getYear() +1900;
                int month =birth.getMonth() ;
                int dayofmonth = birth.getDate();
                DatePickerDialog dialog = new DatePickerDialog(ShowDetailEventActivity.this,R.style.DialogTheme ,date,year,month,dayofmonth);
                dialog.getDatePicker().setMinDate(15177000);
                dialog.show();
            }
        });
    }

    private void setValue(EventOutput output) {
        tvTitleEventt.setText(output.getData().getName());
        Picasso.with(this).load(output.getData().getImage()).fit().centerCrop().noPlaceholder().into(imvImage);
        tvCreatedBy.setText(output.getData().getCreatedBy().getFirstName() + " " + output.getData().getCreatedBy().getLastName());
        tvTopic.setText(output.getData().getEventTopic().getName());
        tvCreatedDate.setText(new SimpleDateFormat("dd/MM/yyyy").format(new Date(output.getData().getCreatedAt())));
        tvStartTime.setText(new SimpleDateFormat("dd/MM/yyyy").format(new Date(output.getData().getStartTime())));
        tvEndTime.setText(new SimpleDateFormat("dd/MM/yyyy").format(new Date(output.getData().getEndTime())));
        tvCurrency1.setText(output.getData().getCurrency().getName());
        tvQuantity.setText(output.getData().getExpectedQuantity().toString());
        tvFee.setText(output.getData().getFee().toString());



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


//        wvContent.getSettings().setJavaScriptEnabled(true);

        final Calendar myCalendar = Calendar.getInstance();
        Long milis = myCalendar.getTimeInMillis();
//        wvContent.loadData(output.getData().getDescription(), "text/html", "UTF-8");
        wvContent.loadDataWithBaseURL("https://www.youtube.com", output.getData().getDescription(),
                "text/html", "UTF-8", null);
        List<String> stepsBeanList = new ArrayList<>();
        List<EventSchedule> schedules = output.getData().getSchedules();
        boolean isTime = false;
        int pos = schedules.size() -1;
        for(int i =0 ; i< output.getData().getSchedules().size();i++)
        {
            if(schedules.get(i).getStartTime() >= milis)
            {
                if(!isTime)
                {
                    pos = i;
                    isTime = true;
                }
                stepsBeanList.add(schedules.get(i).getSchedule().toString() +"\n"+schedules.get(i).getLocation() +", "+ new SimpleDateFormat("dd/MM/yyyy").format(new Date(schedules.get(i).getStartTime())));
            }
            else
                if (schedules.get(i).getStartTime() < milis)
                    {
                        stepsBeanList.add(schedules.get(i).getSchedule().toString() +"\n"+schedules.get(i).getLocation() +", "+ new SimpleDateFormat("dd/MM/yyyy").format(new Date(schedules.get(i).getStartTime())));
                    }

        }


        setpview5
                .setStepsViewIndicatorComplectingPosition(pos)//设置完成的步数
                .reverseDraw(false)//default is true
                .setStepViewTexts(stepsBeanList)//总步骤
                .setLinePaddingProportion(1.0f)//设置indicator线与线间距的比例系数
                .setTextSize(14)
                .setStepsViewIndicatorCompletedLineColor(ContextCompat.getColor(this, android.R.color.black))//设置StepsViewIndicator完成线的颜色
                .setStepsViewIndicatorUnCompletedLineColor(ContextCompat.getColor(this, R.color.cardview_dark_background))//设置StepsViewIndicator未完成线的颜色
                .setStepViewComplectedTextColor(ContextCompat.getColor(this, android.R.color.holo_green_dark))//设置StepsView text完成线的颜色
                .setStepViewUnComplectedTextColor(ContextCompat.getColor(this, R.color.colorPrimaryDark))//设置StepsView text未完成线的颜色
                .setStepsViewIndicatorCompleteIcon(ContextCompat.getDrawable(this, R.drawable.complted))//设置StepsViewIndicator CompleteIcon
                .setStepsViewIndicatorDefaultIcon(ContextCompat.getDrawable(this, R.drawable.default_icon))//设置StepsViewIndicator DefaultIcon
                .setStepsViewIndicatorAttentionIcon(ContextCompat.getDrawable(this, R.drawable.attention));//设置StepsViewIndicator AttentionIcon
            ;


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
