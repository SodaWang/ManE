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
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
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

public class ChangePasswordActivity extends BaseActivity implements OnClickListener {

    /**
     * 保存按键
     *
     * @return
     */
    Button bt_save;

    /**
     * 输入框
     *
     * @param et_enter_old_password 输入旧密码
     * @param et_enter_new_password 输入新密码
     */
    EditText et_enter_old_password, et_enter_new_password;

    @Override
    protected int setContent() {
        // TODO Auto-generated method stub
        return R.layout.activity_change_password;
    }

    @Override
    protected void initViews() {
        // TODO Auto-generated method stub
        et_enter_old_password = (EditText) findViewById(R.id.et_change_password_enter_old_password);
        et_enter_new_password = (EditText) findViewById(R.id.et_change_password_confirm_password);
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
                //检查输入信息
                if (CheckChangePasswordInfo()) {
                    //联网更换密码
                    changePassword();
                }
                break;
            default:
                break;
        }
    }


    /**
     * 更换密码
     */
    private void changePassword() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                Cons.DOMAIN + Cons.CHANGE_PASSWORD,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        if (s != null) {
                            try {
                                JSONObject json = new JSONObject(s);
                                if (json.get("Result").equals("0")) {
                                    ToastUtil.showToast(ChangePasswordActivity.this, "密码更换成功");
                                } else if (json.get("Result").equals("1")) {
                                    ToastUtil.showToast(ChangePasswordActivity.this, json.getString("Message"));
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
                map.put("mobile", MyApplication.user.getPhone());
                map.put("oldpass", et_enter_old_password.getText().toString());
                map.put("newpass", et_enter_new_password.getText().toString());
                return map;
            }
        };
        MyApplication.mQueue.add(stringRequest);
    }

    /**
     * 判断修改密码参数正确与否
     */
    private boolean CheckChangePasswordInfo() {

        if (et_enter_old_password.getText().toString().equals("") || et_enter_old_password.getText() == null) {
            ToastUtil.showToast(this, "请输入旧密码");
            return false;
        }

        if (et_enter_new_password.getText().toString().equals("") || et_enter_new_password.getText() == null) {
            ToastUtil.showToast(this, "请输入新密码");
            return false;
        }

        return true;
    }

}
