package com.hshy41.mane.my.activity;

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
import com.hshy41.mane.R;
import com.hshy41.mane.base.BaseActivity;
import com.hshy41.mane.bean.Identifyingcode_Regist_BaseBean;
import com.hshy41.mane.bean.RegistBaseBean;
import com.hshy41.mane.entity.Identifyingcode_Regist_Entity;
import com.hshy41.mane.entity.RegistEntity;
import com.hshy41.mane.utils.Cons;
import com.hshy41.mane.utils.ToastUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class RegistActivity extends BaseActivity implements OnClickListener {
    Gson gson;
    /**
     * 各种输入框
     *
     * @param et_phone 请输入手机号
     * @param identifyingcode 请输入验证码
     * @param password 请输入密码
     * @param confirm_password 确认密码
     * @param invitationcode 邀请码
     */
    EditText et_phone, et_identifyingcode, et_password,
            et_confirm_password, et_invitationcode;

    /**
     * 按钮
     *
     * @param bt_send_identifyingcode 发送验证码
     * @param bt_regist 注册
     */
    Button bt_send_identifyingcode, bt_regist;


    //Volley请求队列
    RequestQueue mQueue;


    @Override
    protected int setContent() {
        // TODO Auto-generated method stub
        return R.layout.activity_regist;
    }

    @Override
    protected void initViews() {
        // TODO Auto-generated method stub
        gson = new Gson();
        //初始化请求队列
        mQueue = Volley.newRequestQueue(context);

        //初始化各种控件
        //EditText组
        et_phone = (EditText) findViewById(R.id.et_regist_phone);
        et_identifyingcode = (EditText) findViewById(R.id.et_regist_identifyingcode);
        et_password = (EditText) findViewById(R.id.et_regist_password);
        et_confirm_password = (EditText) findViewById(R.id.et_regist_confirmpassword);
        et_invitationcode = (EditText) findViewById(R.id.et_regist_invitationcode);
        //Button组
        bt_send_identifyingcode = (Button) findViewById(R.id.bt_regist_send_identifyingcode);
        bt_regist = (Button) findViewById(R.id.bt_regist_regist);

        bt_send_identifyingcode.setOnClickListener(this);
        bt_regist.setOnClickListener(this);
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

        tv_title_text.setText(R.string.regist);
        bt_title_left.setOnClickListener(this);
        rl_title_left.setOnClickListener(this);

    }

    @Override
    public void onClick(View arg0) {
        // TODO Auto-generated method stub
        switch (arg0.getId()) {
            case R.id.rl_title_left:
            case R.id.bt_title_left:
                finish();
                break;
            case R.id.bt_regist_send_identifyingcode:
                if (Cons.isMobile(et_phone.getText().toString())) {
                    GetIdentifyingcode();
                } else {
                    ToastUtil.showToast(this, "请输入正确的手机号");
                }
                break;
            case R.id.bt_regist_regist:
                if (CheckRegistInfo())
                    Regist();
                break;
            default:
                break;
        }
    }

    /**
     * 获取验证码
     */
    private void GetIdentifyingcode() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                Cons.DOMAIN + Cons.IDENTIFYING_CODE_REGIST,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        if (s != null) {
                            try {
                                JSONObject json = new JSONObject(s);
                                if (json.get("Result").equals("0")) {
                                    Identifyingcode_Regist_BaseBean bean = gson.fromJson(s, Identifyingcode_Regist_BaseBean.class);
                                    Identifyingcode_Regist_Entity data = bean.data;
                                } else if (json.get("Result").equals("1")) {
                                    ToastUtil.showToast(RegistActivity.this, json.getString("Message"));
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
                return map;
            }
        };
        mQueue.add(stringRequest);
    }

    /**
     * 注册
     */
    private void Regist() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                Cons.DOMAIN + Cons.REGIST,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        if (s != null) {
                            try {
                                JSONObject json = new JSONObject(s);
                                if (json.get("Result").equals("0")) {
                                    RegistBaseBean bean = gson.fromJson(s, RegistBaseBean.class);
                                    RegistEntity data = bean.data;
                                } else if (json.get("Result").equals("1")) {
                                    ToastUtil.showToast(RegistActivity.this, json.getString("Message"));
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
                map.put("yzm", et_identifyingcode.getText().toString());
                map.put("yqm", et_invitationcode.getText().toString());
                return map;
            }
        };
        mQueue.add(stringRequest);
    }


    /**
     * 判断注册参数正确与否
     */
    private boolean CheckRegistInfo() {
        if (!Cons.isMobile(et_phone.getText().toString())) {
            ToastUtil.showToast(RegistActivity.this, "请输入正确的手机号");
            return false;
        }

        if (et_identifyingcode.getText().toString().equals("") || et_identifyingcode.getText() == null) {
            ToastUtil.showToast(RegistActivity.this, "请输入验证码");
            return false;
        }

        if (et_password.getText().toString().equals("") || et_password.getText() == null) {
            ToastUtil.showToast(RegistActivity.this, "请输入密码");
            return false;
        }

        if (et_confirm_password.getText().toString().equals("") || et_confirm_password.getText() == null) {
            ToastUtil.showToast(RegistActivity.this, "请确认密码");
            return false;
        }

        if (!et_confirm_password.getText().toString().equals(et_password.getText().toString())) {
            ToastUtil.showToast(RegistActivity.this, "两次输入的密码不一致");
            return false;
        }
        return true;
    }


}