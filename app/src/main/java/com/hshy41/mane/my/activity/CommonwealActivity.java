package com.hshy41.mane.my.activity;

import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.hshy41.mane.R;
import com.hshy41.mane.adapter.ImagePagerAdapter;
import com.hshy41.mane.adapter.IndexListViewAdapter;
import com.hshy41.mane.base.BaseActivity;
import com.hshy41.mane.bean.LunboImageBaseBean;
import com.hshy41.mane.entity.ImageEntity;
import com.hshy41.mane.utils.NetDataCallBack;
import com.hshy41.mane.utils.NetDataUtils;
import com.hshy41.mane.utils.ViewSetUtils;
import com.hshy41.mane.views.AutoScrollViewPager;

import org.apache.http.NameValuePair;
import org.apache.http.client.CookieStore;

import java.util.ArrayList;
import java.util.List;

public class CommonwealActivity
        extends BaseActivity implements View.OnClickListener {
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
     * 满e公益listview
     */
    private ListView listview;
    /**
     * 满e公益大文字布局
     */
    private LinearLayout ll_commonweal_text;

    /**
     * 满e公益list布局
     */
    private RelativeLayout rl_commonweal_list;

    private ImageView iv_diverlien;

    @Override
    protected int setContent() {
        return R.layout.activity_commonweal;
    }

    @Override
    protected void initViews() {
        autoviewpager = (AutoScrollViewPager) findViewById(R.id.avp_commonweal);
        layout = (LinearLayout) findViewById(R.id.ll_commonweal_bannarpointer);
        listview = (ListView) findViewById(R.id.lv_commonweal);

        iv_diverlien = (ImageView) findViewById(R.id.iv_diverlien);

        ll_commonweal_text = (LinearLayout) findViewById(R.id.ll_commonweal_text);

        rl_commonweal_list = (RelativeLayout) findViewById(R.id.rl_commonweal_list);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                rl_commonweal_list.setVisibility(View.GONE);
                ll_commonweal_text.setVisibility(View.VISIBLE);
            }
        });


        ViewSetUtils.setViewHeigh(this, autoviewpager, 2, 1);
        getImage();
        setlistview();

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
        rl_title_right.setVisibility(View.GONE);
        iv_title_gps.setVisibility(View.GONE);
        tv_title_text.setVisibility(View.VISIBLE);
        bt_title_right.setVisibility(View.GONE);
        bt_title_left.setVisibility(View.VISIBLE);
        tv_title_right.setVisibility(View.GONE);

        tv_title_text.setText(R.string.commonweal);

        bt_title_left.setOnClickListener(this);
        rl_title_left.setOnClickListener(this);
        ll_commonweal_text.setOnClickListener(this);
    }


    /**
     * 联网获取轮播图
     */
    private void getImage() {
        // TODO Auto-generated method stub
        List<NameValuePair> pairs = new ArrayList<NameValuePair>();
        String url = "http://byh.qweweq.com/index.php/App/index/banner";
        NetDataUtils.getNetDataForGet(this, url, pairs, mcallBack);
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
        autoviewpager.setAdapter(new ImagePagerAdapter(this, imageIdList).setInfiniteLoop(true));
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
            ImageView newimage = new ImageView(this);
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
        switch (v.getId()) {
            case R.id.rl_title_left:
            case R.id.bt_title_left:
                finish();
                break;
            case R.id.ll_commonweal_text:
                ll_commonweal_text.setVisibility(View.GONE);
                rl_commonweal_list.setVisibility(View.VISIBLE);
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
     * 设置满e公益listview
     */
    private void setlistview() {
        // TODO Auto-generated method stub
        IndexListViewAdapter listViewAdapter = new IndexListViewAdapter(this);
        listview.setAdapter(listViewAdapter);
        ViewSetUtils.setListViewHeightBasedOnChildren(listview);

    }


}