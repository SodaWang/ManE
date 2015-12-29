package com.hshy41.mane.my.activity;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.hshy41.mane.R;
import com.hshy41.mane.adapter.ImagePagerAdapter;
import com.hshy41.mane.adapter.recyclerviewadapter.DividerGridItemDecoration;
import com.hshy41.mane.adapter.recyclerviewadapter.HouseKeeping;
import com.hshy41.mane.base.BaseActivity;
import com.hshy41.mane.bean.LunboImageBaseBean;
import com.hshy41.mane.entity.ImageEntity;
import com.hshy41.mane.utils.Cons;
import com.hshy41.mane.utils.NetDataCallBack;
import com.hshy41.mane.utils.NetDataUtils;
import com.hshy41.mane.views.AutoScrollViewPager;

import org.apache.http.NameValuePair;
import org.apache.http.client.CookieStore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class HouseKeepingActivity extends BaseActivity implements View.OnClickListener {
    private List<ImageEntity> imageIdList = new ArrayList<ImageEntity>();
    /**
     * 自动轮播器
     */
    private AutoScrollViewPager autoviewpager;
    /**
     * 轮播指示图列表
     */
    public List<ImageView> imgYuandian = new ArrayList<ImageView>();
    /**
     * 小白点
     */
    public LinearLayout layout;
    /**
     * 数据源
     *
     * @param savedInstanceState
     */
    ArrayList<HashMap<String, Object>> mData = new ArrayList<HashMap<String, Object>>();

    /**
     * 数据列表
     *
     * @param savedInstanceState
     */
    RecyclerView rv_houseKeeping;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int setContent() {
        return R.layout.activity_housekeeping;
    }

    @Override
    protected void initViews() {

        for (int i = 0; i < Cons.DRAWABLEIDARR_HOUSEKEEPING_ICON.length; i++) {
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("icon", getResources().getDrawable(Cons.DRAWABLEIDARR_HOUSEKEEPING_ICON[i]));
            map.put("title", getResources().getString(Cons.STRIDARR_HOUSEKEEPING_TITLE[i]));
            map.put("content", getResources().getString(Cons.STRIDARR_HOUSEKEEPING_CONTENT[i]));
            mData.add(map);
        }

        rv_houseKeeping = (RecyclerView) findViewById(R.id.rv_housekeeping);
        autoviewpager = (AutoScrollViewPager) findViewById(R.id.avp_housekeeping);
        layout = (LinearLayout) findViewById(R.id.ll_housekeeping_bannarpointer);
//        ViewSetUtils.setViewHeigh(context, autoviewpager, 2, 1);
        rv_houseKeeping.setHasFixedSize(true);
        HouseKeeping adapter = new HouseKeeping(this, mData);
        adapter.setOnItemClickLitener(new HouseKeeping.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(HouseKeepingActivity.this, ServiceListActivity.class);
                startActivity(intent);
            }
        });
        rv_houseKeeping.setAdapter(adapter);
        rv_houseKeeping.setLayoutManager(new GridLayoutManager(this, 2));
        rv_houseKeeping.addItemDecoration(new DividerGridItemDecoration(this));
        getImage();

    }

    @Override
    protected void initTitleBar() {
        rl_title_left = (RelativeLayout) findViewById(R.id.rl_title_left);
        rl_title_right = (RelativeLayout) findViewById(R.id.rl_title_right);
        iv_title_gps = (ImageView) findViewById(R.id.iv_title_gps);
        tv_title_text = (TextView) findViewById(R.id.tv_title_text);
        tv_title_right = (TextView) findViewById(R.id.tv_title_right);
        bt_title_right = (Button) findViewById(R.id.bt_title_right);
        bt_title_left = (Button) findViewById(R.id.bt_title_left);
        tv_title_right = (TextView) findViewById(R.id.tv_title_right);
    }

    @Override
    protected void setTitleBar() {
        rl_title_left.setVisibility(View.VISIBLE);
        rl_title_right.setVisibility(View.VISIBLE);
        iv_title_gps.setVisibility(View.GONE);
        tv_title_text.setVisibility(View.VISIBLE);
        bt_title_right.setVisibility(View.VISIBLE);
        bt_title_left.setVisibility(View.VISIBLE);
        tv_title_right.setVisibility(View.GONE);

        tv_title_text.setText(R.string.housekeeping);

        bt_title_left.setOnClickListener(this);
        rl_title_left.setOnClickListener(this);

        rl_title_right.setOnClickListener(this);
        bt_title_right.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.rl_title_left:
            case R.id.bt_title_left:
                finish();
                break;
            case R.id.rl_title_right:
            case R.id.bt_title_right:
                intent = new Intent(this, SearchActivity.class);
                startActivity(intent);
                break;
        }
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


    /**
     * 轮播图圆点
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
}
