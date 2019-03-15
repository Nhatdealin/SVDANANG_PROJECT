package com.diendan.svdanang;

import android.support.annotation.NonNull;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.diendan.svdanang.Adapter.RecyclerviewAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements DrawerLayout.DrawerListener {
    private  RecyclerView recyclerView;
    private RecyclerviewAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private DrawerLayout mDrawerLayout;
    private TextView tvUssername;
    private ImageView imgAvatar, mImvBack;
    private View mLayoutSlide;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview_menu);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        ArrayList<Menuitem> menuitemList = new ArrayList<>();
        menuitemList.add(new Menuitem(R.drawable.icon_meeting_management,"Quản lý lịch họp"));
        menuitemList.add(new Menuitem(R.drawable.icon_member_management,"Quản lý thành viên"));
        menuitemList.add(new Menuitem(R.drawable.icon_personal_infomation,"Thông tin cá nhân"));
        menuitemList.add(new Menuitem(R.drawable.icon_user_management,"Quản lý tài khoản"));
        mAdapter = new RecyclerviewAdapter(this,menuitemList);
        recyclerView.setAdapter(mAdapter);
        }
    protected  int initLayout(){
        return R.layout.activity_main;
    }

    protected  void initComponent(){
        imgAvatar = findViewById(R.id.imv_avatar);
        tvUssername = findViewById(R.id.tv_fullname);
        mImvBack = findViewById(R.id.imv_nav_left);
        mImvBack.setVisibility(View.GONE);
        mDrawerLayout = findViewById(R.id.drawer_layout);
        mDrawerLayout.addDrawerListener(this);
        mLayoutSlide = findViewById(R.id.layout_left_menu);
    }
    @Override
    protected  void onResume(){

        super.onResume();
    }
    protected void loadProfile(){

    }
    protected  void addListener(){

    }

    public void onClick(View view){

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
