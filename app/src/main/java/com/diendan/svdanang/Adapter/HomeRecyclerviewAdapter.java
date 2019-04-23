package com.diendan.svdanang.Adapter;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.diendan.svdanang.Homeitem;
import com.diendan.svdanang.R;
import com.diendan.svdanang.api.ApiListener;
import com.diendan.svdanang.api.models.BlogPostsOutput;
import com.diendan.svdanang.models.BlogPostTopic;
import com.diendan.svdanang.models.ContentBlogPost;
import com.diendan.svdanang.models.ContentProject;
import com.diendan.svdanang.models.DataBlogPostsTopic;
import com.diendan.svdanang.tasks.BaseTask;
import com.diendan.svdanang.tasks.GetBlogPostsByTopicTask;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class HomeRecyclerviewAdapter extends RecyclerView.Adapter<HomeRecyclerviewAdapter.MyViewHolder> {
    private List<Homeitem> mDataset;
    private Context mContext;
    public IOnItemClickedListener mIOnItemClickedListener;
    public void setOnItemClickListener(IOnItemClickedListener listener) {
        mIOnItemClickedListener = listener;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        BlogPostTopic topic;
        TextView tvTitle, tvContent1, tvContent2, tvContent3, tvContent4, tvSeemore;
        ImageView imvImage1, imvImage2, imvImage3, imvImage4;
        private View homeGroup;
        private View viewDividerTop;

        public MyViewHolder(View view) {
            super(view);
            tvTitle = (TextView) view.findViewById(R.id.tv_topic_title);
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
            tvSeemore = view.findViewById(R.id.tv_see_more);

            tvSeemore.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mIOnItemClickedListener != null) {
                        mIOnItemClickedListener.onItemClickComment(topic);
                    }
                }
            });
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
        holder.topic = menu.getTopic();
        holder.tvTitle.setText(menu.getTitle());
        holder.tvContent1.setText(menu.getData1().getTitle());
        holder.tvContent2.setText(menu.getData2().getTitle());
        holder.tvContent3.setText(menu.getData3().getTitle());
        holder.tvContent4.setText(menu.getData4().getTitle());
        Picasso.with(mContext).load(menu.getData1().getImage()).error(R.drawable.img_default).fit().into(holder.imvImage1);
        Picasso.with(mContext).load(menu.getData2().getImage()).error(R.drawable.img_default).fit().into(holder.imvImage2);
        Picasso.with(mContext).load(menu.getData3().getImage()).error(R.drawable.img_default).fit().into(holder.imvImage3);
        Picasso.with(mContext).load(menu.getData4().getImage()).error(R.drawable.img_default).fit().into(holder.imvImage4);

    }


    @Override
    public int getItemCount() {
        return mDataset.size();
    }


    public interface IOnItemClickedListener {
        void onItemClick(int id);

        void onItemClickComment(BlogPostTopic topic);

        void userSelectedAValue(String value);
    }
}
