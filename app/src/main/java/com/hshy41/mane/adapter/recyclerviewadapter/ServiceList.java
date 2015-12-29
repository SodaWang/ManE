package com.hshy41.mane.adapter.recyclerviewadapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.gson.Gson;
import com.hshy41.mane.R;
import com.hshy41.mane.adapter.ImagePagerAdapter;
import com.hshy41.mane.adapter.IndexListViewAdapter;
import com.hshy41.mane.adapter.LeftPopListViewAdapter;
import com.hshy41.mane.adapter.RightPopListViewAdapter;
import com.hshy41.mane.adapter.ServiceListViewAdapter;
import com.hshy41.mane.bean.LunboImageBaseBean;
import com.hshy41.mane.entity.ImageEntity;
import com.hshy41.mane.my.activity.ProductInfoActivity;
import com.hshy41.mane.my.activity.ServiceListActivity;
import com.hshy41.mane.utils.Cons;
import com.hshy41.mane.utils.NetDataCallBack;
import com.hshy41.mane.utils.NetDataUtils;
import com.hshy41.mane.utils.ViewSetUtils;
import com.hshy41.mane.views.AutoScrollViewPager;

import org.apache.http.NameValuePair;
import org.apache.http.client.CookieStore;

import java.util.ArrayList;
import java.util.List;

public class ServiceList
        extends RecyclerView.Adapter<ServiceList.ViewHolder> {

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        Context mContext;
        public final View mView;


        enum PopMenuState {LEFT_OUT, LEFT_IN, RIGHT_OUT, RIGHT_IN}

        ;
        PopMenuState popState;
        /**
         * 弹出项是否弹出
         */
        private boolean[] b_isPop = {false, false};


        /**
         * 服务列表listview
         */
        private ListView listview;
        /**
         * 服务列表弹出布局
         */
        private RelativeLayout rl_pop;

        /**
         * 左下拉剪头
         */
        private ImageView iv_arrowdown_left;

        /**
         * 右下拉剪头
         */
        private ImageView iv_arrowdown_right;

        /**
         * 左上拉剪头
         */
        private ImageView iv_arrowup_left;

        /**
         * 右上拉剪头
         */
        private ImageView iv_arrowup_right;
        /**
         * 服务列表左弹出布局
         */
        private RelativeLayout rl_pop_left;
        /**
         * 服务列表右弹出布局
         */
        private RelativeLayout rl_pop_right;
        /**
         * 服务列表左弹出listview
         */
        private ListView lv_pop_left;
        /**
         * 服务列表右弹出listview
         */
        private ListView lv_pop_right;
        /**
         * 服务列表整体布局
         */
        private LinearLayout ll_servicelist;
        /**
         * 服务列表整体布局
         */
        private ImageView iv_diverlien;
        /**
         * 弹出动画
         */
        private Animation am_out_left, am_out_right;
        /**
         * 缩入动画
         */
        private Animation am_in_left, am_in_right;

        /**
         * 点击了哪个弹出菜单
         */
        private int i_popIndex = -1;

        public ViewHolder(ViewGroup parent, View view) {
            super(view);
            mContext = parent.getContext();
            mView = view;

            listview = (ListView) view.findViewById(R.id.lv_servicelist);
            rl_pop = (RelativeLayout) view.findViewById(R.id.rl_pop);
            iv_arrowdown_left = (ImageView) view.findViewById(R.id.iv_arrowdown_left);
            iv_arrowdown_right = (ImageView) view.findViewById(R.id.iv_arrowdown_right);
            iv_arrowup_left = (ImageView) view.findViewById(R.id.iv_arrowup_left);
            iv_arrowup_right = (ImageView) view.findViewById(R.id.iv_arrowup_right);
            rl_pop_left = (RelativeLayout) view.findViewById(R.id.rl_pop_left);
            rl_pop_right = (RelativeLayout) view.findViewById(R.id.rl_pop_right);
            lv_pop_left = (ListView) view.findViewById(R.id.lv_pop_left);
            lv_pop_right = (ListView) view.findViewById(R.id.lv_pop_right);
            ll_servicelist = (LinearLayout) view.findViewById(R.id.ll_servicelist);
            iv_diverlien = (ImageView) view.findViewById(R.id.iv_diverlien);
            am_out_left = AnimationUtils.loadAnimation(mContext, R.anim.popout);
            am_out_right = AnimationUtils.loadAnimation(mContext, R.anim.popout);
            am_in_left = AnimationUtils.loadAnimation(mContext, R.anim.popin);
            am_in_right = AnimationUtils.loadAnimation(mContext, R.anim.popin);


            rl_pop_left.setOnClickListener(this);
            rl_pop_right.setOnClickListener(this);
            setlistview();
            ViewSetUtils.CountListAndSet(mContext, listview, rl_pop);

            am_in_left.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {

                    lv_pop_left.clearAnimation();
                    lv_pop_left.setVisibility(View.GONE);
                    if (b_isPop[0] != true && b_isPop[1] != true) {
                        iv_diverlien.setVisibility(View.VISIBLE);
                        rl_pop.setVisibility(View.GONE);
                    }


                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });

            am_in_right.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    lv_pop_right.clearAnimation();
                    lv_pop_right.setVisibility(View.GONE);
                    if (b_isPop[0] != true && b_isPop[1] != true) {
                        rl_pop.setVisibility(View.GONE);
                        iv_diverlien.setVisibility(View.VISIBLE);
                    }

                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
//            ViewSetUtils.setViewHeighWithListView(context, ll_pop, listview);
        }


        @Override
        public void onClick(View v) {
            Intent intent;
            Bundle bundle;
            switch (v.getId()) {
                case R.id.rl_pop_left:
                    i_popIndex = 0;
                    if (!b_isPop[0]) {
                        iv_arrowdown_left.setVisibility(View.GONE);
                        iv_arrowup_left.setVisibility(View.VISIBLE);
                        iv_arrowdown_right.setVisibility(View.GONE);
                        iv_arrowup_right.setVisibility(View.GONE);
                        rl_pop.setVisibility(View.VISIBLE);

                        iv_diverlien.setVisibility(View.GONE);

                        lv_pop_left.setVisibility(View.VISIBLE);
                        lv_pop_left.startAnimation(am_out_left);
                        b_isPop[0] = true;
                    } else {
                        iv_arrowdown_left.setVisibility(View.VISIBLE);
                        iv_arrowup_left.setVisibility(View.GONE);
                        iv_arrowdown_right.setVisibility(View.VISIBLE);
                        iv_arrowup_right.setVisibility(View.GONE);

                        lv_pop_right.setVisibility(View.GONE);

                        lv_pop_left.startAnimation(am_in_left);

                        b_isPop[0] = false;
                    }

                    if (b_isPop[0] == b_isPop[1] == true) {
                        iv_diverlien.setVisibility(View.GONE);
                        lv_pop_right.startAnimation(am_in_right);
                        b_isPop[1] = false;
                    }
                    break;
                case R.id.rl_pop_right:
                    i_popIndex = 1;
                    if (!b_isPop[1]) {
                        iv_arrowdown_left.setVisibility(View.GONE);
                        iv_arrowup_left.setVisibility(View.GONE);
                        iv_arrowdown_right.setVisibility(View.GONE);
                        iv_arrowup_right.setVisibility(View.VISIBLE);
                        rl_pop.setVisibility(View.VISIBLE);

                        iv_diverlien.setVisibility(View.GONE);

                        lv_pop_left.setVisibility(View.GONE);
                        lv_pop_right.setVisibility(View.VISIBLE);
                        lv_pop_right.startAnimation(am_out_right);
                        b_isPop[1] = true;
                    } else {
                        iv_arrowdown_left.setVisibility(View.VISIBLE);
                        iv_arrowup_left.setVisibility(View.GONE);
                        iv_arrowdown_right.setVisibility(View.VISIBLE);
                        iv_arrowup_right.setVisibility(View.GONE);

                        lv_pop_left.setVisibility(View.GONE);
                        lv_pop_right.startAnimation(am_in_right);
                        b_isPop[1] = false;
                    }
                    if (b_isPop[0] == b_isPop[1] == true) {
                        iv_diverlien.setVisibility(View.GONE);
                        lv_pop_left.startAnimation(am_in_left);
                        b_isPop[0] = false;
                    }
                    break;
            }
        }

        /**
         * 设置服务列表listview
         */
        private void setlistview() {
            // TODO Auto-generated method stub
            ServiceListViewAdapter listViewAdapter = new ServiceListViewAdapter(mContext);
            listview.setAdapter(listViewAdapter);
            ViewSetUtils.setListViewHeightBasedOnChildren(listview);

            lv_pop_left.setAdapter((new LeftPopListViewAdapter(mContext)));
            lv_pop_right.setAdapter(new RightPopListViewAdapter(mContext));

            listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Bundle bundle = new Bundle();
                    bundle.putInt("activity", Cons.ACTIVITY_SERVICELIST);
                    Intent intent = new Intent(mContext, ProductInfoActivity.class);
                    intent.putExtras(bundle);
                    mContext.startActivity(intent);
                }
            });

        }

    }

    public String getValueAt(int position) {
        return "1";
    }


    public ServiceList() {

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_servicelist, parent, false);

        return new ViewHolder(parent, view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "Item Clicked!", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return 1;
    }
}