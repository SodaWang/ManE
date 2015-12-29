package com.hshy41.mane.adapter.recyclerviewadapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hshy41.mane.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 家政服务配器
 * Created by lichao on 2015/12/22.
 */
public class HouseKeeping extends RecyclerView.Adapter<HouseKeeping.MyViewHolder> {
    private ArrayList<HashMap<String, Object>> mData;
    private Context mContext;
    private OnItemClickLitener mOnItemClickLitener;

    public interface OnItemClickLitener {
        void onItemClick(View view, int position);
    }

    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener) {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }

    /**
     * 初始化适配器，载入数据源
     *
     * @param context 上下文
     * @param data    数据源
     */
    public HouseKeeping(Context context, ArrayList<HashMap<String, Object>> data) {
        mData = data;
        mContext = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(
                LayoutInflater.from(mContext).inflate(R.layout.item_recycler_housekeeping, parent, false));
        return holder;
    }


    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        holder.iv_item_housekeeping_icon.setBackgroundDrawable((Drawable) mData.get(position).get("icon"));
        holder.tv_item_housekeeping_title.setText((String) mData.get(position).get("title"));
        holder.tv_item_housekeeping_content.setText((String) mData.get(position).get("content"));


        if (mOnItemClickLitener != null) {
            holder.rl_item_layout_housekeeping.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = holder.getLayoutPosition();
                    mOnItemClickLitener.onItemClick(holder.rl_item_layout_housekeeping, pos);
                }
            });

        }
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    class MyViewHolder extends ViewHolder {

        TextView tv_item_housekeeping_title;
        TextView tv_item_housekeeping_content;
        ImageView iv_item_housekeeping_icon;
        RelativeLayout rl_item_layout_housekeeping;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv_item_housekeeping_title = (TextView) itemView.findViewById(R.id.tv_item_housekeeping_title);
            tv_item_housekeeping_content = (TextView) itemView.findViewById(R.id.tv_item_housekeeping_content);
            iv_item_housekeeping_icon = (ImageView) itemView.findViewById(R.id.iv_item_housekeeping_icon);
            rl_item_layout_housekeeping = (RelativeLayout) itemView.findViewById(R.id.rl_item_layout_housekeeping);
        }
    }
}
