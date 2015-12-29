package com.hshy41.mane.my.activity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hshy41.mane.R;
import com.hshy41.mane.adapter.recyclerviewadapter.DeafaultItemDecoration;
import com.hshy41.mane.adapter.recyclerviewadapter.PropertyNotification;
import com.hshy41.mane.base.BaseActivity;

/**
 * 物业通知页面
 *
 * @author lichao
 */
public class PropertyNotificationActivity extends BaseActivity implements View.OnClickListener {

    /**
     * 物业通知列表
     */
    RecyclerView rv_property_notification;

    @Override
    protected int setContent() {
        return R.layout.activity_property_notification;
    }

    @Override
    protected void initViews() {
        rv_property_notification = (RecyclerView) findViewById(R.id.rv_property_notification);
        PropertyNotification adapter = new PropertyNotification(this);
        rv_property_notification.setAdapter(adapter);
        rv_property_notification.setLayoutManager(new LinearLayoutManager(this));
        rv_property_notification.addItemDecoration(
                new DeafaultItemDecoration(this, DeafaultItemDecoration.VERTICAL_LIST));
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

        tv_title_text.setText(R.string.property_notification);

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

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }
}
