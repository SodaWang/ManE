package com.hshy41.mane.adapter.recyclerviewadapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.widget.RelativeLayout;

import com.hshy41.mane.R;

/**
 * 社区适配器
 * Created by lichao on 2015/12/21.
 */
public class Community extends RecyclerView.Adapter<Community.MyViewHolder> {

    private Context mContext;
    private OnItemClickLitener mOnItemClickLitener;
    private final int TYPE_NORMAL = 1;
    private final int TYPE_FOOTER = 2;

    /**
     * 初始化适配器，载入数据源
     *
     * @param context 上下文
     */
    public Community(Context context) {
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
        MyViewHolder holder;
        if (viewType == TYPE_NORMAL) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_community, parent, false);
            holder = new MyViewHolder(view, TYPE_NORMAL);
            return holder;
        }
        if (viewType == TYPE_FOOTER) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_community_add, parent, false);
            holder = new MyViewHolder(view, TYPE_FOOTER);
            return holder;
        }
        return null;
    }


    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        if (mOnItemClickLitener != null) {
            if (position <= 8) {
                holder.rl_community.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int pos = holder.getLayoutPosition();
                        mOnItemClickLitener.onItemClick(holder.rl_community, pos);
                    }
                });
            } else {
                holder.rl_community_add.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int pos = holder.getLayoutPosition();
                        mOnItemClickLitener.onItemClick(holder.rl_community_add, pos);
                    }
                });
            }
        }
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 9) {
            return TYPE_FOOTER;
        } else {
            return TYPE_NORMAL;
        }
    }

    class MyViewHolder extends ViewHolder {

        RelativeLayout rl_community, rl_community_add;
        public int viewType;

        public MyViewHolder(View itemView, int viewType) {
            super(itemView);
            this.viewType = viewType;
            if (viewType == TYPE_NORMAL) {
                //normal
                rl_community = (RelativeLayout) itemView.findViewById(R.id.rl_item_community);
            }
            if (viewType == TYPE_FOOTER) {
                //footer
                rl_community_add = (RelativeLayout) itemView.findViewById(R.id.rl_item_community_add);
            }

        }
    }
}
