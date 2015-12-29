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
 * Created by lichao on 2015/12/20.
 */
public class GetMonney extends RecyclerView.Adapter<GetMonney.MyViewHolder> {
    private List<Drawable> list_data;
    private Context mContext;
    private OnItemClickLitener mOnItemClickLitener;


    /**
     * 初始化适配器，载入数据源
     *
     * @param context 上下文
     * @param data    数据源
     */
    public GetMonney(Context context, List<Drawable> data) {
        list_data = data;
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
                LayoutInflater.from(mContext).inflate(R.layout.item_recycler_getmonney, parent, false));
        return holder;
    }


    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        holder.iv_item_getmonney_ad.setBackgroundDrawable(list_data.get(position));
        if (mOnItemClickLitener != null) {
            holder.rl_item_layout_getmonney_title.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = holder.getLayoutPosition();
                    mOnItemClickLitener.onItemClick(holder.rl_item_layout_getmonney_title, pos);
                }
            });

        }


    }

    @Override
    public int getItemCount() {
        return list_data.size();
    }

    class MyViewHolder extends ViewHolder {

        ImageView iv_item_getmonney_ad;
        RelativeLayout rl_item_layout_getmonney_title;

        public MyViewHolder(View itemView) {
            super(itemView);
            iv_item_getmonney_ad = (ImageView) itemView.findViewById(R.id.iv_item_getmonney_adimg);
            rl_item_layout_getmonney_title = (RelativeLayout) itemView.findViewById(R.id.rl_item_layout_getmonney_title);
        }
    }
}
