package com.hshy41.mane.my.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
import com.hshy41.mane.entity.LoginEntity;
import com.hshy41.mane.utils.Cons;
import com.hshy41.mane.utils.ToastUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends BaseActivity implements OnClickListener {

    /**
     * 登录按键
     *
     * @return
     */
    Button bt_login;

    /**
     * 找回密码
     *
     * @return
     */
    TextView tv_findpassword;

    /**
     * 输入框
     *
     * @param et_phone 手机号
     * @param et_password 密码
     */
    EditText et_phone, et_password;

    LoginEntity data;
    LoginBaseBean bean;

    @Override
    protected int setContent() {
        // TODO Auto-generated method stub
        return R.layout.activity_login;
    }

    @Override
    protected void initViews() {
        // TODO Auto-generated method stub

        bt_login = (Button) findViewById(R.id.bt_login_login);
        tv_findpassword = (TextView) findViewById(R.id.tv_findpassword);
        et_phone = (EditText) findViewById(R.id.et_login_phone);
        et_password = (EditText) findViewById(R.id.et_login_password);

        tv_findpassword.setOnClickListener(this);
        bt_login.setOnClickListener(this);

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
        rl_title_right.setVisibility(View.VISIBLE);
        iv_title_gps.setVisibility(View.GONE);
        tv_title_text.setVisibility(View.VISIBLE);
        bt_title_right.setVisibility(View.GONE);
        bt_title_left.setVisibility(View.VISIBLE);
        tv_title_right.setVisibility(View.VISIBLE);

        tv_title_right.setText(R.string.regist);
        tv_title_text.setText(R.string.login);

        tv_title_right.setOnClickListener(this);
        bt_title_left.setOnClickListener(this);
        rl_title_left.setOnClickListener(this);
        rl_title_right.setOnClickListener(this);

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
            case R.id.bt_login_login:
                if (CheckLoginInfo())
                    Login();
                break;
            case R.id.rl_title_right:
            case R.id.tv_title_right:
                intent = new Intent(LoginActivity.this, RegistActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_findpassword:
                intent = new Intent(LoginActivity.this, FindPasswordActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }


    /**
     * 登陆
     */
    private void Login() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                Cons.DOMAIN + Cons.LOGIN,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        if (s != null) {
                            try {
                                JSONObject json = new JSONObject(s);
                                if (json.get("Result").equals("0")) {
                                    bean = MyApplication.gson.fromJson(s, LoginBaseBean.class);
                                    data = bean.data;
                                    //保存手机号
                                    MyApplication.user.setPhone(et_phone.getText().toString());
                                    //更新用户信息
                                    MyApplication.updataUserInfo(LoginActivity.this);
                                    Bundle bundle = new Bundle();
                                    bundle.putSerializable("loginentity", data);
                                    Intent intent = new Intent();
                                    intent.putExtras(bundle);
                                    setResult(Cons.RESULT_LOGIN_ACTIVITY, intent);
                                    finish();
                                } else if (json.get("Result").equals("1")) {
                                    ToastUtil.showToast(LoginActivity.this, json.getString("Message"));
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
                map.put("mobile", et_phone.getText().toString());
                map.put("password", et_password.getText().toString());
//                map.put("mobile", "18201310937");
//                map.put("password", "456789");
                return map;
            }
        };
        MyApplication.mQueue.add(stringRequest);
    }


    /**
     * 判断注册参数正确与否
     */
    private boolean CheckLoginInfo() {

        if (!Cons.isMobile(et_phone.getText().toString())) {
            ToastUtil.showToast(this, "请输入正确的手机号");
            return false;
        }

        if (et_password.getText() == null || et_password.getText().toString().equals("")) {
            ToastUtil.showToast(this, "密码不能为空");
            return false;
        }
        return true;
    }

}
