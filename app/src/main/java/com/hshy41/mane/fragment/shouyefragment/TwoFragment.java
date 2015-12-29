package com.hshy41.mane.fragment.shouyefragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.hshy41.mane.R;
import com.hshy41.mane.base.BaseFragment;
import com.hshy41.mane.my.activity.CommonwealActivity;
import com.hshy41.mane.my.activity.FinancingActivity;
import com.hshy41.mane.my.activity.GetMonneyActivity;
import com.hshy41.mane.my.activity.HouseKeepingActivity;
import com.hshy41.mane.my.activity.HouseRentActivity;
import com.hshy41.mane.my.activity.TourActivity;
import com.hshy41.mane.my.activity.ZhouBianShangHuActivity;
import com.hshy41.mane.utils.Cons;

public class TwoFragment extends BaseFragment {
    private GridView gridview;
    private ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
    String[] str = {"装修", "开锁", "宠物", "物业通知", "房屋出租", "保险"};
    int[] img = {R.drawable.zhuangxiu, R.drawable.kaisuo, R.drawable.chongwu, R.drawable.tongzhi, R.drawable.chuzu, R.drawable.baoxian};

    @Override
    protected void setTitleBar() {
        // TODO Auto-generated method stub

    }

    @Override
    protected int setLayoutID() {
        // TODO Auto-generated method stub
        return R.layout.fragment_two;
    }

    @Override
    protected void initView(View view) {
        // TODO Auto-generated method stub
        getdata();
        gridview = (GridView) view.findViewById(R.id.gridview_two);
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            Intent intent;
            Bundle bundle;

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    //装修
                    case 0:
                        intent = new Intent(getActivity(), ZhouBianShangHuActivity.class);
                        bundle = new Bundle();
                        bundle.putInt("fragment", Cons.FRAMENT_ZHOUBIANSHANGHU);
                        intent.putExtras(bundle);
                        getActivity().startActivity(intent);
                        break;
                    //开锁
                    case 1:
                        intent = new Intent(getActivity(), GetMonneyActivity.class);
                        startActivity(intent);
                        break;
                    //理财
                    case 2:
                        intent = new Intent(getActivity(), FinancingActivity.class);
                        startActivity(intent);
                        break;
                    //物业通知
                    case 3:
                        intent = new Intent(getActivity(), HouseKeepingActivity.class);
                        startActivity(intent);
                        break;
                    //房屋出租
                    case 4:
                        intent = new Intent(getActivity(), CommonwealActivity.class);
                        startActivity(intent);
                        break;
                    //保险
                    case 5:
                        intent = new Intent(getActivity(), HouseRentActivity.class);
                        startActivity(intent);
                        break;
                }
            }
        });
        addgridview();
    }

    private void addgridview() {
        // TODO Auto-generated method stub
        String[] key = {"img", "name"};
        int[] value = {R.id.iv_gridItem_img, R.id.tv_gridItem_text};
        gridview.setAdapter(new MyGiridViewAdapater());
    }

    private List<? extends Map<String, ?>> getdata() {
        // TODO Auto-generated method stub
        for (int i = 0; i < str.length; i++) {
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("img", img[i]);
            map.put("name", str[i]);
            list.add(map);
        }
        return list;
    }

    class MyGiridViewAdapater extends BaseAdapter {

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
                LayoutInflater mInflater = LayoutInflater.from(getActivity());
                convertView = mInflater.inflate(R.layout.gridviewitem, null);
                holder.iv_gridImg = (ImageView) convertView.findViewById(R.id.iv_gridItem_img);
                holder.tv_gridText = (TextView) convertView.findViewById(R.id.tv_gridItem_text);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            holder.tv_gridText.setText(str[position]);
            holder.iv_gridImg.setBackgroundResource(img[position]);
            return convertView;
        }
    }

    class ViewHolder {
        ImageView iv_gridImg;
        TextView tv_gridText;
    }
}
