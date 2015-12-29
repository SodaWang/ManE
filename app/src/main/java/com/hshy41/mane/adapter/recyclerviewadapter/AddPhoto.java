package com.hshy41.mane.adapter.recyclerviewadapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Handler;
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
 * 添加照片适配器
 * Created by lichao on 2015/12/21.
 */
public class AddPhoto extends RecyclerView.Adapter<AddPhoto.MyViewHolder> {

    public List<Bitmap> mData;
    private Context mContext;
    private OnItemClickLitener mOnItemClickLitener;

    /**
     * 初始化适配器，载入数据源
     *
     * @param context 上下文
     * @param data    数据源
     */
    public AddPhoto(Context context, List<Bitmap> data) {
        mData = data;
        mContext = context;
    }

    public interface OnItemClickLitener {
        void onItemClick(View view, int position);

        void onItemLongClick(View view, int position);
    }

    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener) {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(
                LayoutInflater.from(mContext).inflate(R.layout.item_recycler_add_photo, parent, false));
        return holder;
    }


    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        holder.iv_photo.setImageBitmap(mData.get(position));

        if (mOnItemClickLitener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = holder.getLayoutPosition();
                    mOnItemClickLitener.onItemClick(holder.itemView, pos);
                }
            });

            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    int pos = holder.getLayoutPosition();
                    mOnItemClickLitener.onItemLongClick(holder.itemView, pos);
                    return false;
                }
            });

        }
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    class MyViewHolder extends ViewHolder {

        ImageView iv_photo;

        public MyViewHolder(View itemView) {
            super(itemView);
            iv_photo = (ImageView) itemView.findViewById(R.id.iv_item_photo);
        }
    }

    //添加动画
    public void addData(Bitmap bm, int position) {
        mData.add(position, bm);
        notifyItemInserted(position);
    }

    //删除动画
    public void removeData(int position) {
        mData.remove(position);
        notifyItemRemoved(position);
    }
}
