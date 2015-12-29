package com.hshy41.mane.fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.hshy41.mane.R;
import com.hshy41.mane.adapter.ImagePagerAdapter;
import com.hshy41.mane.adapter.recyclerviewadapter.Community;
import com.hshy41.mane.adapter.recyclerviewadapter.DeafaultItemDecoration;
import com.hshy41.mane.base.BaseFragment;
import com.hshy41.mane.bean.LunboImageBaseBean;
import com.hshy41.mane.entity.ImageEntity;
import com.hshy41.mane.my.activity.CommonwealActivity;
import com.hshy41.mane.my.activity.FinancingActivity;
import com.hshy41.mane.my.activity.GetMonneyActivity;
import com.hshy41.mane.my.activity.HouseKeepingActivity;
import com.hshy41.mane.my.activity.HouseRentActivity;
import com.hshy41.mane.my.activity.PropertyHallActivity;
import com.hshy41.mane.my.activity.SearchActivity;
import com.hshy41.mane.my.activity.TourActivity;
import com.hshy41.mane.my.activity.ZhouBianShangHuActivity;
import com.hshy41.mane.utils.Cons;
import com.hshy41.mane.utils.NetDataCallBack;
import com.hshy41.mane.utils.NetDataUtils;
import com.hshy41.mane.views.AutoScrollViewPager;

import org.apache.http.NameValuePair;
import org.apache.http.client.CookieStore;


public class CommunityFragment extends BaseFragment implements View.OnClickListener {
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
     * 物业服务按钮
     */
    private Button bt_property_service;

    /**
     * 社区热门推荐列表
     */
    private RecyclerView rv_community;

    @Override
    protected void initTitleBar(View view) {
        rl_title_left = (RelativeLayout) view.findViewById(R.id.rl_title_left);
        rl_title_right = (RelativeLayout) view.findViewById(R.id.rl_title_right);
        iv_title_gps = (ImageView) view.findViewById(R.id.iv_title_gps);
        tv_title_text = (TextView) view.findViewById(R.id.tv_title_text);
        bt_title_right = (Button) view.findViewById(R.id.bt_title_right);
        bt_title_left = (Button) view.findViewById(R.id.bt_title_left);
        tv_title_right = (TextView) view.findViewById(R.id.tv_title_right);
        rl_title_layout = (RelativeLayout) view.findViewById(R.id.title_layout);
    }

    @Override
    protected void setTitleBar() {
        rl_title_layout.setVisibility(View.VISIBLE);
        rl_title_left.setVisibility(View.GONE);
        rl_title_right.setVisibility(View.VISIBLE);
        tv_title_text.setVisibility(View.VISIBLE);
        iv_title_gps.setVisibility(View.VISIBLE);
        bt_title_right.setVisibility(View.VISIBLE);
        bt_title_left.setVisibility(View.GONE);
        tv_title_right.setVisibility(View.GONE);
    }

    @Override
    protected int setLayoutID() {
        // TODO Auto-generated method stub
        return R.layout.fragment_community;
    }

    @Override
    protected void initView(View view) {
        // TODO Auto-generated method stub
        bt_property_service = (Button) view.findViewById(R.id.bt_community_property_service);
        rv_community = (RecyclerView) view.findViewById(R.id.rv_community);
        autoviewpager = (AutoScrollViewPager) view.findViewById(R.id.avp_community);
        layout = (LinearLayout) view.findViewById(R.id.ll_community_bannarpointer);
        bt_property_service = (Button) view.findViewById(R.id.bt_community_property_service);

        Community adapter = new Community(getActivity());
        adapter.setOnItemClickLitener(new Community.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent;
                switch (position) {
                    case 0:
                        intent = new Intent(getActivity(), HouseKeepingActivity.class);
                        getActivity().startActivity(intent);
                        break;
                    case 1:
                        intent = new Intent(getActivity(), TourActivity.class);
                        getActivity().startActivity(intent);
                        break;
                    case 2:
                        intent = new Intent(getActivity(), FinancingActivity.class);
                        getActivity().startActivity(intent);
                        break;
                }
            }
        });
        rv_community.setAdapter(adapter);
        rv_community.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv_community.addItemDecoration(new DeafaultItemDecoration(getActivity(),
                DeafaultItemDecoration.VERTICAL_LIST));
        bt_property_service.setOnClickListener(this);
        rl_title_right.setOnClickListener(this);
        bt_title_right.setOnClickListener(this);
        getImage();
    }

    /**
     * 联网获取轮播图
     */
    private void getImage() {
        // TODO Auto-generated method stub
        List<NameValuePair> pairs = new ArrayList<NameValuePair>();
        String url = "http://byh.qweweq.com/index.php/App/index/banner";
        NetDataUtils.getNetDataForGet(getActivity(), url, pairs, mcallBack);
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
        autoviewpager.setAdapter(new ImagePagerAdapter(getActivity(), imageIdList).setInfiniteLoop(true));
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
            ImageView newimage = new ImageView(getActivity());
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

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.bt_community_property_service:
                intent = new Intent(getActivity(), PropertyHallActivity.class);
                getActivity().startActivity(intent);
                break;
            case R.id.rl_title_right:
            case R.id.bt_title_right:
                intent = new Intent(getActivity(), SearchActivity.class);
                getActivity().startActivity(intent);
                break;
        }
    }
}
