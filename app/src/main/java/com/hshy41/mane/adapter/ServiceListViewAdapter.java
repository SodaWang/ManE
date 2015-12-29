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

public class ServiceListViewAdapter extends BaseAdapter {

    private ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
    String[] str_price = {"￥43/人", "￥23/人", "￥43/人", "￥423/人", "￥43/人", "￥56/人", "￥33/人", "￥43/人", "￥56/人", "￥43/人"};
    String[] str_title = {"周边商户", "赚钱", "理财", "家政服务", "满e公益", "周边商户", "赚钱", "理财", "家政服务", "满e公益"};
    String[] str_text = {"反反复复反反复复反反复复吩咐", "斯蒂芬斯蒂芬斯蒂芬斯蒂芬森的", "斯蒂芬斯蒂芬斯蒂芬斯蒂芬森的", "威风威风威风威风威风威风威风", "哇福娃俄方哇俄方we",
            "反反复复反反复复反反复复吩咐", "斯蒂芬斯蒂芬斯蒂芬斯蒂芬森的", "斯蒂芬斯蒂芬斯蒂芬斯蒂芬森的", "威风威风威风威风威风威风威风", "哇福娃俄方哇俄方we"};
    int[] img = {R.drawable.listimg, R.drawable.listimg, R.drawable.listimg, R.drawable.listimg, R.drawable.listimg,
            R.drawable.listimg, R.drawable.listimg, R.drawable.listimg, R.drawable.listimg, R.drawable.listimg};
    String[] str_distance = {"45m", "23m", "23m", "45m", "12m", "45m", "234m", "23432m", "34m", "234m"};

    private Context mContext;

    public ServiceListViewAdapter(Context context) {
        mContext = context;
    }

//    private List<? extends Map<String, ?>> getdata() {
//        // TODO Auto-generated method stub
//        for (int i = 0; i < str_title.length; i++) {
//            HashMap<String, Object> map = new HashMap<String, Object>();
//            map.put("img", img[i]);
//            map.put("title", str_title[i]);
//            map.put("text", str_text[i]);
//            list.add(map);
//        }
//        return list;
//    }

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
            convertView = mInflater.inflate(R.layout.item_listview_servicelist, null);
            holder.iv_Img = (ImageView) convertView.findViewById(R.id.iv_item_servicelist_img);
            holder.tv_title = (TextView) convertView.findViewById(R.id.tv_item_servicelist_title);
            holder.tv_text = (TextView) convertView.findViewById(R.id.tv_item_servicelist_content);
            holder.tv_price = (TextView) convertView.findViewById(R.id.tv_item_servicelist_price);
            holder.tv_distance = (TextView) convertView.findViewById(R.id.tv_item_servicelist_distance);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.tv_title.setText(str_title[position]);
        holder.iv_Img.setBackgroundResource(img[position]);
        holder.tv_text.setText(str_text[position]);
        holder.tv_price.setText(str_price[position]);
        holder.tv_distance.setText(str_distance[position]);
        return convertView;
    }

    class ViewHolder {
        ImageView iv_Img;
        TextView tv_title;
        TextView tv_text;
        TextView tv_price;
        TextView tv_distance;
    }

}
