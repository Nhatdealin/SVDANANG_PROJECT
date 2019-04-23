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
import com.diendan.svdanang.models.ContentBlogPost;
import com.squareup.picasso.Picasso;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

public class SeemorePageRecyclerviewAdapter extends  RecyclerView.Adapter<SeemorePageRecyclerviewAdapter.MyViewHolder>  {
    private List<ContentBlogPost> mDataset;
    private Context mContext;
    Picasso picasso;


    public static class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView imvImage;
        TextView tvTopic,tvTitle,tvSummary,tvCreatedAt;
        private View menuGroup;
        private View viewDividerTop;


        public MyViewHolder(View view) {
            super(view);
            imvImage = (ImageView) view.findViewById(R.id.imv_content_see_more);
            tvTitle = (TextView) view.findViewById(R.id.tv_title_see_more);
            tvTopic = (TextView) view.findViewById(R.id.tv_topic);
            tvSummary = (TextView) view.findViewById(R.id.tv_summary_see_more);
            tvCreatedAt = (TextView) view.findViewById(R.id.tv_createdat_blogpost);

        }
    }

    public SeemorePageRecyclerviewAdapter(Context context, List<ContentBlogPost> myDataset) {
        this.mDataset = myDataset;
        this.mContext = context;
    }

    public SeemorePageRecyclerviewAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new SeemorePageRecyclerviewAdapter.MyViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.see_more_item_recyclerview, parent, false));
    }

    @Override
    public void onBindViewHolder(SeemorePageRecyclerviewAdapter.MyViewHolder holder, int position) {
        ContentBlogPost seemoreitem = mDataset.get(position);
        Picasso.with(mContext).load(seemoreitem.getThumbnailImage()).fit().into(holder.imvImage);
        holder.tvTopic.setText(seemoreitem.getBlogPostTopic().getName());
        holder.tvTitle.setText(seemoreitem.getTitle());
        holder.tvCreatedAt.setText(new SimpleDateFormat("dd/MM/yyyy").format(new Date(seemoreitem.getCreatedDate())));
        holder.tvSummary.setText(seemoreitem.getShortContent());
    }



    @Override
    public int getItemCount() {
        return mDataset.size();
    }


}
