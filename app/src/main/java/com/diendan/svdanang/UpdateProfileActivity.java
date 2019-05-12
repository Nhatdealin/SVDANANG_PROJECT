package com.diendan.svdanang;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.SystemClock;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.diendan.svdanang.api.ApiListener;
import com.diendan.svdanang.api.models.AvatarOutput;
import com.diendan.svdanang.api.models.BaseOutput;
import com.diendan.svdanang.api.models.ProfileOutput;
import com.diendan.svdanang.api.objects.AvatarInput;
import com.diendan.svdanang.api.objects.UpdateProfileInput;
import com.diendan.svdanang.models.DataProfile;
import com.diendan.svdanang.tasks.BaseTask;
import com.diendan.svdanang.tasks.GetProfileTask;
import com.diendan.svdanang.tasks.UpdateAvatarTask;
import com.diendan.svdanang.tasks.UpdateProfileTask;
import com.diendan.svdanang.utils.Constants;
import com.diendan.svdanang.utils.SharedPreferenceHelper;
import com.soundcloud.android.crop.Crop;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import static java.nio.file.Files.copy;

public class UpdateProfileActivity extends AppCompatActivity implements ApiListener, View.OnClickListener {
    private Button btn_update_submit, btn_update_cancel, btnChoseImage;
    ImageButton btnTakeImage;
    private ImageView md_nav_back, imv_avatar;
    private TextView tvUsername, tvUpdateEmail;
    private boolean gender, isChangeAvatar = false;
    File finalAvatar;
    private Uri mImageCaptureUri;
    private EditText edtUpdateFirstname, edtUpdateLastname, edtUpdateGender, edtUpdateAddress, edtUpdateCity, edtUpdatePhone, edtUpdateBirthDate, edtUpdateFbLink;
    protected ProgressDialog mProgressDialog;
    protected RadioButton rbMale, rbFemale;
    protected RadioGroup rbgGender;
    private String myImagePath, myImagePathCrop;
    String username, department, email, firstname, lastname, address, birthdate, city, fblink, phone, avatar;
    public static final String TAG = MainActivity.class.getSimpleName();
    private final int PICK_IMAGE_REQUEST = 71;
    private static final int SELECT_PICTURE = 279;
    private final static int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 195;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_update_profile);
        Intent intent = this.getIntent();
        firstname = intent.getStringExtra("firstname");
        lastname = intent.getStringExtra("lastname");
        address = intent.getStringExtra("address");
        avatar = intent.getStringExtra("avatar");
        birthdate = intent.getStringExtra("birthdate");
        city = intent.getStringExtra("city");
        email = intent.getStringExtra("email");
        fblink = intent.getStringExtra("fblink");
        gender = intent.getBooleanExtra("gender", true);
        phone = intent.getStringExtra("phone");
        username = intent.getStringExtra("username");
        department = intent.getStringExtra("department");
        btnChoseImage = findViewById(R.id.btn_chose_image);
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
        imv_avatar = findViewById(R.id.imv_avatar);
        edtUpdateFbLink = findViewById(R.id.edt_update_fblink);
        rbMale = findViewById(R.id.rb_update_male_gender);
        rbFemale = findViewById(R.id.rb_update_female_gender);
        rbgGender = findViewById(R.id.rbg_gender);
        edtUpdatePhone = findViewById(R.id.edt_update_phonenumber);
        tvUsername = findViewById(R.id.tv_infor_username);
        Picasso.with(this).load(avatar).noPlaceholder().fit().centerCrop().into(imv_avatar);
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
                edtUpdateBirthDate.setText(sdf.format(myCalendar.getTime()));
            }
        };

        edtUpdateBirthDate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                Date birth = null;
                try {
                    birth = sdf.parse(edtUpdateBirthDate.getText().toString());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                int year = birth.getYear() + 1900;
                int month = birth.getMonth();
                int dayofmonth = birth.getDate();
                DatePickerDialog dialog = new DatePickerDialog(UpdateProfileActivity.this, R.style.DialogTheme, date, year, month, dayofmonth);
                dialog.getDatePicker().setMinDate(15177000);
                dialog.show();
            }
        });
        addListener();
    }


    protected void addListener() {
        btn_update_submit.setOnClickListener(this);
        btn_update_cancel.setOnClickListener(this);
        btnChoseImage.setOnClickListener(this);
        md_nav_back.setOnClickListener(this);
        rbgGender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int checkedRadioId = group.getCheckedRadioButtonId();
                if (checkedRadioId == R.id.rb_update_female_gender) gender = false;
                else gender = true;
            }

        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_update_submit:
                if (isChangeAvatar) {
                    submitAvatar();
                } else submitProfile();
                break;
            case R.id.btn_update_cancel:
                exit();
                break;

            case R.id.imv_back:
                exit();
                break;
            case R.id.btn_chose_image:
                onClickChooseFromGallery();
                break;
        }

    }

    public void submitAvatar() {
        showLoading(true);
        new UpdateAvatarTask(this, finalAvatar, this).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    private void submitProfile() {
        showLoading(true);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date date = null;
        try {
            date = sdf.parse(edtUpdateBirthDate.getText().toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Long millis = date.getTime();
        UpdateProfileInput update = new UpdateProfileInput(edtUpdateAddress.getText().toString(), avatar, millis, edtUpdateCity.getText().toString(), edtUpdateFbLink.getText().toString(), edtUpdateFirstname.getText().toString(), gender, edtUpdateLastname.getText().toString(), edtUpdatePhone.getText().toString());
        new UpdateProfileTask(this, update, this).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

//    public void onClickTakeAPhoto() {
//        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//        File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/TASKSAPP");
//        if (!file.exists()) {
//            file.mkdirs();
//        }
//        File f = new File(file.getPath() + "/" + SystemClock.currentThreadTimeMillis() + ".jpg");
//        final Uri data = FileProvider.getUriForFile(this, "nauq.mal.com.fileprovider", new File(file.getPath() + "/" + SystemClock.currentThreadTimeMillis() + ".jpg"));
//        this.grantUriPermission(this.getPackageName(), data, Intent.FLAG_GRANT_READ_URI_PERMISSION);
//        mImageCaptureUri = data;
//
//        myImagePath = f.getPath();
//        takePictureIntent.putExtra(android.provider.MediaStore.EXTRA_OUTPUT, mImageCaptureUri);
//        takePictureIntent.putExtra("return-data", true);
//        takePictureIntent.putExtra(MediaStore.EXTRA_FINISH_ON_COMPLETION, true);
//        startActivityForResult(takePictureIntent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
//    }

    private void onClickChooseFromGallery() {
        File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/HR");
        if (!file.exists()) {
            file.mkdirs();
        }
        File f = new File(file.getPath() + "/" + SystemClock.currentThreadTimeMillis() + ".jpg");
        mImageCaptureUri = Uri.fromFile(f);
        myImagePath = f.getPath();

        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_PICK);
        intent.putExtra(android.provider.MediaStore.EXTRA_OUTPUT, mImageCaptureUri);
        this.startActivityForResult(Intent.createChooser(intent, "Select Picture"), SELECT_PICTURE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if(requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
//                && data != null && data.getData() != null )
//        {
//            Uri filePath = data.getData();
//            try {
//                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
//                imv_avatar.setImageBitmap(bitmap);
//                isChangeAvatar = true;
//            }
//            catch (IOException e)
//            {
//                e.printStackTrace();
//            }
//        }

        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE:
                    try {
                        Bundle extras = null;
                        if (data != null) {
                            extras = data.getExtras();
                        }
                        if (data != null && extras != null && extras.get("data") != null) {
                            File f = new File(myImagePath);
                            Bitmap bitMap = (Bitmap) extras.get("data");
                            try {
                                f.createNewFile();
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }
                            ByteArrayOutputStream bos = new ByteArrayOutputStream();
                            bitMap.compress(Bitmap.CompressFormat.PNG, 0, bos);
                            byte[] bitmapdata = bos.toByteArray();
                            FileOutputStream fos;
                            try {
                                fos = new FileOutputStream(f);
                                try {
                                    fos.write(bitmapdata);
                                    mImageCaptureUri = Uri.fromFile(f);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            } catch (FileNotFoundException e) {
                                e.printStackTrace();
                            }
                        } else {
                            // Take with Uri
                        }
                    } catch (NullPointerException ex) {

                    }
                    doCrop();


                    isChangeAvatar = true;
                    break;
                case SELECT_PICTURE:
                    mImageCaptureUri = data.getData();
                    File finalFile1 = new File(getRealPathFromURI(mImageCaptureUri));
                    String[] spl = finalFile1.getPath().split("/");
                    String name1 = spl[spl.length - 1];
                    String[] end = name1.split("\\.");
                    String endName = end[end.length - 1];
                    String newName = finalFile1.getPath().substring(0, finalFile1.getPath().length() - 1 - name1.length())
                            + "/" + SystemClock.currentThreadTimeMillis() + "." + endName;
                    try {
                        copy(finalFile1, new File(newName));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    myImagePath = newName;
                    doCrop();
                    break;
                case Crop.REQUEST_CROP:
                    Uri tempUri = Crop.getOutput(data);
//                    mImgAvatar.setImageURI(tempUri);
                    File finalFile = new File(tempUri.getPath());
                    String[] spl1 = finalFile.getPath().split("/");
                    String name11 = spl1[spl1.length - 1];
                    String[] end1 = name11.split("\\.");
                    String endName1 = end1[end1.length - 1];
                    String newName1 = finalFile.getPath().substring(0,
                            finalFile.getPath().length() - 1 - name11.length()) + "/"
                            + SystemClock.currentThreadTimeMillis() + "." + endName1;

                    try {
                        copy(finalFile, new File(newName1));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    myImagePathCrop = newName1;
                    if (myImagePathCrop != null) {
                        File f = new File(myImagePathCrop);
                        if (f.exists()) {
                            finalAvatar = f;
                        }
                    }

                    break;
            }

        }
    }

    private void doCrop() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd'T'HHmmss");
        String timeStamp = dateFormat.format(new Date());
        String imageFileName = "picture_crop" + timeStamp + ".jpg";
        Uri destination = Uri.fromFile(new File(this.getCacheDir(), imageFileName));
        Crop.of(mImageCaptureUri, destination).asSquare().start(this);
        imv_avatar.setImageURI(mImageCaptureUri);
        isChangeAvatar = true;
    }

    private void exit() {
        Intent i = new Intent(UpdateProfileActivity.this, GetProfileActivity.class);
        i.putExtra("firstname", firstname);
        i.putExtra("lastname", lastname);
        i.putExtra("address", address);
        i.putExtra("birthdate", birthdate);
        i.putExtra("city", city);
        i.putExtra("email", email);
        i.putExtra("fblink", fblink);
        i.putExtra("gender", gender);
        i.putExtra("phone", phone);
        i.putExtra("username", username);
        i.putExtra("department", department);
        showLoading(false);
        startActivity(i);
    }

    public String getRealPathFromURI(Uri uri) {
        Cursor cursor = this.getContentResolver().query(uri, null, null, null, null);
        cursor.moveToFirst();
        int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
        return cursor.getString(idx);
    }

    void copy(File source, File target) throws IOException {

        InputStream in = new FileInputStream(source);
        OutputStream out = new FileOutputStream(target);
        byte[] buf = new byte[1024];
        int len;

        while ((len = in.read(buf)) > 0) {
            out.write(buf, 0, len);
        }
        in.close();
        out.close();
    }

    @Override
    public void onConnectionOpen(BaseTask task) {

    }


    @Override
    public void onConnectionSuccess(BaseTask task, Object data) {
        if (task instanceof UpdateProfileTask) {
            ProfileOutput output = (ProfileOutput) data;
            DataProfile getData = ((ProfileOutput) data).getData();
            if (output.success) {
                Intent i = new Intent(UpdateProfileActivity.this, GetProfileActivity.class);
                i.putExtra("firstname", getData.getFirstName());
                i.putExtra("lastname", getData.getLastName());
                i.putExtra("address", getData.getAddress());
                i.putExtra("avatar",getData.getAvatar());
                i.putExtra("birthdate", getData.getBirthDate());
                i.putExtra("city", getData.getCity());
                i.putExtra("email", email);
                i.putExtra("fblink", getData.getFacebookLink());
                i.putExtra("gender", getData.getGender());
                i.putExtra("phone", getData.getPhoneNumber());
                i.putExtra("username", username);
                i.putExtra("department", department);
                SharedPreferenceHelper.getInstance(this).set(Constants.PREF_AVATAR, output.getData().getAvatar());
                SharedPreferenceHelper.getInstance(this).set(Constants.PREF_USERNAME, output.getData().getUserName());
                SharedPreferenceHelper.getInstance(this).set(Constants.PREF_PERSON_NAME, String.valueOf(output.getData().getFirstName() + " " + output.getData().getLastName()));
                showLoading(false);
                startActivity(i);
            }
        }
        if (task instanceof UpdateAvatarTask) {
            AvatarOutput output1 = (AvatarOutput) data;
            avatar = output1.getFileDownloadUri();
            SharedPreferenceHelper.getInstance(this).set(Constants.PREF_AVATAR, output1.getFileDownloadUri());
            submitProfile();


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
