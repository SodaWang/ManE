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

import com.hshy41.mane.R;

import java.util.List;

/**
 * 我的收藏适配器
 * Created by lichao on 2015/12/21.
 */
public class Collect extends RecyclerView.Adapter<Collect.MyViewHolder> {
    private List<Drawable> mData;
    private Context mContext;
    private OnItemClickLitener mOnItemClickLitener;

    /**
     * 初始化适配器，载入数据源
     *
     * @param context 上下文
     * @param data    数据源
     */
    public Collect(Context context, List<Drawable> data) {
        mData = data;
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
                LayoutInflater.from(mContext).inflate(R.layout.item_recycler_bankfinancing, parent, false));
        return holder;
    }


    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
//        holder.rl_item_bankfinancing.setBackgroundDrawable(mData.get(position));
//        if (mOnItemClickLitener != null) {
//            holder.rl_item_bankfinancing.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    int pos = holder.getLayoutPosition();
//                    mOnItemClickLitener.onItemClick(holder.rl_item_bankfinancing, pos);
//                }
//            });
//
//        }
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    class MyViewHolder extends ViewHolder {

        RelativeLayout rl_item_bankfinancing;

        public MyViewHolder(View itemView) {
            super(itemView);
//            rl_item_bankfinancing = (RelativeLayout) itemView.findViewById(R.id.rl_item_bankfinancing);
        }
    }
}
