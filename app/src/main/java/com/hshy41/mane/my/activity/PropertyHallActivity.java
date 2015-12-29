package com.hshy41.mane.my.activity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hshy41.mane.R;
import com.hshy41.mane.base.BaseActivity;

/**
 * 物业大厅页面
 *
 * @author lichao
 */
public class PropertyHallActivity extends BaseActivity implements View.OnClickListener {

    /**
     * 物业大厅选项
     *
     * @param rl_property_notification 物业通知
     * @param rl_property_repair 物业报修
     * @param rl_property_service 物业服务
     * @param rl_property_pay 物业缴费
     * @param rl_vintage_release 旧货发布
     */
    RelativeLayout rl_property_notification, rl_property_repair,
            rl_property_service, rl_property_pay, rl_vintage_release;


    @Override
    protected int setContent() {
        return R.layout.activity_property_hall;
    }

    @Override
    protected void initViews() {
        rl_property_notification = (RelativeLayout) findViewById(R.id.rl_property_hall_property_notification);
        rl_property_repair = (RelativeLayout) findViewById(R.id.rl_property_hall_property_repair);
        rl_property_service = (RelativeLayout) findViewById(R.id.rl_property_hall_property_service);
        rl_property_pay = (RelativeLayout) findViewById(R.id.rl_property_hall_property_pay);
        rl_vintage_release = (RelativeLayout) findViewById(R.id.rl_property_hall_property_vintage_release);

        rl_property_notification.setOnClickListener(this);
        rl_property_repair.setOnClickListener(this);
        rl_property_service.setOnClickListener(this);
        rl_property_pay.setOnClickListener(this);
        rl_vintage_release.setOnClickListener(this);
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

        tv_title_text.setText(R.string.property_hall);

        bt_title_left.setOnClickListener(this);
        rl_title_left.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.rl_title_left:
            case R.id.bt_title_left:
                finish();
                break;
            case R.id.rl_property_hall_property_notification://物业通知
                intent = new Intent(this, PropertyNotificationActivity.class);
                startActivity(intent);
                break;
            case R.id.rl_property_hall_property_repair://物业报修
                intent = new Intent(this, PropertyRepairActivity.class);
                startActivity(intent);
                break;
            case R.id.rl_property_hall_property_service://物业服务
                intent = new Intent(this, PropertyServiceActivity.class);
                startActivity(intent);
                break;
            case R.id.rl_property_hall_property_pay://物业缴费
                intent = new Intent(this, PropertyPayActivity.class);
                startActivity(intent);
                break;
            case R.id.rl_property_hall_property_vintage_release://旧货发布
                intent = new Intent(this, VintageListActivity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }
}
