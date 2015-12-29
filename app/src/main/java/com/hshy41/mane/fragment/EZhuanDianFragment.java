package com.hshy41.mane.fragment;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hshy41.mane.R;
import com.hshy41.mane.base.BaseFragment;
import com.hshy41.mane.my.activity.AccountActivity;
import com.hshy41.mane.my.activity.GetMonneyGameActivity;
import com.hshy41.mane.my.activity.LoginActivity;
import com.hshy41.mane.my.activity.MyCollectActivity;
import com.hshy41.mane.my.activity.RegistActivity;
import com.hshy41.mane.my.activity.SearchActivity;
import com.hshy41.mane.my.activity.SetActivity;
import com.hshy41.mane.utils.Cons;

public class EZhuanDianFragment extends BaseFragment implements OnClickListener {
    /**
     * 游戏赚钱标题布局
     */
    RelativeLayout rl_getmonney_ganme;
    /**
     * APP推荐标题布局
     */
    RelativeLayout rl_app_suggets_ganme;

    @Override
    protected void setTitleBar() {
        rl_title_layout.setVisibility(View.VISIBLE);
        rl_title_left.setVisibility(View.VISIBLE);
        rl_title_right.setVisibility(View.VISIBLE);
        tv_title_text.setVisibility(View.VISIBLE);
        iv_title_gps.setVisibility(View.GONE);
        bt_title_right.setVisibility(View.VISIBLE);
        bt_title_left.setVisibility(View.VISIBLE);
        tv_title_right.setVisibility(View.GONE);

        tv_title_text.setText(R.string.ezhuandian);
        bt_title_right.setBackgroundResource(R.drawable.sousuo);
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
        rl_title_layout = (RelativeLayout) view.findViewById(R.id.rl_titlebar);
    }

    @Override
    protected int setLayoutID() {
        // TODO Auto-generated method stub
        return R.layout.fragment_ezhuandian;
    }

    @Override
    protected void initView(View view) {
        // TODO Auto-generated method stub
        rl_getmonney_ganme = (RelativeLayout) view.findViewById(R.id.rl_ezhuandian_getmonney_game);
        rl_app_suggets_ganme = (RelativeLayout) view.findViewById(R.id.rl_ezhuandian_app_suggest);

        rl_getmonney_ganme.setOnClickListener(this);
        rl_app_suggets_ganme.setOnClickListener(this);


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

    }

    @Override
    public void onClick(View arg0) {
        Intent intent;
        switch (arg0.getId()) {
            case R.id.rl_title_left:
            case R.id.bt_title_left:
                getActivity().finish();
                break;
            case R.id.bt_title_right:
            case R.id.rl_title_right:
                intent = new Intent(getActivity(), SearchActivity.class);
                getActivity().startActivity(intent);
                break;
            case R.id.rl_ezhuandian_getmonney_game:
                intent = new Intent(getActivity(), GetMonneyGameActivity.class);
                getActivity().startActivity(intent);
                break;
            default:
                break;
        }
    }

}
