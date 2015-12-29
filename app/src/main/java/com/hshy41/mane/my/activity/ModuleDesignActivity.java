package com.hshy41.mane.my.activity;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hshy41.mane.R;
import com.hshy41.mane.adapter.recyclerviewadapter.ModuleDesign;
import com.hshy41.mane.base.BaseActivity;
import com.hshy41.mane.layoutmanager.MyGridLayoutManager;
import com.hshy41.mane.utils.Cons;

import java.util.ArrayList;
import java.util.List;

public class ModuleDesignActivity extends BaseActivity implements View.OnClickListener{
    /**
     * 数据源
     */
    List<int[]> mData;

    /**
     * 信息列表
     */
    RecyclerView rv_moduledesign_top;
    RecyclerView rv_moduledesign_bottom;

    @Override
    protected int setContent() {
        return R.layout.activity_moduledesign;
    }

    @Override
    protected void initViews() {
        mData = new ArrayList<int[]>();
        mData.add(Cons.STRIDARR_MODULEDESIGN_TOP);
        mData.add(Cons.STRIDARR_MODULEDESIGN_BOTTOM);
        //加载recyclerview
        rv_moduledesign_top = (RecyclerView) findViewById(R.id.rv_moduledesign_top);
        rv_moduledesign_bottom = (RecyclerView) findViewById(R.id.rv_moduledesign_bottom);

        //设置上方数据
        rv_moduledesign_top.setHasFixedSize(true);
        rv_moduledesign_top.setAdapter(new ModuleDesign(this, mData.get(0)));
        rv_moduledesign_top.setLayoutManager(new MyGridLayoutManager(this, 3, mData.get(0).length));
//        rv_getMonney.setItemAnimator(new DefaultItemAnimator());
        //设置下方数据
        rv_moduledesign_bottom.setHasFixedSize(true);
        rv_moduledesign_bottom.setAdapter(new ModuleDesign(this, mData.get(1)));
        rv_moduledesign_bottom.setLayoutManager(new GridLayoutManager(this, 3));
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

        tv_title_text.setText(R.string.moduledesign);

        rl_title_left.setOnClickListener(this);
        bt_title_left.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rl_title_left:
            case R.id.bt_title_left:
                finish();
                break;
        }

    }
}
