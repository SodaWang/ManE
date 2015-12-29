package com.hshy41.mane.my.activity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hshy41.mane.R;
import com.hshy41.mane.adapter.recyclerviewadapter.AD;
import com.hshy41.mane.adapter.recyclerviewadapter.ADItemDecoration;
import com.hshy41.mane.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * 广告页面
 *
 * @author lichao
 */
public class ADActivity extends BaseActivity implements View.OnClickListener {

    /**
     * 数据源
     */
    List<Drawable> mData = new ArrayList<Drawable>();
    /**
     * 广告信息列表
     */

    private RecyclerView rv_ad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected int setContent() {
        return R.layout.activity_ad;
    }

    @Override
    protected void initViews() {
        rv_ad = (RecyclerView) findViewById(R.id.rv_ad);
        AD adpater = new AD(this);
        adpater.setOnItemClickLitener(new AD.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {

                Intent intent = new Intent(ADActivity.this, WebActivity.class);
                startActivity(intent);
            }
        });
        rv_ad.setAdapter(adpater);
        rv_ad.setLayoutManager(new GridLayoutManager(this, 2));
        rv_ad.setHasFixedSize(true);
        rv_ad.addItemDecoration(new ADItemDecoration(this));
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

        tv_title_text.setText(R.string.ad);

        rl_title_right.setOnClickListener(this);
        bt_title_right.setOnClickListener(this);
        bt_title_left.setOnClickListener(this);
        rl_title_left.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_title_left:
            case R.id.bt_title_left:
                finish();
                break;
            case R.id.rl_title_right:
            case R.id.bt_title_right:
                Intent intent = new Intent(this, SearchActivity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }

}
