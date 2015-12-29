package com.hshy41.mane.adapter.recyclerviewadapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.hshy41.mane.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 旧货列表适配器
 * Created by lichao on 2015/12/20.
 */
public class VintageList extends RecyclerView.Adapter<VintageList.MyViewHolder> {
    private Context mContext;
    private OnItemClickLitener mOnItemClickLitener;


    /**
     * 初始化适配器
     *
     * @param context 上下文
     */
    public VintageList(Context context) {
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
                LayoutInflater.from(mContext).inflate(R.layout.item_recycler_vintage_list, parent, false));

        return holder;
    }


    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {

//        if (mOnItemClickLitener != null) {
//            holder.ll_item_tour.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    int pos = holder.getLayoutPosition();
//                    mOnItemClickLitener.onItemClick(holder.ll_item_tour, pos);
//                }
//            });
//            holder.rb_item_tour.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
//                @Override
//                public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
//                    holder.tv_item_tour_rating.setText(String.valueOf(holder.rb_item_tour.getRating()));
//                }
//            });
//        }
    }

    @Override
    public int getItemCount() {
        return 20;
    }

    class MyViewHolder extends ViewHolder {
        ;

        public MyViewHolder(View itemView) {
            super(itemView);

        }
    }
}
