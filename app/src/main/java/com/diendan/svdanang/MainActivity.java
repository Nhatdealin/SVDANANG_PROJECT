package com.diendan.svdanang;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.support.annotation.NonNull;
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
import com.diendan.svdanang.Fragment.HomeFragment;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements DrawerLayout.DrawerListener, View.OnClickListener {
    private RecyclerView mRecyclerView;
    private MenuRecyclerviewAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private DrawerLayout mDrawerLayout;
    private TextView tvUssername;
    private ImageView imgAvatar, mImvBack,mImvNav;
    private View mLayoutSlide, mCurrentTab;
    private Fragment mCurrentFragment, mMenuBefore;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview_menu);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        ArrayList<Menuitem> menuitemList = new ArrayList<>();
        menuitemList.add(new Menuitem(R.drawable.icon_meeting_management, "Quản lý lịch họp"));
        menuitemList.add(new Menuitem(R.drawable.icon_member_management, "Quản lý thành viên"));
        menuitemList.add(new Menuitem(R.drawable.icon_personal_infomation, "Thông tin cá nhân"));
        menuitemList.add(new Menuitem(R.drawable.icon_user_management, "Quản lý tài khoản"));
        mAdapter = new MenuRecyclerviewAdapter(this, menuitemList);
        mRecyclerView.setAdapter(mAdapter);
        initComponent();
        addListener();
    }



    protected void initComponent() {
        imgAvatar = findViewById(R.id.imv_avatar);
        tvUssername = findViewById(R.id.tv_fullname);
        mImvNav = findViewById(R.id.imv_nav_left);
        mImvBack = findViewById(R.id.imv_ic_back);
        mImvBack.setVisibility(View.VISIBLE);
        mLayoutSlide = findViewById(R.id.layout_left_menu);
        mDrawerLayout = findViewById(R.id.drawer_layout);
        mDrawerLayout.addDrawerListener(this);
        mCurrentFragment = HomeFragment.newInstance();
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

    }

    protected void addListener() {
            mImvBack.setOnClickListener(this);
            mImvNav.setOnClickListener(this);
    }

    public void onClick(View view) {
            switch (view.getId()){
                case R.id.imv_nav_left:
                    onBackPressed();
                    break;
                case R.id.imv_ic_back:
                    onBackPressed();
                    break;
            }
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
}
