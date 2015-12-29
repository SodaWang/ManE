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
 * 物业通知配器
 * Created by lichao on 2015/12/22.
 */
public class PropertyNotification extends RecyclerView.Adapter<PropertyNotification.MyViewHolder> {

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
     */
    public PropertyNotification(Context context) {
        mContext = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(
                LayoutInflater.from(mContext).inflate(R.layout.item_recycler_property_notification, parent, false));
        return holder;
    }


    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 10;
    }

    class MyViewHolder extends ViewHolder {


        public MyViewHolder(View itemView) {
            super(itemView);

        }
    }
}
