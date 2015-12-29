package com.hshy41.mane.my.activity;

import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hshy41.mane.R;
import com.hshy41.mane.base.BaseActivity;

public class PropertyRepairActivity extends BaseActivity implements View.OnClickListener {
    /**
     * 一键拨号布局
     */
    private RelativeLayout rl_one_key_phone;

    /**
     * 公共报修布局
     *
     * @return
     */
    private RelativeLayout rl_public_repair;

    @Override
    protected int setContent() {
        return R.layout.activity_property_repair;
    }

    @Override
    protected void initViews() {
        rl_one_key_phone =
                (RelativeLayout) findViewById(R.id.rl_property_repair_one_key_phone);
        rl_public_repair = (RelativeLayout) findViewById(R.id.rl_property_repair_public_repair);

        rl_public_repair.setOnClickListener(this);
        rl_one_key_phone.setOnClickListener(this);
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

        tv_title_text.setText(R.string.property_repair);

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
            case R.id.rl_property_repair_one_key_phone:
                intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("tel://110"));
                startActivity(intent);
                break;
            case R.id.rl_property_repair_public_repair:
                intent = new Intent(this, PublicRepairTableActivity.class);
                startActivity(intent);
                break;
        }
    }
}
