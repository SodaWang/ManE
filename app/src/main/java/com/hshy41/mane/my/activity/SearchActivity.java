package com.hshy41.mane.my.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hshy41.mane.R;
import com.hshy41.mane.adapter.recyclerviewadapter.ModuleDesign;
import com.hshy41.mane.adapter.recyclerviewadapter.Search;
import com.hshy41.mane.base.BaseActivity;
import com.hshy41.mane.layoutmanager.MyGridLayoutManager;
import com.hshy41.mane.utils.Cons;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends BaseActivity implements View.OnClickListener {
    /**
     * 数据源
     */
    List<int[]> mData;
    /**
     * 顶部信息表
     */
    RecyclerView rv_search_top;

    /**
     * 底部信息表
     */
    RecyclerView rv_search_bottom;

    @Override
    protected int setContent() {
        return R.layout.activity_search;
    }

    @Override
    protected void initViews() {

        mData = new ArrayList<int[]>();
        mData.add(Cons.STRIDARR_MODULEDESIGN_TOP);
        mData.add(Cons.STRIDARR_MODULEDESIGN_BOTTOM);
        //加载recyclerview
        rv_search_top = (RecyclerView) findViewById(R.id.rv_search_top);
        rv_search_bottom = (RecyclerView) findViewById(R.id.rv_search_bottom);

        //设置上方数据
        rv_search_top.setHasFixedSize(true);
        rv_search_top.setAdapter(new ModuleDesign(this, mData.get(0)));
        rv_search_top.setLayoutManager(new MyGridLayoutManager(this, 3, mData.get(0).length));
//        rv_getMonney.setItemAnimator(new DefaultItemAnimator());
        //设置下方数据
        rv_search_bottom.setHasFixedSize(true);
        rv_search_bottom.setAdapter(new Search(this, mData.get(1)));
        rv_search_bottom.setLayoutManager(new LinearLayoutManager(this));
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
        rl_title_search = (RelativeLayout) findViewById(R.id.rl_title_search);
    }

    @Override
    protected void setTitleBar() {
        rl_title_left.setVisibility(View.VISIBLE);
        rl_title_right.setVisibility(View.VISIBLE);
        iv_title_gps.setVisibility(View.GONE);
        tv_title_text.setVisibility(View.GONE);
        bt_title_right.setVisibility(View.GONE);
        bt_title_left.setVisibility(View.VISIBLE);
        tv_title_right.setVisibility(View.VISIBLE);
        rl_title_search.setVisibility(View.VISIBLE);

        tv_title_right.setText(R.string.search);

        tv_title_right.setOnClickListener(this);
        bt_title_left.setOnClickListener(this);
        rl_title_left.setOnClickListener(this);
        rl_title_right.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_title_left:
            case R.id.bt_title_left:
                finish();
                break;
        }
    }
}
