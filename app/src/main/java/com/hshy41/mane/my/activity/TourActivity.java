package com.hshy41.mane.my.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
/**
 * @author lichao
 * 周边旅游页面
 */
import com.hshy41.mane.R;
import com.hshy41.mane.base.BaseFragment;
import com.hshy41.mane.fragment.TourFragment;
import com.lsjwzh.widget.recyclerviewpager.FragmentStatePagerAdapter;
import com.lsjwzh.widget.recyclerviewpager.RecyclerViewPager;
import com.lsjwzh.widget.recyclerviewpager.TabLayoutSupport;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class TourActivity extends AppCompatActivity implements View.OnClickListener {
    /**
     * 当前碎片
     */
    private int FRAMENT;

    private FragmentManager fm;
    /**
     * 碎片列表
     */
    private List<BaseFragment> fragmentlist = new ArrayList<BaseFragment>();

    /**
     * 开源viewpager
     */
    protected RecyclerViewPager mRecyclerView;
    /**
     * 碎片适配器
     */
    private FragmentsAdapter mAdapter;

    /**
     * 左布局
     */
    protected RelativeLayout rl_title_back;
    /**
     * 返回钮
     */
    protected Button bt_title_back;

    /**
     * 标题
     */
    protected TextView tv_title_text;

    /**
     * 定位图标
     */
    protected ImageView iv_title_gps;

    /**
     * 右按键
     */
    protected Button bt_title_right;

    /**
     * 右布局
     *
     * @param savedInstanceState
     */
    protected RelativeLayout rl_title_right;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tour);
        initViewPager();
        initTabLayout();
        initTitleBar();
        setTitleBar();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search, menu);
        return true;
    }

    private void initTabLayout() {
        // 给TabLayout增加Tab, 并关联ViewPager
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tl_tour);
        TabLayoutSupport.setupWithViewPager(tabLayout, mRecyclerView, mAdapter);
//		tabLayout.setTabTextColors(R.color.text_color_select, R.color.text_color_hint);
    }

    private void initToolbar() {
//		tb_tour = (Toolbar) findViewById(R.id.tb_tour);
//		tb_tour.setNavigationIcon(R.drawable.back);
//		tb_tour.setTitle(R.string.aroundshop);
//		setSupportActionBar(tb_tour);
    }

    protected void initViewPager() {
        mRecyclerView = (RecyclerViewPager) findViewById(R.id.rvp_tour);
        LinearLayoutManager layout = new LinearLayoutManager(this,
                LinearLayoutManager.HORIZONTAL,
                false);
        mRecyclerView.setLayoutManager(layout);
        mAdapter = new FragmentsAdapter(this.getSupportFragmentManager());
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLongClickable(true);
        mRecyclerView.addOnPageChangedListener(new RecyclerViewPager.OnPageChangedListener() {
            @Override
            public void OnPageChanged(int oldPosition, int newPosition) {
                Log.d("test", "oldPosition:" + oldPosition + " newPosition:" +
                        newPosition);
            }
        });

    }

    private void initTitleBar() {
        rl_title_back = (RelativeLayout) findViewById(R.id.rl_title_left);
        iv_title_gps = (ImageView) findViewById(R.id.iv_title_gps);
        tv_title_text = (TextView) findViewById(R.id.tv_title_text);
        bt_title_right = (Button) findViewById(R.id.bt_title_right);
        bt_title_back = (Button) findViewById(R.id.bt_title_left);
        rl_title_right = (RelativeLayout) findViewById(R.id.rl_title_right);
    }


    private void setTitleBar() {
        rl_title_back.setVisibility(View.VISIBLE);
        iv_title_gps.setVisibility(View.GONE);
        tv_title_text.setVisibility(View.VISIBLE);
        bt_title_right.setVisibility(View.GONE);
        rl_title_right.setVisibility(View.GONE);

        tv_title_text.setText(R.string.tour);


        bt_title_right.setOnClickListener(this);
        rl_title_right.setOnClickListener(this);
        rl_title_back.setOnClickListener(this);
        bt_title_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        Bundle bundle;
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

    class FragmentsAdapter extends FragmentStatePagerAdapter implements TabLayoutSupport.ViewPagerTabLayoutAdapter {
        LinkedHashMap<Integer, Fragment> mFragmentCache = new LinkedHashMap<>();

        public FragmentsAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public android.support.v4.app.Fragment getItem(int position,
                                                       android.support.v4.app.Fragment.SavedState savedState) {
            android.support.v4.app.Fragment f = mFragmentCache.containsKey(position)
                    ? mFragmentCache.get(position)
                    : new TourFragment();
            Log.e("test", "getItem:" + position + " from cache" + mFragmentCache.containsKey(position));
            if (savedState == null || f.getArguments() == null) {
                Bundle bundle = new Bundle();
                bundle.putInt("index", position);
                f.setArguments(bundle);
                Log.e("test", "setArguments:" + position);
            } else if (!mFragmentCache.containsKey(position)) {
                f.setInitialSavedState(savedState);
                Log.e("test", "setInitialSavedState:" + position);
            }
            mFragmentCache.put(position, f);
            return f;
        }

        @Override
        public void onDestroyItem(int position, android.support.v4.app.Fragment fragment) {
            // onDestroyItem
            while (mFragmentCache.size() > 5) {
                Object[] keys = mFragmentCache.keySet().toArray();
                mFragmentCache.remove(keys[0]);
            }
        }

        @Override
        public String getPageTitle(int position) {
            return "item-" + position;
        }

        @Override
        public int getItemCount() {
            return 2;
        }
    }


}
