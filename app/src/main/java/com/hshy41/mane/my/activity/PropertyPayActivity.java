package com.hshy41.mane.my.activity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hshy41.mane.R;
import com.hshy41.mane.base.BaseActivity;

/**
 * 物业缴费页面
 *
 * @author lichao
 */
public class PropertyPayActivity extends BaseActivity implements View.OnClickListener {

    /**
     * 选中按钮
     *
     * @param iv_personal 个人
     * @param iv_company 单位
     * @param iv_pick_the_invoice 自取发票
     * @param iv_send_home 送货上门
     */
    ImageView iv_personal, iv_company, iv_pick_the_invoice, iv_send_home;

    @Override
    protected int setContent() {
        return R.layout.activity_property_pay;
    }

    @Override
    protected void initViews() {
        iv_personal = (ImageView) findViewById(R.id.iv_property_pay_personal);
        iv_company = (ImageView) findViewById(R.id.iv_property_pay_company);
        iv_pick_the_invoice = (ImageView) findViewById(R.id.iv_property_pay_pick_the_invoice);
        iv_send_home = (ImageView) findViewById(R.id.iv_property_pay_send_home);

        iv_personal.setOnClickListener(this);
        iv_company.setOnClickListener(this);
        iv_pick_the_invoice.setOnClickListener(this);
        iv_send_home.setOnClickListener(this);
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

        tv_title_text.setText(R.string.property_pay);

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
            case R.id.iv_property_pay_personal:
                iv_personal.setBackgroundResource(R.drawable.danxuankuang);
                iv_company.setBackgroundResource(R.drawable.danxuankuang2);
                break;
            case R.id.iv_property_pay_company:
                iv_personal.setBackgroundResource(R.drawable.danxuankuang2);
                iv_company.setBackgroundResource(R.drawable.danxuankuang);
                break;
            case R.id.iv_property_pay_pick_the_invoice:
                iv_pick_the_invoice.setBackgroundResource(R.drawable.danxuankuang);
                iv_send_home.setBackgroundResource(R.drawable.danxuankuang2);
                break;
            case R.id.iv_property_pay_send_home:
                iv_pick_the_invoice.setBackgroundResource(R.drawable.danxuankuang2);
                iv_send_home.setBackgroundResource(R.drawable.danxuankuang);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }
}
