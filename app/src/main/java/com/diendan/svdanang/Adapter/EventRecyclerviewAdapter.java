package com.diendan.svdanang.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.diendan.svdanang.Eventitem;
import com.diendan.svdanang.R;
import com.diendan.svdanang.Seemoreitem;
import com.diendan.svdanang.models.ContentEvent;
import com.squareup.picasso.Picasso;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

public class EventRecyclerviewAdapter extends  RecyclerView.Adapter<EventRecyclerviewAdapter.MyViewHolder>  {
    private List<ContentEvent> mDataset;
    private Context mContext;

    public IOnItemClickedListener mIOnItemClickedListener;
    public void setOnItemClickListener(IOnItemClickedListener listener) {
        mIOnItemClickedListener = listener;
    }
    public static class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView imvImage;
        TextView tvTopic,tvTitle,tvSummary,tvLocation,tvFee,tvCurrency,tvStartTime,tvEndTime;
        Button btnDetail,btnRegister;


        public MyViewHolder(View view) {
            super(view);
            imvImage = (ImageView) view.findViewById(R.id.imv_content_event);
            tvTitle = (TextView) view.findViewById(R.id.tv_title_event);
            tvTopic = (TextView) view.findViewById(R.id.tv_topic_event);
            tvSummary = (TextView) view.findViewById(R.id.tv_summary_event);
            tvFee = (TextView) view.findViewById(R.id.tv_fee_event);
            tvLocation = (TextView) view.findViewById(R.id.tv_location_event);
            tvCurrency = (TextView) view.findViewById(R.id.tv_currency_event);
            tvStartTime = (TextView) view.findViewById(R.id.tv_starttime_event);
            tvEndTime = (TextView) view.findViewById(R.id.tv_endtime_event);
            btnDetail = (Button) view.findViewById(R.id.btn_information_event);
            btnRegister = (Button) view.findViewById(R.id.btn_register_event);


        }
    }
    public EventRecyclerviewAdapter(Context context, List<ContentEvent> myDataset) {
        this.mDataset = myDataset;
        this.mContext = context;
    }

    public EventRecyclerviewAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new EventRecyclerviewAdapter.MyViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.event_item_recyclerview, parent, false));
    }

    @Override
    public void onBindViewHolder(EventRecyclerviewAdapter.MyViewHolder holder, int position) {
        final ContentEvent eventitem = mDataset.get(position);
        if(eventitem.getImage() != null)
        {
            Picasso.with(mContext).load(eventitem.getImage()).noPlaceholder().fit().centerCrop().into(holder.imvImage);
        }
        if(eventitem.getName() != null)
        {
            holder.tvTitle.setText(eventitem.getName());
        }
        if(eventitem.getShortDescription() != null)
        {
            holder.tvSummary.setText(eventitem.getShortDescription());
        }
        if(eventitem.getEventTopic() != null)
        {
            holder.tvTopic.setText(eventitem.getEventTopic().getName());
        }
        if(eventitem.getLocation() != null)
        {
            holder.tvLocation.setText(eventitem.getLocation());
        }if(eventitem.getFee() != null)
        {
            holder.tvFee.setText(String.valueOf(eventitem.getFee()));
        }if(eventitem.getCurrency() != null)
        {
            holder.tvCurrency.setText(eventitem.getCurrency().getName());
        }if(eventitem.getStartTime() != null)
        {
            holder.tvStartTime.setText(new SimpleDateFormat("dd/MM/yyyy").format(new Date(eventitem.getStartTime())));
        }if(eventitem.getEndTime() != null)
        {
            holder.tvEndTime.setText(new SimpleDateFormat("dd/MM/yyyy").format(new Date(eventitem.getEndTime())));
        }

        holder.btnDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mIOnItemClickedListener != null) {
                    mIOnItemClickedListener.onItemClickComment(eventitem.getId(),false);
                }
            }
        });
        holder.btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mIOnItemClickedListener != null) {
                    mIOnItemClickedListener.onItemClickComment(eventitem.getId(),true);
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
        void onItemClickComment(Long id,boolean isRegister);
        void userSelectedAValue(String value);
    }
}
