package com.hshy41.mane.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.hshy41.mane.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RightPopListViewAdapter extends BaseAdapter {

    private ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
    String[] str_title = {"周边商户", "赚钱", "理财"};

    private Context mContext;

    public RightPopListViewAdapter(Context context) {
        mContext = context;
    }

    private List<? extends Map<String, ?>> getdata() {
        // TODO Auto-generated method stub
        for (int i = 0; i < str_title.length; i++) {
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("title", str_title[i]);
            list.add(map);
        }
        return list;
    }

    @Override
    public int getCount() {
        return str_title.length;
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
            convertView = mInflater.inflate(R.layout.item_listview_pop, null);
            holder.tv_title = (TextView) convertView.findViewById(R.id.tv_pop);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.tv_title.setText(str_title[position]);
        return convertView;
    }

    class ViewHolder {
        TextView tv_title;
    }

}
