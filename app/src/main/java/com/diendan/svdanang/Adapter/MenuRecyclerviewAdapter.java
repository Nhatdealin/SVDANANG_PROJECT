package com.diendan.svdanang.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.diendan.svdanang.Menuitem;
import com.diendan.svdanang.R;

import java.util.List;

public class MenuRecyclerviewAdapter extends RecyclerView.Adapter<MenuRecyclerviewAdapter.MyViewHolder> {
    private List<Menuitem> mDataset;
    private Context mContext;

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvName;
        ImageView imvImage;
        private View menuGroup;
        private View viewDividerTop;

        public MyViewHolder(View view) {
            super(view);
            tvName = (TextView) view.findViewById(R.id.tv_item);
            tvName.setSelected(true);
            imvImage = (ImageView) view.findViewById(R.id.imv_item);
            menuGroup = view.findViewById(R.id.menu_group);
            viewDividerTop = view.findViewById(R.id.view_divider_top);
        }
    }

    public MenuRecyclerviewAdapter(Context context, List<Menuitem> myDataset) {
        this.mDataset = myDataset;
        this.mContext = context;
    }

    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_recycleview, parent, false));
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Menuitem menu = mDataset.get(position);
        holder.tvName.setText(menu.getData());
        holder.imvImage.setImageResource(menu.getIdImage());
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }


}
