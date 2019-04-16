package com.diendan.svdanang.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.diendan.svdanang.Eventitem;
import com.diendan.svdanang.Projectitem;
import com.diendan.svdanang.R;
import com.diendan.svdanang.models.ContentProject;
import com.diendan.svdanang.utils.Constants;
import com.squareup.picasso.Picasso;

import java.math.BigDecimal;
import java.util.List;

public class ProjectRecyclerviewAdapter extends  RecyclerView.Adapter<ProjectRecyclerviewAdapter.MyViewHolder>  {
    private List<ContentProject> mDataset;
    private Context mContext;

    public IOnItemClickedListener mIOnItemClickedListener;
    public void setOnItemClickListener(IOnItemClickedListener listener) {
        mIOnItemClickedListener = listener;
    }
    public static class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView imvImage;
        TextView tvTopic,tvTitle,tvSummary,tvRaised,tvGoal;
        LinearLayout mProgressBar;
        View vSpace;


        public MyViewHolder(View view) {
            super(view);
            imvImage = (ImageView) view.findViewById(R.id.imv_content_project);
            tvTitle = (TextView) view.findViewById(R.id.tv_title_project);
            tvTopic = (TextView) view.findViewById(R.id.tv_topic_project);
            tvSummary = (TextView) view.findViewById(R.id.tv_summary_project);
            tvRaised = (TextView) view.findViewById(R.id.tv_raised);
            tvGoal = (TextView) view.findViewById(R.id.tv_goal);
            mProgressBar = (LinearLayout) view.findViewById(R.id.progress_bar_cr);
            vSpace = (View) view.findViewById(R.id.view_space);
        }
    }
    public ProjectRecyclerviewAdapter(Context context, List<ContentProject> myDataset) {
        this.mDataset = myDataset;
        this.mContext = context;
    }

    public ProjectRecyclerviewAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ProjectRecyclerviewAdapter.MyViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.project_item_recyclerview, parent, false));
    }

    @Override
    public void onBindViewHolder(ProjectRecyclerviewAdapter.MyViewHolder holder, int position) {

        ContentProject item = mDataset.get(position);
        BigDecimal raised;
        if(item.getRaised()!= null) raised = item.getRaised();
        else raised = new BigDecimal(0);
        Picasso.with(mContext).load(item.getImage()).noPlaceholder().into(holder.imvImage);
        holder.tvTopic.setText(item.getProjectTopic().getName());
        holder.tvTitle.setText(item.getName());
        holder.tvSummary.setText(item.getShortDescription());
        holder.tvRaised.setText(String.valueOf(raised));
        holder.tvGoal.setText(String.valueOf(item.getGoal()));

        float factor = holder.itemView.getContext().getResources().getDisplayMetrics().density;
        ViewGroup.LayoutParams progresBarLayoutParams =  holder.mProgressBar.getLayoutParams();


        int divide =1;
        if(raised.compareTo(item.getGoal()) == 1 ) divide=1;
        else divide =(raised.divide(item.getGoal())).intValue();
        progresBarLayoutParams.width =  (int)(Constants.WITDH * divide *factor) ;
        Log.i("aaaaaaaa",(raised.divide(item.getGoal())).intValue()+"");
        holder.mProgressBar.setLayoutParams(progresBarLayoutParams);

        ViewGroup.LayoutParams viewSpacelayoutParams = holder.vSpace.getLayoutParams();
        viewSpacelayoutParams.width = (int)(Constants.WITDH * divide *factor);
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
