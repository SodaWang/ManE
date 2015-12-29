package com.hshy41.mane.my.activity;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hshy41.mane.R;
import com.hshy41.mane.base.BaseActivity;
import com.zcw.togglebutton.ToggleButton;

public class SetActivity extends BaseActivity implements OnClickListener {

    /**
     * 安全退出按钮
     *
     * @return
     */
    Button bt_safe_quit;

    /**
     * 个人信息设置布局
     *
     * @return
     */
    RelativeLayout rl_personal_info_set;

    /**
     * 帐号安全布局
     *
     * @return
     */
    RelativeLayout rl_account_safe;

    /**
     * 消息设置布局
     *
     * @return
     */
    RelativeLayout rl_message_set;

    /**
     * 意见反馈布局
     *
     * @return
     */
    RelativeLayout rl_suggestion_post;

    /**
     * 关于我们布局
     *
     * @return
     */
    RelativeLayout rl_about_us;

    /**
     * 产品介绍布局
     *
     * @return
     */
    RelativeLayout rl_product_introduce;

    /**
     * 信息推送开关
     *
     * @return
     */
    ToggleButton tg_info_post;

    @Override
    protected int setContent() {
        // TODO Auto-generated method stub
        return R.layout.activity_set;
    }

    @Override
    protected void initViews() {
        // TODO Auto-generated method stub
        bt_safe_quit = (Button) findViewById(R.id.bt_set_safe_quit);
        rl_personal_info_set = (RelativeLayout) findViewById(R.id.rl_set_personal_info_set);
        rl_account_safe = (RelativeLayout) findViewById(R.id.rl_set_account_safe);
        rl_message_set = (RelativeLayout) findViewById(R.id.rl_set_message_set);
        tg_info_post = (ToggleButton) findViewById(R.id.tg_set_info_post);
        rl_suggestion_post = (RelativeLayout) findViewById(R.id.rl_set_suggestion_post);
        rl_about_us = (RelativeLayout) findViewById(R.id.rl_set_about_us);
        rl_product_introduce = (RelativeLayout) findViewById(R.id.rl_set_product_introduces);

        tg_info_post.setToggleOn();

        rl_product_introduce.setOnClickListener(this);
        rl_about_us.setOnClickListener(this);
        rl_suggestion_post.setOnClickListener(this);
        rl_message_set.setOnClickListener(this);
        rl_account_safe.setOnClickListener(this);
        rl_personal_info_set.setOnClickListener(this);
        bt_safe_quit.setOnClickListener(this);

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
    }

    @Override
    protected void setTitleBar() {
        rl_title_left.setVisibility(View.VISIBLE);
        rl_title_right.setVisibility(View.GONE);
        iv_title_gps.setVisibility(View.GONE);
        tv_title_text.setVisibility(View.VISIBLE);
        bt_title_right.setVisibility(View.GONE);
        bt_title_left.setVisibility(View.VISIBLE);

        tv_title_text.setText(R.string.set);
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
            case R.id.bt_set_safe_quit:
                finish();
                break;
            case R.id.rl_set_personal_info_set:
                intent = new Intent(this, PersonalInfoSetActivity.class);
                startActivity(intent);
                break;
            case R.id.rl_set_account_safe:
                intent = new Intent(this, ChangePasswordActivity.class);
                startActivity(intent);
                break;
            case R.id.rl_set_message_set:
                intent = new Intent(this, MessageSetActivity.class);
                startActivity(intent);
                break;
            case R.id.rl_set_suggestion_post:
                intent = new Intent(this, SuggestionPostActivity.class);
                startActivity(intent);
                break;
            case R.id.rl_set_about_us:
                intent = new Intent(this, AboutUsActivity.class);
                startActivity(intent);
                break;
            case R.id.rl_set_product_introduces:
                break;
            default:
                break;
        }
    }

}
