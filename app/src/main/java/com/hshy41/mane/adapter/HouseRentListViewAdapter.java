package com.hshy41.mane.adapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.hshy41.mane.R;

public class HouseRentListViewAdapter extends BaseAdapter {

    private ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
    String[] str_title = {"周边商户", "赚钱", "理财", "家政服务", "满e公益", "周边商户", "赚钱", "理财", "家政服务", "满e公益"};
    String[] str_text = {"反反复复反反复复反反复复吩咐", "斯蒂芬斯蒂芬斯蒂芬斯蒂芬森的", "斯蒂芬斯蒂芬斯蒂芬斯蒂芬森的", "威风威风威风威风威风威风威风", "哇福娃俄方哇俄方we",
            "反反复复反反复复反反复复吩咐", "斯蒂芬斯蒂芬斯蒂芬斯蒂芬森的", "斯蒂芬斯蒂芬斯蒂芬斯蒂芬森的", "威风威风威风威风威风威风威风", "哇福娃俄方哇俄方we"};
    int[] img = {R.drawable.listimg, R.drawable.listimg, R.drawable.listimg, R.drawable.listimg, R.drawable.listimg,
            R.drawable.listimg, R.drawable.listimg, R.drawable.listimg, R.drawable.listimg, R.drawable.listimg};
    String[] str_price = {"￥6600/月", "￥6600/月", "￥6600/月", "￥6600/月", "￥6600/月",
            "￥6600/月", "￥6600/月", "￥6600/月", "￥6600/月", "￥6600/月"};
    private Context mContext;

    public HouseRentListViewAdapter(Context context) {
        mContext = context;
    }

    private List<? extends Map<String, ?>> getdata() {
        // TODO Auto-generated method stub
        for (int i = 0; i < str_title.length; i++) {
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("img", img[i]);
            map.put("title", str_title[i]);
            map.put("text", str_text[i]);
            map.put("price", str_price[i]);
            list.add(map);
        }
        return list;
    }

    @Override
    public int getCount() {
        return img.length;
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            LayoutInflater mInflater = LayoutInflater.from(mContext);
            convertView = mInflater.inflate(R.layout.item_listview_houserent, null);
            holder.iv_Img = (ImageView) convertView.findViewById(R.id.iv_item_houserent_img);
            holder.tv_title = (TextView) convertView.findViewById(R.id.tv_item_houserent_title);
            holder.tv_text = (TextView) convertView.findViewById(R.id.tv_item_houserent_content);
            holder.tv_price = (TextView) convertView.findViewById(R.id.tv_item_houserent_price);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.tv_title.setText(str_title[position]);
        holder.iv_Img.setBackgroundResource(img[position]);
        holder.tv_text.setText(str_text[position]);
        holder.tv_price.setText(str_price[position]);
        return convertView;
    }

    class ViewHolder {
        ImageView iv_Img;
        TextView tv_title;
        TextView tv_text;
        TextView tv_price;
    }

}
