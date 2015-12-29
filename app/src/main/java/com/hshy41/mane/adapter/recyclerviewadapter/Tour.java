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
 * 周边旅游适配器
 * Created by lichao on 2015/12/20.
 */
public class Tour extends RecyclerView.Adapter<Tour.MyViewHolder> {
    private int[] mData;
    private Context mContext;
    private OnItemClickLitener mOnItemClickLitener;


    /**
     * 初始化适配器
     *
     * @param context 上下文
     */
    public Tour(Context context) {
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
                LayoutInflater.from(mContext).inflate(R.layout.item_recycler_tour, parent, false));

        return holder;
    }


    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {

        if (mOnItemClickLitener != null) {
            holder.ll_item_tour.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = holder.getLayoutPosition();
                    mOnItemClickLitener.onItemClick(holder.ll_item_tour, pos);
                }
            });
            holder.rb_item_tour.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
                @Override
                public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                    holder.tv_item_tour_rating.setText(String.valueOf(holder.rb_item_tour.getRating()));
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    class MyViewHolder extends ViewHolder {

        RatingBar rb_item_tour;
        TextView tv_item_tour_rating;
        LinearLayout ll_item_tour;

        public MyViewHolder(View itemView) {
            super(itemView);
            ll_item_tour = (LinearLayout) itemView.findViewById(R.id.ll_item_tour);
            rb_item_tour = (RatingBar) itemView.findViewById(R.id.rb_item_tour);
            tv_item_tour_rating = (TextView) itemView.findViewById(R.id.tv_item_tour_rating);
            tv_item_tour_rating.setText(String.valueOf(rb_item_tour.getRating()));
        }
    }
}
