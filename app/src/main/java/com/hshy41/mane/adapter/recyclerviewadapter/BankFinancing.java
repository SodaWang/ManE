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

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 银行理财适配器
 * Created by lichao on 2015/12/21.
 */
public class BankFinancing extends RecyclerView.Adapter<BankFinancing.MyViewHolder> {
    private ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
    String[] str_title = {"周边商户", "赚钱", "理财", "家政服务", "满e公益", "周边商户", "赚钱", "理财", "家政服务", "满e公益"};
    String[] str_text = {"反反复复反反复复反反复复吩咐", "斯蒂芬斯蒂芬斯蒂芬斯蒂芬森的", "斯蒂芬斯蒂芬斯蒂芬斯蒂芬森的", "威风威风威风威风威风威风威风", "哇福娃俄方哇俄方we",
            "反反复复反反复复反反复复吩咐", "斯蒂芬斯蒂芬斯蒂芬斯蒂芬森的", "斯蒂芬斯蒂芬斯蒂芬斯蒂芬森的", "威风威风威风威风威风威风威风", "哇福娃俄方哇俄方we"};
    int[] img = {R.drawable.listimg, R.drawable.listimg, R.drawable.listimg, R.drawable.listimg, R.drawable.listimg,
            R.drawable.listimg, R.drawable.listimg, R.drawable.listimg, R.drawable.listimg, R.drawable.listimg};
    private Context mContext;
    private OnItemClickLitener mOnItemClickLitener;

    /**
     * 初始化适配器，载入数据源
     *
     * @param context 上下文
     */
    public BankFinancing(Context context) {
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
        holder.iv_item_bankfinancing_img.setBackgroundResource(img[position]);
        holder.tv_item_bankfinancing_content.setText(str_text[position]);
        holder.tv_item_bankfinancing_title.setText(str_title[position]);

        if (mOnItemClickLitener != null) {
            holder.rl_item_bankfinancing.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = holder.getLayoutPosition();
                    mOnItemClickLitener.onItemClick(holder.rl_item_bankfinancing, pos);
                }
            });

        }
    }

    @Override
    public int getItemCount() {
        return str_title.length;
    }

    class MyViewHolder extends ViewHolder {

        ImageView iv_item_bankfinancing_img;
        TextView tv_item_bankfinancing_title;
        TextView tv_item_bankfinancing_content;
        RelativeLayout rl_item_bankfinancing;

        public MyViewHolder(View itemView) {
            super(itemView);
            rl_item_bankfinancing = (RelativeLayout) itemView.findViewById(R.id.rl_item_bankfinancing);
            iv_item_bankfinancing_img = (ImageView) itemView.findViewById(R.id.iv_item_bankfinancing_img);
            tv_item_bankfinancing_content = (TextView) itemView.findViewById(R.id.tv_item_bankfinancing_content);
            tv_item_bankfinancing_title = (TextView) itemView.findViewById(R.id.tv_item_bankfinancing_title);
        }
    }
}
