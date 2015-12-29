package com.hshy41.mane.fragment;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.CookieStore;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.google.gson.Gson;

import com.hshy41.mane.R;
import com.hshy41.mane.adapter.FragmentAdapter;
import com.hshy41.mane.adapter.ImagePagerAdapter;
import com.hshy41.mane.adapter.IndexListViewAdapter;
import com.hshy41.mane.base.BaseFragment;
import com.hshy41.mane.bean.LunboImageBaseBean;
import com.hshy41.mane.entity.ImageEntity;
import com.hshy41.mane.fragment.shouyefragment.OneFragment;
import com.hshy41.mane.fragment.shouyefragment.TwoFragment;
import com.hshy41.mane.my.activity.SearchActivity;
import com.hshy41.mane.utils.NetDataCallBack;
import com.hshy41.mane.utils.NetDataUtils;
import com.hshy41.mane.utils.ViewSetUtils;
import com.hshy41.mane.views.AutoScrollViewPager;

public class IndexFragment extends BaseFragment implements View.OnClickListener {
    private AutoScrollViewPager autoviewpager;
    private ViewPager viewpager_15;
    private ImageView img1, img2, img3, img4, img6;
    private TextView img5;
    private ListView listview;
    private List<ImageEntity> imageIdList = new ArrayList<ImageEntity>();
    public List<ImageView> imgYuandian = new ArrayList<ImageView>();
    public LinearLayout layout, layout1;// 小白点
    private int img[] = {R.drawable.a1, R.drawable.a2, R.drawable.a3, R.drawable.a2};
    private List<Fragment> fragmentlist = new ArrayList<Fragment>();
    private OneFragment onefragment;
    private TwoFragment twofragment;

    /**
     * 九宫格当前页卡
     */
    private int i_index_gridpage = 0;

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

        tv_title_text.setText(R.string.gps_address);
        //正确显示titlebar
        rl_title_layout.setFocusable(true);
        rl_title_layout.setFocusableInTouchMode(true);
        rl_title_layout.requestFocus();

        bt_title_right.setOnClickListener(this);
        rl_title_right.setOnClickListener(this);
    }

    @Override
    protected int setLayoutID() {
        // TODO Auto-generated method stub
        context = getActivity();
        return R.layout.fragment_index;
    }

    @Override
    protected void initView(View view) {
        // TODO Auto-generated method stub
        autoviewpager = (AutoScrollViewPager) view.findViewById(R.id.viewpager_image);
        viewpager_15 = (ViewPager) view.findViewById(R.id.viewpager_15);
        layout = (LinearLayout) view.findViewById(R.id.viewpager_yuandian);
        layout1 = (LinearLayout) view.findViewById(R.id.viewpager_yuandian1);
        listview = (ListView) view.findViewById(R.id.lv_index_recommend);
        img1 = (ImageView) view.findViewById(R.id.img_1);
        img2 = (ImageView) view.findViewById(R.id.img_2);
        img3 = (ImageView) view.findViewById(R.id.img_3);
        img4 = (ImageView) view.findViewById(R.id.iv_index_concessions);
        img5 = (TextView) view.findViewById(R.id.tv_index_market);
        img6 = (ImageView) view.findViewById(R.id.img_6);
        ViewSetUtils.setViewHeigh(context, autoviewpager, 2, 1);
        ViewSetUtils.setViewHeigh(context, img1, 2, 1);
        ViewSetUtils.setViewWhithAndHeigh(context, img2, 50, 1, 1);
        ViewSetUtils.setViewWhithAndHeigh(context, img3, 50, 1, 1);
        getImage();
        setViewpager();
        setlistview();
    }

    /**
     * 设置 为您推荐的listview
     */
    private void setlistview() {
        // TODO Auto-generated method stub
        IndexListViewAdapter listViewAdapter = new IndexListViewAdapter(getActivity());
        listview.setAdapter(listViewAdapter);
        ViewSetUtils.setListViewHeightBasedOnChildren(listview);
    }

    /**
     * 设置九宫格viewpager
     */
    private void setViewpager() {
        // TODO Auto-generated method stub
        onefragment = new OneFragment();
        twofragment = new TwoFragment();
        fragmentlist.add(onefragment);
        fragmentlist.add(twofragment);
        viewpager_15.setAdapter(new FragmentAdapter(getChildFragmentManager(), fragmentlist));
        viewpager_15.setCurrentItem(0);
        viewpager_15.setOnPageChangeListener(new MyOnPageChangeListener1());
        setGalleryIndex1(layout1, 0);

        ViewSetUtils.setViewHeigh(getActivity(), viewpager_15, 1, 1);
    }

    /**
     * 联网获取轮播图
     */
    private void getImage() {
        // TODO Auto-generated method stub
        List<NameValuePair> pairs = new ArrayList<NameValuePair>();
        String url = "http://byh.qweweq.com/index.php/App/index/banner";
        NetDataUtils.getNetDataForGet(context, url, pairs, mcallBack);
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
        autoviewpager.setAdapter(new ImagePagerAdapter(context, imageIdList).setInfiniteLoop(true));
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
            ImageView newimage = new ImageView(context);
            newimage.setLayoutParams(new LayoutParams(
                    LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
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
     * 九宫格圆点
     *
     * @param viewgroup
     * @param p
     * @return
     */
    private List<ImageView> setGalleryIndex1(LinearLayout viewgroup, int p) {
        // TODO Auto-generated method stub
        int size = fragmentlist == null ? 0 : fragmentlist.size();
        if (size == 0)
            return null;
        viewgroup.removeAllViews();
        for (int i = 0; i < size; i++) {
            ImageView newimage = new ImageView(context);
            newimage.setLayoutParams(new LayoutParams(
                    LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
            newimage.setPadding(5, 0, 5, 0);

            if (i == p) {
                newimage.setImageResource(R.drawable.lunbo2_1);
            } else {
                newimage.setImageResource(R.drawable.lunbo2_2);
            }
            imgYuandian.add(newimage);
            viewgroup.addView(newimage);
        }
        return imgYuandian;

    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.bt_title_right:
            case R.id.rl_title_right:
                intent = new Intent(getActivity(), SearchActivity.class);
                getActivity().startActivity(intent);
                break;

        }
    }

    /**
     * 九宫格viewpager监听
     *
     * @author Administrator
     */
    public class MyOnPageChangeListener1 implements OnPageChangeListener {

        @Override
        public void onPageSelected(int position) {

            setGalleryIndex1(layout1, (position) % fragmentlist.size());

        }

        @Override
        public void onPageScrolled(int position, float positionOffset,
                                   int positionOffsetPixels) {
            i_index_gridpage = position;
        }

        @Override
        public void onPageScrollStateChanged(int arg0) {
        }
    }

    /**
     * 轮播图监听
     *
     * @author Administrator
     */
    public class MyOnPageChangeListener implements OnPageChangeListener {

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
    public void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        autoviewpager.startAutoScroll();
    }

    @Override
    public void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        autoviewpager.startAutoScroll();
    }

}
