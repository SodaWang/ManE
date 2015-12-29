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

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 银行理财适配器
 * Created by lichao on 2015/12/21.
 */
public class AD extends RecyclerView.Adapter<AD.MyViewHolder> {

    private Context mContext;
    private OnItemClickLitener mOnItemClickLitener;

    /**
     * 初始化适配器，载入数据源
     *
     * @param context 上下文
     */
    public AD(Context context) {
        mContext = context;
    }

    public interface OnItemClickLitener {
        void onItemClick(View view, int position);
    }

    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener) {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(
                LayoutInflater.from(mContext).inflate(R.layout.item_recycler_ad, parent, false));
        return holder;
    }


    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        if (mOnItemClickLitener != null) {
            holder.rl_item_layout_ad.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = holder.getLayoutPosition();
                    mOnItemClickLitener.onItemClick(holder.rl_item_layout_ad, pos);
                }
            });

        }
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    class MyViewHolder extends ViewHolder {

        RelativeLayout rl_item_layout_ad;

        public MyViewHolder(View itemView) {
            super(itemView);
            rl_item_layout_ad = (RelativeLayout) itemView.findViewById(R.id.rl_item_layout_ad);

        }
    }
}
