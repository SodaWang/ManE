package com.hshy41.mane.my.activity;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.hshy41.mane.R;
import com.hshy41.mane.base.BaseActivity;

public class ChangePasswordActivity extends BaseActivity implements OnClickListener {
    //Volley请求队列
    RequestQueue mQueue;
    //Gson
    Gson gson;
    /**
     * 保存按键
     * @return
     */
    Button bt_save;

    @Override
    protected int setContent() {
        // TODO Auto-generated method stub
        return R.layout.activity_change_password;
    }

    @Override
    protected void initViews() {
        // TODO Auto-generated method stub
        //初始化Gson
        gson = new Gson();
        //初始化请求队列
        mQueue = Volley.newRequestQueue(context);

        bt_save = (Button) findViewById(R.id.bt_change_password_save);

        bt_save.setOnClickListener(this);
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

        tv_title_text.setText(R.string.change_password);


        bt_title_left.setOnClickListener(this);
        rl_title_left.setOnClickListener(this);

    }

    @Override
    public void onClick(View arg0) {
        // TODO Auto-generated method stub
        Intent intent;
        switch (arg0.getId()) {
            case R.id.rl_title_left:
            case R.id.bt_title_left:
                finish();
                break;
            case R.id.bt_change_password_save:
                finish();
                break;
            default:
                break;
        }
    }

}
