package com.diendan.svdanang.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.diendan.svdanang.Fragment.HomeFragment;
import com.diendan.svdanang.Homeitem;
import com.diendan.svdanang.Menuitem;
import com.diendan.svdanang.R;

import java.util.List;

public class HomeRecyclerviewAdapter extends RecyclerView.Adapter<HomeRecyclerviewAdapter.MyViewHolder> {
    private List<Homeitem> mDataset;
    private Context mContext;

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle, tvContent1, tvContent2, tvContent3, tvContent4;
        ImageView imvImage1, imvImage2, imvImage3, imvImage4;
        private View homeGroup;
        private View viewDividerTop;

        public MyViewHolder(View view) {
            super(view);
            tvTitle = (TextView) view.findViewById(R.id.tv_home_title);
            tvTitle.setSelected(true);
            tvContent1 = view.findViewById(R.id.tv_home_content1);
            tvContent2 = view.findViewById(R.id.tv_home_content2);
            tvContent3 = view.findViewById(R.id.tv_home_content3);
            tvContent4 = view.findViewById(R.id.tv_home_content4);
            imvImage1 = (ImageView) view.findViewById(R.id.imv_home_item1);
            imvImage2 = (ImageView) view.findViewById(R.id.imv_home_item2);
            imvImage3 = (ImageView) view.findViewById(R.id.imv_home_item3);
            imvImage4 = (ImageView) view.findViewById(R.id.imv_home_item4);
            homeGroup = view.findViewById(R.id.home_group);
            viewDividerTop = view.findViewById(R.id.view_divider_top_home);
        }
    }

    public HomeRecyclerviewAdapter(Context context, List<Homeitem> myDataset) {
        this.mDataset = myDataset;
        this.mContext = context;
    }

    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.home_item_recyclerview, parent, false));
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Homeitem menu = mDataset.get(position);
        holder.tvTitle.setText(menu.getTitle());
        holder.tvContent1.setText(menu.getData1());
        holder.tvContent2.setText(menu.getData2());
        holder.tvContent3.setText(menu.getData3());
        holder.tvContent4.setText(menu.getData4());
        holder.imvImage1.setImageResource(menu.getIdentity1());
        holder.imvImage2.setImageResource(menu.getIdentity2());
        holder.imvImage3.setImageResource(menu.getIdentity3());
        holder.imvImage4.setImageResource(menu.getIdentity4());
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }


}
