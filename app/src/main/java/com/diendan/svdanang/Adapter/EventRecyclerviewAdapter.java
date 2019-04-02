package com.diendan.svdanang.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.diendan.svdanang.Eventitem;
import com.diendan.svdanang.R;
import com.diendan.svdanang.Seemoreitem;
import com.squareup.picasso.Picasso;

import java.util.List;

public class EventRecyclerviewAdapter extends  RecyclerView.Adapter<EventRecyclerviewAdapter.MyViewHolder>  {
    private List<Eventitem> mDataset;
    private Context mContext;

    public IOnItemClickedListener mIOnItemClickedListener;
    public void setOnItemClickListener(IOnItemClickedListener listener) {
        mIOnItemClickedListener = listener;
    }
    public static class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView imvImage;
        TextView tvTopic,tvTitle,tvSummary,tvLocation,tvFee;


        public MyViewHolder(View view) {
            super(view);
            imvImage = (ImageView) view.findViewById(R.id.imv_content_event);
            tvTitle = (TextView) view.findViewById(R.id.tv_title_event);
            tvTopic = (TextView) view.findViewById(R.id.tv_topic_event);
            tvSummary = (TextView) view.findViewById(R.id.tv_summary_event);
            tvFee = (TextView) view.findViewById(R.id.tv_fee_event);
            tvLocation = (TextView) view.findViewById(R.id.tv_location_event);

        }
    }
    public EventRecyclerviewAdapter(Context context, List<Eventitem> myDataset) {
        this.mDataset = myDataset;
        this.mContext = context;
    }

    public EventRecyclerviewAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new EventRecyclerviewAdapter.MyViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.event_item_recyclerview, parent, false));
    }

    @Override
    public void onBindViewHolder(EventRecyclerviewAdapter.MyViewHolder holder, int position) {
        Eventitem eventitem = mDataset.get(position);
        Picasso.with(mContext).load(eventitem.getIdImage()).fit().into(holder.imvImage);
        holder.imvImage.setImageResource(eventitem.getIdImage());
        holder.tvTopic.setText(eventitem.getTopic());
        holder.tvTitle.setText(eventitem.getTitle());
        holder.tvSummary.setText(eventitem.getSummary());
        holder.tvLocation.setText(eventitem.getLocation());
        holder.tvFee.setText(String.valueOf(eventitem.getFee()));
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
