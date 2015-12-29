package com.hshy41.mane.adapter.recyclerviewadapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.widget.TextView;

import com.hshy41.mane.R;

import java.util.List;

/**
 * Created by lichao on 2015/12/20.
 */
public class ModuleDesign extends RecyclerView.Adapter<ModuleDesign.MyViewHolder> {
    private int [] mData;
    private Context mContext;

    /**
     * 初始化适配器，载入数据源
     *
     * @param context
     *            上下文
     * @param data
     *            数据源
     */
    public ModuleDesign(Context context, int[] data) {
        mData = data;
        mContext = context;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(
                LayoutInflater.from(mContext).inflate(R.layout.item_recycler_moduledesign, parent, false));
        return holder;
    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.tv_item_moduledesign_text.setText(mData[position]);
    }

    @Override
    public int getItemCount() {
        return mData.length;
    }

    class MyViewHolder extends ViewHolder {

        TextView tv_item_moduledesign_text;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv_item_moduledesign_text = (TextView) itemView.findViewById(R.id.tv_item_moduledesign_text);
        }
    }
}
