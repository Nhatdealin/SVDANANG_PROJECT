package com.diendan.svdanang.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.diendan.svdanang.Eventitem;
import com.diendan.svdanang.Projectitem;
import com.diendan.svdanang.R;
import com.diendan.svdanang.utils.Constants;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ProjectRecyclerviewAdapter extends  RecyclerView.Adapter<ProjectRecyclerviewAdapter.MyViewHolder>  {
    private List<Projectitem> mDataset;
    private Context mContext;

    public IOnItemClickedListener mIOnItemClickedListener;
    public void setOnItemClickListener(IOnItemClickedListener listener) {
        mIOnItemClickedListener = listener;
    }
    public static class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView imvImage;
        TextView tvTopic,tvTitle,tvSummary,tvRaised;
        LinearLayout mProgressBar;
        View vSpace;


        public MyViewHolder(View view) {
            super(view);
            imvImage = (ImageView) view.findViewById(R.id.imv_content_project);
            tvTitle = (TextView) view.findViewById(R.id.tv_title_project);
            tvTopic = (TextView) view.findViewById(R.id.tv_topic_project);
            tvSummary = (TextView) view.findViewById(R.id.tv_summary_project);
            tvRaised = (TextView) view.findViewById(R.id.tv_raised);
            mProgressBar = (LinearLayout) view.findViewById(R.id.progress_bar_cr);
            vSpace = (View) view.findViewById(R.id.view_space);
        }
    }
    public ProjectRecyclerviewAdapter(Context context, List<Projectitem> myDataset) {
        this.mDataset = myDataset;
        this.mContext = context;
    }

    public ProjectRecyclerviewAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ProjectRecyclerviewAdapter.MyViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.project_item_recyclerview, parent, false));
    }

    @Override
    public void onBindViewHolder(ProjectRecyclerviewAdapter.MyViewHolder holder, int position) {
        Projectitem projectitem = mDataset.get(position);
        Picasso.with(mContext).load(projectitem.getIdImage()).noPlaceholder().into(holder.imvImage);
        holder.imvImage.setImageResource(projectitem.getIdImage());
        holder.tvTopic.setText(projectitem.getTopic());
        holder.tvTitle.setText(projectitem.getTitle());
        holder.tvSummary.setText(projectitem.getSummary());
        holder.tvRaised.setText(String.valueOf(projectitem.getRaised()));

        ViewGroup.LayoutParams progresBarLayoutParams =  holder.mProgressBar.getLayoutParams();
        progresBarLayoutParams.width = Constants.WITDH *projectitem.getRaised()/projectitem.getGoal();
        holder.mProgressBar.setLayoutParams(progresBarLayoutParams);

        ViewGroup.LayoutParams viewSpacelayoutParams = holder.vSpace.getLayoutParams();
        viewSpacelayoutParams.width = Constants.WITDH*projectitem.getRaised()/projectitem.getGoal();
        holder.vSpace.setLayoutParams(viewSpacelayoutParams);

    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }


    public interface IOnItemClickedListener {
        void onItemClick(int id);
        void onItemClickComment(int id);
        void userSelectedAValue(String value);
    }
}
