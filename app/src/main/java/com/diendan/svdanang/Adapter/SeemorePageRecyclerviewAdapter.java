package com.diendan.svdanang.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.diendan.svdanang.R;
import com.diendan.svdanang.Seemoreitem;
import com.diendan.svdanang.Viewpageitem;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SeemorePageRecyclerviewAdapter extends  RecyclerView.Adapter<SeemorePageRecyclerviewAdapter.MyViewHolder>  {
    private List<Seemoreitem> mDataset;
    private Context mContext;
    Picasso picasso;


    public static class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView imvImage;
        TextView tvTopic,tvTitle,tvSummary;
        private View menuGroup;
        private View viewDividerTop;


        public MyViewHolder(View view) {
            super(view);
            imvImage = (ImageView) view.findViewById(R.id.imv_content_see_more);
            tvTitle = (TextView) view.findViewById(R.id.tv_title_see_more);
            tvTopic = (TextView) view.findViewById(R.id.tv_topic);
            tvSummary = (TextView) view.findViewById(R.id.tv_summary_see_more);

        }
    }

    public SeemorePageRecyclerviewAdapter(Context context, List<Seemoreitem> myDataset) {
        this.mDataset = myDataset;
        this.mContext = context;
    }

    public SeemorePageRecyclerviewAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new SeemorePageRecyclerviewAdapter.MyViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.see_more_item_recyclerview, parent, false));
    }

    @Override
    public void onBindViewHolder(SeemorePageRecyclerviewAdapter.MyViewHolder holder, int position) {
        Seemoreitem seemoreitem = mDataset.get(position);
        Picasso.with(mContext).load(seemoreitem.getIdentity()).fit().into(holder.imvImage);
        holder.imvImage.setImageResource(seemoreitem.getIdentity());
        holder.tvTopic.setText(seemoreitem.getTopic());
        holder.tvTitle.setText(seemoreitem.getTitle());
        holder.tvSummary.setText(seemoreitem.getSummary());
    }



    @Override
    public int getItemCount() {
        return mDataset.size();
    }


}
