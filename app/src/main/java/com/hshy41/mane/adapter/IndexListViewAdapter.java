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

public class IndexListViewAdapter extends BaseAdapter {

	private ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
	String[] str_title = {"周边商户", "赚钱", "理财", "家政服务", "满e公益", "周边商户", "赚钱", "理财", "家政服务", "满e公益"};
	String[] str_text = {"反反复复反反复复反反复复吩咐", "斯蒂芬斯蒂芬斯蒂芬斯蒂芬森的", "斯蒂芬斯蒂芬斯蒂芬斯蒂芬森的", "威风威风威风威风威风威风威风", "哇福娃俄方哇俄方we",
			"反反复复反反复复反反复复吩咐", "斯蒂芬斯蒂芬斯蒂芬斯蒂芬森的", "斯蒂芬斯蒂芬斯蒂芬斯蒂芬森的", "威风威风威风威风威风威风威风", "哇福娃俄方哇俄方we"};
	int[] img = {R.drawable.listimg, R.drawable.listimg, R.drawable.listimg, R.drawable.listimg, R.drawable.listimg,
			R.drawable.listimg, R.drawable.listimg, R.drawable.listimg, R.drawable.listimg, R.drawable.listimg};

	private Context mContext;

	public IndexListViewAdapter(Context context) {
		mContext = context;
	}

	private List<? extends Map<String, ?>> getdata() {
		// TODO Auto-generated method stub
		for (int i = 0; i < str_title.length; i++) {
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("img", img[i]);
			map.put("title", str_title[i]);
			map.put("text", str_text[i]);
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
			convertView = mInflater.inflate(R.layout.item_listview_index, null);
			holder.iv_Img = (ImageView) convertView.findViewById(R.id.iv_index_listimg);
			holder.tv_title = (TextView) convertView.findViewById(R.id.tv_index_listtitle);
			holder.tv_text = (TextView) convertView.findViewById(R.id.tv_index_listtext);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		holder.tv_title.setText(str_title[position]);
		holder.iv_Img.setBackgroundResource(img[position]);
		holder.tv_text.setText(str_text[position]);
		return convertView;
	}

	class ViewHolder {
		ImageView iv_Img;
		TextView tv_title;
		TextView tv_text;
	}

}
