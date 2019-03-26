package com.diendan.svdanang;


import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;



import com.diendan.svdanang.Adapter.SeemorePageRecyclerviewAdapter;
import com.diendan.svdanang.Adapter.ViewpageRecyclerviewAdapter;

import com.diendan.svdanang.R;
import com.diendan.svdanang.Seemoreitem;


import java.util.ArrayList;

public class SeeMoreActivity extends AppCompatActivity implements View.OnClickListener {
    RecyclerView mSeemoreRecyclerView;
    SeemorePageRecyclerviewAdapter mAdapter;
    ViewpageRecyclerviewAdapter mViewPageAdapter;
    ArrayList<Seemoreitem> seemorelist;
    LinearLayoutManager mLayoutManager;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.see_more_fragment);
        mSeemoreRecyclerView = (RecyclerView) findViewById(R.id.recyclerview_see_more_fragment);
        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mSeemoreRecyclerView.setLayoutManager(mLayoutManager);

        seemorelist = new ArrayList<>();
        seemorelist.add(new Seemoreitem(R.drawable.cover4,"Chương trình Hành trình SV 'Thập Niên 80' ","Hành Trình SV","Chương trình với các thử thách những năm thập niên 80, giúp cho người tham gia sống trong không khí của những hoài niệm và đầy sự mộc mạc"));
        seemorelist.add(new Seemoreitem(R.drawable.cover3,"Chương trình tình nguyện 'Áo ấm mùa đông 3' ","HTình nguyện","Chương trình tình nguyện tại làng AHleo thuộc xã Đông Giang, Tỉnh Quảng Nam, với tinh thân tương thân tương ái,....."));
        seemorelist.add(new Seemoreitem(R.drawable.cover4,"Chương trình Hành trình SV 'Thập Niên 80' ","Hành Trình SV","Chương trình với các thử thách những năm thập niên 80, giúp cho người tham gia sống trong không khí của những hoài niệm và đầy sự mộc mạc"));
        seemorelist.add(new Seemoreitem(R.drawable.cover3,"Chương trình tình nguyện 'Áo ấm mùa đông 3' ","HTình nguyện","Chương trình tình nguyện tại làng AHleo thuộc xã Đông Giang, Tỉnh Quảng Nam, với tinh thân tương thân tương ái,....."));
        mAdapter = new SeemorePageRecyclerviewAdapter(this, seemorelist);
        mSeemoreRecyclerView.setAdapter(mAdapter);

        addListener();

    }

    private void addListener() {
        //chỗ ni là m muốn onlick cái nào thì setOnClickListener(this) vào đây

    }


    @Override
    public void onClick(View view) {
        // khai báo nút detail findviewby id ở trên, rồi vô đây làm sự kiện chuyển sang trang detail
        switch (view.getId()) {

        }

    }
}
