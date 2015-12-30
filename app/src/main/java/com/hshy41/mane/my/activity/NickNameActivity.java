package com.hshy41.mane.my.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.hshy41.mane.MyApplication;
import com.hshy41.mane.R;
import com.hshy41.mane.base.BaseActivity;
import com.hshy41.mane.bean.LoginBaseBean;
import com.hshy41.mane.utils.Cons;
import com.hshy41.mane.utils.ToastUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class NickNameActivity extends BaseActivity implements OnClickListener {

    /**
     * 昵称输入框
     *
     * @return
     */
    EditText et_nickname;


    @Override
    protected int setContent() {
        // TODO Auto-generated method stub
        return R.layout.activity_nick_name;
    }

    @Override
    protected void initViews() {
        // TODO Auto-generated method stub
        et_nickname = (EditText) findViewById(R.id.et_nickname);

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
        rl_title_right.setVisibility(View.VISIBLE);
        iv_title_gps.setVisibility(View.GONE);
        tv_title_text.setVisibility(View.VISIBLE);
        bt_title_right.setVisibility(View.GONE);
        bt_title_left.setVisibility(View.VISIBLE);
        tv_title_right.setVisibility(View.VISIBLE);

        tv_title_text.setText(R.string.set);
        tv_title_right.setText(R.string.save);

        bt_title_left.setOnClickListener(this);
        rl_title_left.setOnClickListener(this);
        rl_title_right.setOnClickListener(this);
        tv_title_right.setOnClickListener(this);

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
            case R.id.tv_title_right:
            case R.id.rl_title_right:
                if (CheckNicknameInfo()) {
                    changeNickname();

                }

            default:
                break;
        }
    }

    /**
     * 修改昵称
     */
    private void changeNickname() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                Cons.DOMAIN + Cons.CHANGE_NICKNAME,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        if (s != null) {
                            try {
                                JSONObject json = new JSONObject(s);
                                if (json.get("Result").equals("0")) {
                                    //更新昵称
                                    MyApplication.user.setNickname(et_nickname.getText().toString());
                                    MyApplication.updataUserInfo(NickNameActivity.this);
                                    //成功信息
                                    ToastUtil.showToast(NickNameActivity.this, json.getString("Message"));
                                    finish();
                                } else if (json.get("Result").equals("1")) {
                                    //失败信息
                                    ToastUtil.showToast(NickNameActivity.this, json.getString("Message"));
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<String, String>();
                map.put("uid", MyApplication.user.getId());
                map.put("cnname", et_nickname.getText().toString());
                return map;
            }
        };
        MyApplication.mQueue.add(stringRequest);
    }


    /**
     * 判断昵称正确与否
     */
    private boolean CheckNicknameInfo() {
        if (et_nickname.getText() == null || et_nickname.getText().toString().equals("")) {
            ToastUtil.showToast(this, "昵称不能为空");
            return false;
        }
        return true;
    }

}
