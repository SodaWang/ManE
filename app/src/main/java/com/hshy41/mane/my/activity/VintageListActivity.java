package com.hshy41.mane.my.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
/**
 * @author lichao
 * 各种次级页面
 */
import com.hshy41.mane.R;
import com.hshy41.mane.adapter.recyclerviewadapter.VintageList;
import com.hshy41.mane.base.BaseFragment;
import com.hshy41.mane.fragment.ZhouBianShangHuFragment;
import com.lsjwzh.widget.recyclerviewpager.FragmentStatePagerAdapter;
import com.lsjwzh.widget.recyclerviewpager.RecyclerViewPager;
import com.lsjwzh.widget.recyclerviewpager.TabLayoutSupport;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * 旧货列表页面
 */
public class VintageListActivity extends AppCompatActivity implements View.OnClickListener {


    /**
     * 左布局
     */
    protected RelativeLayout rl_title_left;
    /**
     * 返回钮
     */
    protected Button bt_title_left;

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

    /**
     * 旧货列表
     * @param savedInstanceState
     */
    protected RecyclerView rv_vintage_list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vintage_list);

        rv_vintage_list = (RecyclerView) findViewById(R.id.rv_vintage_list);
        VintageList adapter = new VintageList(this);
        rv_vintage_list.setAdapter(adapter);
        rv_vintage_list.setLayoutManager(new LinearLayoutManager(this));
        initTitleBar();
        setTitleBar();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search, menu);
        return true;
    }


    private void initTitleBar() {

        rl_title_left = (RelativeLayout) findViewById(R.id.rl_title_left);
        iv_title_gps = (ImageView) findViewById(R.id.iv_title_gps);
        tv_title_text = (TextView) findViewById(R.id.tv_title_text);
        bt_title_right = (Button) findViewById(R.id.bt_title_right);
        bt_title_left = (Button) findViewById(R.id.bt_title_left);
        rl_title_right = (RelativeLayout) findViewById(R.id.rl_title_right);
    }


    private void setTitleBar() {
        rl_title_left.setVisibility(View.VISIBLE);
        iv_title_gps.setVisibility(View.GONE);
        tv_title_text.setVisibility(View.VISIBLE);
        bt_title_right.setVisibility(View.VISIBLE);

        tv_title_text.setText(R.string.vintage_list);


        bt_title_right.setOnClickListener(this);
        rl_title_right.setOnClickListener(this);
        rl_title_left.setOnClickListener(this);
        bt_title_left.setOnClickListener(this);
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
                intent = new Intent(this, GoodsReleaseActivity.class);
                startActivity(intent);
                break;
        }

    }


}
