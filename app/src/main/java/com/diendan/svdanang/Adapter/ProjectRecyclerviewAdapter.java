package com.diendan.svdanang.Adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.diendan.svdanang.Eventitem;
import com.diendan.svdanang.Projectitem;
import com.diendan.svdanang.R;
import com.diendan.svdanang.models.ContentProject;
import com.diendan.svdanang.utils.Constants;
import com.squareup.picasso.Picasso;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

public class ProjectRecyclerviewAdapter extends RecyclerView.Adapter<ProjectRecyclerviewAdapter.MyViewHolder> {
    private List<ContentProject> mDataset;
    private Context mContext;
    public IOnItemClickedListener mIOnItemClickedListener;

    public void setOnItemClickListener(IOnItemClickedListener listener) {
        mIOnItemClickedListener = listener;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView imvImage;
        TextView tvTopic, tvTitle, tvSummary, tvRaised, tvGoal, tvCurrency1, tvCurrency2, tvStartTime, tvEndTime,tvDonator;
        LinearLayout mProgressBar;
        Button btnDetail,btnDonate;

        View vSpace;

        public MyViewHolder(View view) {
            super(view);
            imvImage = (ImageView) view.findViewById(R.id.imv_content_project);
            tvTitle = (TextView) view.findViewById(R.id.tv_title_project);
            tvTopic = (TextView) view.findViewById(R.id.tv_topic_project);
            tvSummary = (TextView) view.findViewById(R.id.tv_summary_project);
            tvRaised = (TextView) view.findViewById(R.id.tv_raised);
            tvGoal = (TextView) view.findViewById(R.id.tv_goal);
            tvCurrency1 = (TextView) view.findViewById(R.id.tv_currency1_project);
            tvCurrency2 = (TextView) view.findViewById(R.id.tv_currency2_project);
            tvStartTime = (TextView) view.findViewById(R.id.tv_starttime_project);
            tvEndTime = (TextView) view.findViewById(R.id.tv_endtime_project);
            tvDonator = (TextView) view.findViewById(R.id.tv_donator_project);
            btnDetail = (Button) view.findViewById(R.id.btn_detail_project);
            btnDonate = (Button) view.findViewById(R.id.btn_donate_project);
            mProgressBar = (LinearLayout) view.findViewById(R.id.progress_bar_cr);
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
    public void onBindViewHolder(ProjectRecyclerviewAdapter.MyViewHolder viewHolder, int position) {

        final ContentProject item = mDataset.get(position);
        BigDecimal raised;
        if (item.getRaised() != null) raised = (BigDecimal) item.getRaised().divide(new BigDecimal(1),2,RoundingMode.CEILING);
        else raised = new BigDecimal(0);
        Picasso.with(mContext).load(item.getImage()).fit().centerCrop().noPlaceholder().into(viewHolder.imvImage);
        if(item.getProjectTopic().getName() != null)
        {
            viewHolder.tvTopic.setText(item.getProjectTopic().getName());
        }

        viewHolder.tvTitle.setText(item.getName());
        viewHolder.tvSummary.setText(item.getShortDescription());
        viewHolder.tvRaised.setText(String.valueOf(raised));
        viewHolder.tvGoal.setText(String.valueOf(item.getGoal()));
        viewHolder.tvCurrency1.setText(item.getCurrency().getName());
        viewHolder.tvCurrency2.setText(item.getCurrency().getName());
        viewHolder.tvDonator.setText(item.getNumOfDonators().toString());
        viewHolder.tvStartTime.setText( new SimpleDateFormat("dd/MM/yyyy").format(new Date(item.getStartTime())));
        viewHolder.tvEndTime.setText(new SimpleDateFormat("dd/MM/yyyy").format(new Date(item.getEndTime())));
        float factor = viewHolder.itemView.getContext().getResources().getDisplayMetrics().density;
        ViewGroup.LayoutParams progresBarLayoutParams = viewHolder.mProgressBar.getLayoutParams();
        float divide = 1;
        if (raised.compareTo(item.getGoal()) == 1) divide = 1;
        else divide = (raised.divide(item.getGoal(), 3,RoundingMode.CEILING).floatValue());
        progresBarLayoutParams.width = (int) (Constants.WITDH * divide * factor);
        viewHolder.mProgressBar.setLayoutParams(progresBarLayoutParams);
        viewHolder.btnDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mIOnItemClickedListener != null) {
                    mIOnItemClickedListener.onItemClickComment(item.getId(),false);
                }
            }
        });
        viewHolder.btnDonate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mIOnItemClickedListener != null) {
                    mIOnItemClickedListener.onItemClickComment(item.getId(),true);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public interface IOnItemClickedListener {
        void onItemClick(int id);

        void onItemClickComment(Long id,boolean isDonating);

        void userSelectedAValue(String value);
    }
}
