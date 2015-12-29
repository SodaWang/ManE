package com.hshy41.mane.adapter.recyclerviewadapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.widget.TextView;

import com.hshy41.mane.R;

/**
 * Created by lichao on 2015/12/20.
 */
public class Search extends RecyclerView.Adapter<Search.MyViewHolder> {

    private static final int IS_HEADER = 2;
    private static final int IS_FOOTER = 3;
    private static final int IS_NORMAL = 1;

    /**
     * 初始化适配器，载入数据源
     *
     * @param mContext
     * 上下文
     * @param mData
     * 数据源
     */
    private int[] mData;
    private Context mContext;

    public Search(Context context, int[] data) {
        mData = data;
        mContext = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        MyViewHolder holder;
        if (viewType == IS_NORMAL) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_search, parent, false);
            holder = new MyViewHolder(view, IS_NORMAL);
            return holder;
        }
        if (viewType == IS_FOOTER) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_cleansearch, parent, false);
            holder = new MyViewHolder(view, IS_FOOTER);
            return holder;
        }
        return null;
    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        if (position != 0 && position != mData.length && holder.viewType == IS_NORMAL) {
            //normal
            holder.tv_item_serach_text.setText(mData[position]);
        }
        if (position == mData.length && holder.viewType == IS_FOOTER) {
            //footer
        }
    }

    @Override
    public int getItemCount() {
        return mData.length;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == mData.length - 1) {
            return IS_FOOTER;
        } else {
            return IS_NORMAL;
        }
    }

    class MyViewHolder extends ViewHolder {

        TextView tv_item_serach_text;
        public int viewType;

        public MyViewHolder(View itemView, int viewType) {
            super(itemView);
            this.viewType = viewType;
            if (viewType == IS_NORMAL) {
                tv_item_serach_text = (TextView) itemView.findViewById(R.id.tv_item_search_text);
            }

        }
    }
}
