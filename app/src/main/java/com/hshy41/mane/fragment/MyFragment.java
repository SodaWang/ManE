package com.hshy41.mane.fragment;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.hshy41.mane.MyApplication;
import com.hshy41.mane.R;
import com.hshy41.mane.base.BaseFragment;
import com.hshy41.mane.entity.LoginEntity;
import com.hshy41.mane.my.activity.AccountActivity;
import com.hshy41.mane.my.activity.LoginActivity;
import com.hshy41.mane.my.activity.MyCollectActivity;
import com.hshy41.mane.my.activity.RegistActivity;
import com.hshy41.mane.my.activity.SetActivity;
import com.hshy41.mane.utils.AMapUtils;
import com.hshy41.mane.utils.Cons;

import com.hshy41.mane.utils.ToastUtil;
import com.nostra13.universalimageloader.core.ImageLoader;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MyFragment extends BaseFragment implements OnClickListener {

    /**
     * 已登录布局
     */
    RelativeLayout rl_logined;
    /**
     * 未登录布局
     */
    LinearLayout ll_unlogin;

    /**
     * 登录按钮
     */
    TextView tv_login;

    /**
     * 注册按钮
     */
    TextView tv_regist;

    /**
     * 我的收藏布局
     */
    RelativeLayout rl_mycollect;
    /**
     * 账户中心布局
     */
    RelativeLayout rl_accountcenter;

    /**
     * 账户uid
     */
    TextView tv_my_uid;

    /**
     * 昵称
     */
    TextView tv_my_nickname;

    /**
     * 头像
     */
    ImageView iv_my_head;

    /**
     * 签到
     */
    ImageView iv_sign_in;

    /**
     * 缓存值
     */
    TextView tv_cache;



    @Override
    public void onResume() {
        super.onResume();
        //检查是否登录过
        if (MyApplication.checkIsLogin(getActivity())) {
            rl_logined.setVisibility(View.VISIBLE);
            ll_unlogin.setVisibility(View.GONE);
            //设置id
            tv_my_uid.setText(getResources().getString(R.string.accuontid)
                    + MyApplication.user.getId());
            //设置头像
            ImageLoader.getInstance().displayImage(MyApplication.user.getFace(),
                    iv_my_head, MyApplication.options);
            //设置昵称
            tv_my_nickname.setText(MyApplication.user.getNickname());
        } else {
            rl_logined.setVisibility(View.GONE);
            ll_unlogin.setVisibility(View.VISIBLE);
            //设置头像
            iv_my_head.setImageResource(R.drawable.a2);
        }

    }



    @Override
    protected void setTitleBar() {
        rl_title_layout.setVisibility(View.VISIBLE);
        rl_title_left.setVisibility(View.GONE);
        rl_title_right.setVisibility(View.VISIBLE);
        tv_title_text.setVisibility(View.VISIBLE);
        iv_title_gps.setVisibility(View.GONE);
        bt_title_right.setVisibility(View.VISIBLE);
        bt_title_left.setVisibility(View.GONE);
        tv_title_right.setVisibility(View.GONE);

        tv_title_text.setText(R.string.my);
        bt_title_right.setBackgroundResource(R.drawable.bt_set);
        // 正确显示titlebar
        rl_title_layout.setFocusable(true);
        rl_title_layout.setFocusableInTouchMode(true);
        rl_title_layout.requestFocus();

        rl_title_right.setOnClickListener(this);
        bt_title_right.setOnClickListener(this);
        bt_title_left.setOnClickListener(this);
        rl_title_left.setOnClickListener(this);
    }

    @Override
    protected void initTitleBar(View view) {
        rl_title_left = (RelativeLayout) view.findViewById(R.id.rl_title_left);
        rl_title_right = (RelativeLayout) view.findViewById(R.id.rl_title_right);
        iv_title_gps = (ImageView) view.findViewById(R.id.iv_title_gps);
        tv_title_text = (TextView) view.findViewById(R.id.tv_title_text);
        bt_title_right = (Button) view.findViewById(R.id.bt_title_right);
        bt_title_left = (Button) view.findViewById(R.id.bt_title_left);
        tv_title_right = (TextView) view.findViewById(R.id.tv_title_right);
        rl_title_layout = (RelativeLayout) view.findViewById(R.id.title_layout);
        tv_my_uid = (TextView) view.findViewById(R.id.tv_my_uid);
        tv_my_nickname = (TextView) view.findViewById(R.id.tv_my_nickname);

    }

    @Override
    protected int setLayoutID() {
        // TODO Auto-generated method stub
        return R.layout.fragment_my;
    }

    @Override
    protected void initView(View view) {
        // TODO Auto-generated method stub

        rl_logined = (RelativeLayout) view.findViewById(R.id.rl_logined);
        ll_unlogin = (LinearLayout) view.findViewById(R.id.ll_unlogin);
        tv_login = (TextView) view.findViewById(R.id.tv_login);
        tv_regist = (TextView) view.findViewById(R.id.tv_regist);
        rl_mycollect = (RelativeLayout) view.findViewById(R.id.rl_my_mycollect);
        rl_accountcenter = (RelativeLayout) view.findViewById(R.id.rl_my_accountcenter);
        tv_my_nickname = (TextView) view.findViewById(R.id.tv_my_nickname);
        iv_my_head = (ImageView) view.findViewById(R.id.iv_my_head);
        iv_sign_in = (ImageView) view.findViewById(R.id.iv_my_sign_in);




        iv_sign_in.setOnClickListener(this);
        rl_accountcenter.setOnClickListener(this);
        rl_mycollect.setOnClickListener(this);
        tv_login.setOnClickListener(this);
        tv_regist.setOnClickListener(this);


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        switch (requestCode) {
            case Cons.REQUEST_DEAFAULT:
                switch (resultCode) {
                    // 根据登录情况设置布局显示
                    case Cons.RESULT_LOGIN_ACTIVITY:
                        if (data.getExtras() != null) {
                            LoginEntity loginentity = (LoginEntity) data.getExtras().getSerializable("loginentity");
                            rl_logined.setVisibility(View.VISIBLE);
                            ll_unlogin.setVisibility(View.GONE);
                            String id = loginentity.getId();
                            MyApplication.user.setId(id);
                            MyApplication.user.setFace(loginentity.getAvatar());
                            MyApplication.user.setNickname(loginentity.getCnname());
                            MyApplication.updataUserInfo(getActivity());
                            tv_my_uid.setText(getResources().getString(R.string.accuontid)
                                    + id);
                            tv_my_nickname.setText(loginentity.getCnname());
                            /**
                             * 显示图片
                             * 参数1：图片url
                             * 参数2：显示图片的控件
                             * 参数3：显示图片的设置
                             * 参数4：监听器
                             */
                            ImageLoader.getInstance().displayImage(loginentity.getAvatar(), iv_my_head, MyApplication.options);
                            ToastUtil.showLongToast(getActivity(), "登陆成功");
                        }

                        break;
                    default:
//                        rl_logined.ssetVisibilityetVisibility(View.VISIBLE);
//                        ll_unlogin.(View.GONE);
                        break;
                }

        }
    }

    @Override
    public void onClick(View arg0) {
        Intent intent;
        switch (arg0.getId()) {
            case R.id.tv_login:
                intent = new Intent(getActivity(), LoginActivity.class);
                getActivity().setResult(Cons.RESULT_LOGIN_ACTIVITY);
                getActivity().startActivityForResult(intent, Cons.REQUEST_DEAFAULT);
                break;
            case R.id.tv_regist:
                intent = new Intent(getActivity(), RegistActivity.class);
                getActivity().startActivity(intent);
                break;
            case R.id.rl_my_mycollect:
                intent = new Intent(getActivity(), MyCollectActivity.class);
                getActivity().startActivity(intent);
                break;
            case R.id.rl_my_accountcenter:
                intent = new Intent(getActivity(), AccountActivity.class);
                getActivity().startActivity(intent);
                break;
            case R.id.bt_title_right:
            case R.id.rl_title_right:
                intent = new Intent(getActivity(), SetActivity.class);
                getActivity().startActivity(intent);
                break;
            case R.id.iv_my_sign_in:
                signIn();
                break;
            default:
                break;
        }
    }

//    /**
//     * 图片加载第一次显示监听器
//     *
//     * @author Administrator
//     */
//    private static class AnimateFirstDisplayListener extends SimpleImageLoadingListener {
//
//        static final List<String> displayedImages = Collections.synchronizedList(new LinkedList<String>());
//
//        @Override
//        public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
//            if (loadedImage != null) {
//                ImageView imageView = (ImageView) view;
//                // 是否第一次显示
//                boolean firstDisplay = !displayedImages.contains(imageUri);
//                if (firstDisplay) {
//                    // 图片淡入效果
//                    FadeInBitmapDisplayer.animate(imageView, 500);
//                    displayedImages.add(imageUri);
//                }
//            }
//        }
//    }

    /**
     * 每日签到
     */
    private void signIn() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                Cons.DOMAIN + Cons.SIGN_IN,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        if (s != null) {
                            try {
                                JSONObject json = new JSONObject(s);
                                if (json.get("Result").equals("0")) {
                                    ToastUtil.showToast(getActivity(), json.getString("Message"));
                                } else if (json.get("Result").equals("1")) {
                                    ToastUtil.showToast(getActivity(), json.getString("Message"));
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
                return map;
            }
        };
        MyApplication.mQueue.add(stringRequest);
    }


}
