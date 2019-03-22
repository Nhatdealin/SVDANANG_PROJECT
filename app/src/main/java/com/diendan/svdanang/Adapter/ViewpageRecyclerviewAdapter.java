package com.diendan.svdanang.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.diendan.svdanang.Menuitem;
import com.diendan.svdanang.R;
import com.diendan.svdanang.Viewpageitem;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ViewpageRecyclerviewAdapter extends  RecyclerView.Adapter<ViewpageRecyclerviewAdapter.MyViewHolder>  {
    private List<Viewpageitem> mDataset;
    private Context mContext;
    Picasso picasso;

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView imvImage;
        private View menuGroup;
        private View viewDividerTop;

        public MyViewHolder(View view) {
            super(view);
            imvImage = (ImageView) view.findViewById(R.id.imv_viewpage_item);
        }
    }

    public ViewpageRecyclerviewAdapter(Context context, List<Viewpageitem> myDataset) {
        this.mDataset = myDataset;
        this.mContext = context;
    }

    public ViewpageRecyclerviewAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewpageRecyclerviewAdapter.MyViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.home_item_viewpage, parent, false));
    }

    @Override
    public void onBindViewHolder( ViewpageRecyclerviewAdapter.MyViewHolder holder, int position) {
        Viewpageitem imageview = mDataset.get(position);
        Picasso.with(mContext).load(imageview.getIdentity()).fit().into(holder.imvImage);
        holder.imvImage.setImageResource(imageview.getIdentity());
    }



    @Override
    public int getItemCount() {
        return mDataset.size();
    }


}
