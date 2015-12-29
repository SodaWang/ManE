package com.hshy41.mane.adapter.recyclerviewadapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hshy41.mane.R;

/**
 * 交易详情适配器
 * Created by lichao on 2015/12/23.
 */
public class TransactionDetails extends RecyclerView.Adapter<TransactionDetails.MyViewHolder> {

    private Context mContext;
    private OnItemClickLitener mOnItemClickLitener;

    /**
     * 初始化适配器，载入数据源
     *
     * @param context 上下文
     */
    public TransactionDetails(Context context) {
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
                LayoutInflater.from(mContext).inflate(R.layout.item_recycler_transaction_details, parent, false));
        return holder;
    }


    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 20;
    }

    class MyViewHolder extends ViewHolder {


        public MyViewHolder(View itemView) {
            super(itemView);

        }
    }
}
