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
import com.hshy41.mane.bean.LunboImageBaseBean;
import com.hshy41.mane.entity.ImageEntity;
import com.hshy41.mane.my.activity.ProductInfoActivity;
import com.hshy41.mane.utils.Cons;
import com.hshy41.mane.utils.NetDataCallBack;
import com.hshy41.mane.utils.NetDataUtils;
import com.hshy41.mane.utils.ViewSetUtils;
import com.hshy41.mane.views.AutoScrollViewPager;

import org.apache.http.NameValuePair;
import org.apache.http.client.CookieStore;

import java.util.ArrayList;
import java.util.List;

public class ZhouBianShangHu
        extends RecyclerView.Adapter<ZhouBianShangHu.ViewHolder> {

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
         * 轮播器
         */
        private AutoScrollViewPager autoviewpager;
        /**
         * 轮播指示器
         */
        public LinearLayout layout;

        /**
         * 轮播图信息组
         */
        private List<ImageEntity> imageIdList = new ArrayList<ImageEntity>();
        /**
         * 轮播指示器控件组
         */
        public List<ImageView> imgYuandian = new ArrayList<ImageView>();
        /**
         * 周边商户listview
         */
        private ListView listview;
        /**
         * 周边商户弹出布局
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
         * 周边商户左弹出布局
         */
        private RelativeLayout rl_pop_left;
        /**
         * 周边商户右弹出布局
         */
        private RelativeLayout rl_pop_right;
        /**
         * 周边商户左弹出listview
         */
        private ListView lv_pop_left;
        /**
         * 周边商户右弹出listview
         */
        private ListView lv_pop_right;
        /**
         * 周边商户整体布局
         */
        private LinearLayout ll_zhoubianshanghu;
        /**
         * 周边商户整体布局
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
            autoviewpager = (AutoScrollViewPager) view.findViewById(R.id.avp_zhoubianshanghu);
            layout = (LinearLayout) view.findViewById(R.id.ll_zhoubianshanghu_bannarpointer);
            listview = (ListView) view.findViewById(R.id.lv_zhoubianshanghu);
            rl_pop = (RelativeLayout) view.findViewById(R.id.rl_pop);
            iv_arrowdown_left = (ImageView) view.findViewById(R.id.iv_arrowdown_left);
            iv_arrowdown_right = (ImageView) view.findViewById(R.id.iv_arrowdown_right);
            iv_arrowup_left = (ImageView) view.findViewById(R.id.iv_arrowup_left);
            iv_arrowup_right = (ImageView) view.findViewById(R.id.iv_arrowup_right);
            rl_pop_left = (RelativeLayout) view.findViewById(R.id.rl_pop_left);
            rl_pop_right = (RelativeLayout) view.findViewById(R.id.rl_pop_right);
            lv_pop_left = (ListView) view.findViewById(R.id.lv_pop_left);
            lv_pop_right = (ListView) view.findViewById(R.id.lv_pop_right);
            ll_zhoubianshanghu = (LinearLayout) view.findViewById(R.id.ll_zhoubianshangh);
            iv_diverlien = (ImageView) view.findViewById(R.id.iv_diverlien);
            am_out_left = AnimationUtils.loadAnimation(mContext, R.anim.popout);
            am_out_right = AnimationUtils.loadAnimation(mContext, R.anim.popout);
            am_in_left = AnimationUtils.loadAnimation(mContext, R.anim.popin);
            am_in_right = AnimationUtils.loadAnimation(mContext, R.anim.popin);

            listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Bundle bundle = new Bundle();
                    bundle.putInt("activity", Cons.ACTIVITY_ZHOUBIANSHANGHU);
                    Intent intent = new Intent(mContext, ProductInfoActivity.class);
                    intent.putExtras(bundle);
                    mContext.startActivity(intent);
                }
            });

            rl_pop_left.setOnClickListener(this);
            rl_pop_right.setOnClickListener(this);

            ViewSetUtils.setViewHeigh(mContext, autoviewpager, 2, 1);

            getImage();
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

        /**
         * 联网获取轮播图
         */
        private void getImage() {
            // TODO Auto-generated method stub
            List<NameValuePair> pairs = new ArrayList<NameValuePair>();
            String url = "http://byh.qweweq.com/index.php/App/index/banner";
            NetDataUtils.getNetDataForGet(mContext, url, pairs, mcallBack);
        }

        private NetDataCallBack mcallBack = new NetDataCallBack() {

            @Override
            public void onNetSuccess(String t, CookieStore cookieStore) {
                // TODO Auto-generated method stub
                Gson g = new Gson();
                LunboImageBaseBean bean = g.fromJson(t, LunboImageBaseBean.class);
                List<ImageEntity> list = bean.data;
                imageIdList.addAll(list);
                setlunbo();
            }

            @Override
            public void onNetFailure() {
                // TODO Auto-generated method stub

            }

            @Override
            public void onNetError(String errMsg) {
                // TODO Auto-generated method stub

            }
        };

        private void setlunbo() {
            // TODO Auto-generated method stub
            autoviewpager.setAdapter(new ImagePagerAdapter(mContext, imageIdList).setInfiniteLoop(true));
            autoviewpager.setOnPageChangeListener(new MyOnPageChangeListener());
            autoviewpager.setInterval(5000);
            autoviewpager.startAutoScroll();
            autoviewpager.setCurrentItem(100 - 100 % imageIdList.size());
            setGalleryIndex(layout, 0);
        }

        /**
         * 轮播图原点
         *
         * @param viewgroup
         * @param p
         * @return
         */
        private List<ImageView> setGalleryIndex(LinearLayout viewgroup, int p) {
            // TODO Auto-generated method stub

            int size = imageIdList == null ? 0 : imageIdList.size();
            if (size == 0)
                return null;
            viewgroup.removeAllViews();
            for (int i = 0; i < size; i++) {
                ImageView newimage = new ImageView(mContext);
                newimage.setLayoutParams(new ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                newimage.setPadding(5, 0, 5, 0);

                if (i == p) {
                    newimage.setImageResource(R.drawable.lunbo);
                } else {
                    newimage.setImageResource(R.drawable.lunbo_kong);
                }
                imgYuandian.add(newimage);
                viewgroup.addView(newimage);
            }
            return imgYuandian;
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
         * 轮播图监听
         *
         * @author Administrator
         */
        public class MyOnPageChangeListener implements ViewPager.OnPageChangeListener {

            @Override
            public void onPageSelected(int position) {

                setGalleryIndex(layout, (position) % imageIdList.size());
            }

            @Override
            public void onPageScrolled(int position, float positionOffset,
                                       int positionOffsetPixels) {
            }

            @Override
            public void onPageScrollStateChanged(int arg0) {
            }
        }

        /**
         * 设置周边商户listview
         */
        private void setlistview() {
            // TODO Auto-generated method stub
            IndexListViewAdapter listViewAdapter = new IndexListViewAdapter(mContext);
            listview.setAdapter(listViewAdapter);
            ViewSetUtils.setListViewHeightBasedOnChildren(listview);

            lv_pop_left.setAdapter((new LeftPopListViewAdapter(mContext)));
            lv_pop_right.setAdapter(new RightPopListViewAdapter(mContext));
        }

    }

    public String getValueAt(int position) {
        return "1";
    }


    public ZhouBianShangHu() {

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_zhoubianshanghu, parent, false);

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