package com.hshy41.mane.my.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hshy41.mane.R;
import com.hshy41.mane.base.BaseActivity;

/**
 * 账户中心页面
 *
 * @author lichao
 */
public class AccountActivity extends BaseActivity implements View.OnClickListener {

    RelativeLayout rl_transcation_details, rl_cash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected int setContent() {
        return R.layout.activity_account;
    }

    @Override
    protected void initViews() {
        rl_transcation_details = (RelativeLayout) findViewById(R.id.rl_account_transaction_details);
        rl_cash = (RelativeLayout) findViewById(R.id.rl_account_cash);
        rl_transcation_details.setOnClickListener(this);
        rl_cash.setOnClickListener(this);
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

        tv_title_text.setText(R.string.accuontcenter);

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
            case R.id.rl_account_transaction_details:
                intent = new Intent(this, TransactionDetailsActivity.class);
                startActivity(intent);
                break;
            case R.id.rl_account_cash:
                intent = new Intent(this, CashActivity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }
}