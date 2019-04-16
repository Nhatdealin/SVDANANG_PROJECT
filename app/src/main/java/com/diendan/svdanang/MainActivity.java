package com.diendan.svdanang;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.diendan.svdanang.Adapter.MenuRecyclerviewAdapter;
import com.diendan.svdanang.Fragment.EventFragment;
import com.diendan.svdanang.Fragment.HomeFragment;
import com.diendan.svdanang.Fragment.ProjectFragment;
import com.diendan.svdanang.api.ApiListener;
import com.diendan.svdanang.api.models.ProfileOutput;
import com.diendan.svdanang.models.DataProfile;
import com.diendan.svdanang.tasks.BaseTask;
import com.diendan.svdanang.tasks.GetProfileTask;
import com.diendan.svdanang.utils.Constants;
import com.diendan.svdanang.utils.SharedPreferenceHelper;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ApiListener<ProfileOutput>,DrawerLayout.DrawerListener, View.OnClickListener {
    private RecyclerView mRecyclerView;
    private MenuRecyclerviewAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private DrawerLayout mDrawerLayout;
    private TextView tvUssername;
    private ImageView imgAvatar, mImvBack, mImvNav;
    private View mLayoutSlide, mCurrentTab, mEventTab, mFindTab, mProjectTab;
    private Fragment mCurrentFragment, mMenuBefore;
    protected ProgressDialog mProgressDialog;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview_menu);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        ArrayList<Menuitem> menuitemList = new ArrayList<>();
        menuitemList.add(new Menuitem(Constants.MENU_ITEM_MANAGE_MEETING, 1, R.drawable.icon_meeting_management, "Quản lý lịch họp"));
        menuitemList.add(new Menuitem(Constants.MENU_ITEM_MANAGE_MEMBER, 2, R.drawable.icon_member_management, "Quản lý thành viên"));
        menuitemList.add(new Menuitem(Constants.MENU_ITEM_PROFILE, 3, R.drawable.icon_personal_infomation, "Thông tin cá nhân"));
        menuitemList.add(new Menuitem(Constants.MENU_ITEM_MANAGE_USER, 4, R.drawable.icon_user_management, "Quản lý tài khoản"));
        mAdapter = new MenuRecyclerviewAdapter(this, menuitemList);
        mRecyclerView.setAdapter(mAdapter);
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setCancelable(false);
        mProgressDialog.setMessage(getString(R.string.txt_waiting));
        initComponent();
        loadProfile();
        addListener();
    }


    protected void initComponent() {
        imgAvatar = findViewById(R.id.imv_avatar);
        tvUssername = findViewById(R.id.tv_fullname);
        mCurrentTab = findViewById(R.id.imv_ic_home);
        mEventTab = findViewById(R.id.imv_ic_event);
        mFindTab = findViewById(R.id.imv_ic_find);
        mProjectTab = findViewById(R.id.imv_ic_project);
        mImvNav = findViewById(R.id.imv_nav_left);
        mImvBack = findViewById(R.id.imv_ic_back);
        mImvBack.setVisibility(View.VISIBLE);
        mLayoutSlide = findViewById(R.id.layout_left_menu);
        mDrawerLayout = findViewById(R.id.drawer_layout);
        mDrawerLayout.addDrawerListener(this);
        mCurrentFragment = HomeFragment.newInstance();
        mCurrentTab.setSelected(true);
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.add(R.id.main_frame, mCurrentFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    protected void onResume() {

        super.onResume();
    }

    protected void loadProfile() {

        if (SharedPreferenceHelper.getInstance(this).get(Constants.PREF_PERSON_NAME) != null) {
            tvUssername.setText(SharedPreferenceHelper.getInstance(this).get(Constants.PREF_PERSON_NAME));
        }
    }

    protected void addListener() {
        mImvBack.setOnClickListener(this);
        mImvNav.setOnClickListener(this);
        mCurrentTab.setOnClickListener(this);
        mFindTab.setOnClickListener(this);
        mProjectTab.setOnClickListener(this);
        mEventTab.setOnClickListener(this);
        mAdapter.setOnItemClickListener(new MenuRecyclerviewAdapter.IOnItemClickedListener () {
            @Override
            public void onItemClick(int id) {

            }

            @Override
            public void onItemClickComment(int id) {
                switch (id) {
                    case 110:
                        showLoading(true);
                        GetProfile();
                        break;
                }
            }


            @Override
            public void userSelectedAValue(String value) {

            }
        });
    }

    private void GetProfile() {
        new GetProfileTask(this,this).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imv_nav_left:
                onBackPressed();
                break;
            case R.id.imv_ic_back:
                onBackPressed();
                break;
            case R.id.imv_ic_event:
                mCurrentTab.setSelected(false);
                mCurrentTab = view;
                mCurrentTab.setSelected(true);
                setNewPage(new EventFragment());
                break;
            case R.id.imv_ic_home:
                mCurrentTab.setSelected(false);
                mCurrentTab = view;
                mCurrentTab.setSelected(true);
                setNewPage(new HomeFragment());
                break;
            case R.id.imv_ic_find:

                break;
            case R.id.imv_ic_project:
                mCurrentTab.setSelected(false);
                mCurrentTab = view;
                mCurrentTab.setSelected(true);
                setNewPage(new ProjectFragment());
                break;
        }
    }


    public void setNewPage(Fragment fragment) {
        try {
            if (getFragmentManager().getBackStackEntryCount() > 0) {
                for (int i = 0; i < getFragmentManager().getBackStackEntryCount(); i++) {
                    getFragmentManager().popBackStackImmediate();
                }
            }
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.main_frame, fragment, "currentFragment");
            transaction.commitAllowingStateLoss();
            if (mCurrentFragment != null)
                transaction.remove(mCurrentFragment);
            mCurrentFragment = fragment;

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addFragment(Fragment fragment) {
        getFragmentManager().beginTransaction()
                .replace(R.id.main_frame, fragment)
                .addToBackStack(null)
                .commit();
    }

    public void replaceFragment(Fragment fragment) {
        getFragmentManager().popBackStackImmediate(R.id.main_frame, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        getFragmentManager().beginTransaction()
                .replace(R.id.main_frame, fragment)
                .commit();
    }

    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            mDrawerLayout.openDrawer(GravityCompat.START);
        }
    }

    @Override
    public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {

    }

    @Override
    public void onDrawerOpened(@NonNull View drawerView) {

    }

    @Override
    public void onDrawerClosed(@NonNull View drawerView) {

    }

    @Override
    public void onDrawerStateChanged(int newState) {

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
    public void onConnectionOpen(BaseTask task) {

    }

    @Override
    public void onConnectionSuccess(BaseTask task, ProfileOutput data) {

        if(task instanceof GetProfileTask){

            ProfileOutput output = data;
            DataProfile getData = data.getData();
            if(output.success){
                Intent i = new Intent(MainActivity.this, GetProfileActivity.class);
                i.putExtra("firstname",getData.getFirstName());
                i.putExtra("lastname",getData.getLastName());
                i.putExtra("address",getData.getAddress());
                i.putExtra("birthdate",getData.getBirthDate());
                i.putExtra("city",getData.getCity());
                i.putExtra("department",getData.getDepartment());
                i.putExtra("email",getData.getEmail());
                i.putExtra("fblink",getData.getFacebookLink());
                i.putExtra("gender",getData.getGender());
                i.putExtra("phone",getData.getPhoneNumber());
                i.putExtra("username",getData.getUserName());
                showLoading(false);
                startActivity(i);
            }

        }
    }

    @Override
    public void onConnectionError(BaseTask task, Exception exception) {

    }
}
