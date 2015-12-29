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

public class MessageSetActivity extends BaseActivity implements OnClickListener {
    /**
     * ToggleButton列表
     *
     * @param tb_message_off 消息免打扰
     * @param tb_sound 声音
     * @param tb_vibrate 声音
     */
    ToggleButton tb_message_off, tb_sound, tb_vibrate;

    @Override
    protected int setContent() {
        // TODO Auto-generated method stub
        return R.layout.activity_message_set;
    }

    @Override
    protected void initViews() {
        // TODO Auto-generated method stub
        tb_message_off = (ToggleButton) findViewById(R.id.tb_message_set_message_off);
        tb_sound = (ToggleButton) findViewById(R.id.tb_message_set_sound);
        tb_vibrate = (ToggleButton) findViewById(R.id.tb_message_set_vibrate);

        tb_sound.setToggleOn();
        tb_vibrate.setToggleOn();

        tb_message_off.setOnToggleChanged(new ToggleButton.OnToggleChanged() {
            @Override
            public void onToggle(boolean on) {
                if (on) {
                    tb_sound.setToggleOff();
                    tb_vibrate.setToggleOff();
                    tb_sound.setEnabled(false);
                    tb_vibrate.setEnabled(false);
                } else {
                    tb_sound.setEnabled(true);
                    tb_vibrate.setEnabled(true);
                }
            }
        });

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

        tv_title_text.setText(R.string.message_set);


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

            default:
                break;
        }

    }


}
