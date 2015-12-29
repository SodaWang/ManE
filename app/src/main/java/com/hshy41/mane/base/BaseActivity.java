package com.hshy41.mane.base;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hshy41.mane.R;

/**
 * activity基类
 * 
 * @author DADI
 * 
 */
public abstract class BaseActivity extends FragmentActivity {

	protected Context context;
	/** 左布局，右布局 */
    protected RelativeLayout rl_title_left, rl_title_right;
	/** 定位小图 */
    protected ImageView iv_title_gps;
	/** 标题 */
    protected TextView tv_title_text;
	/** 搜索键 */
	protected Button bt_title_right;
	/** 返回键 */
	protected Button bt_title_left;
	/**
	 * 右侧文字
	 */
	protected TextView tv_title_right;

	/**
	 *搜索布局
     */
	protected RelativeLayout rl_title_search;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
		context = getApplicationContext();
		setContentView(setContent());
		getIntentDate();
		initViews();
		initTitleBar();
		setTitleBar();
	}
	/**
	 * 设置布局文件
	 *
	 * @return 布局ID
	 */
	protected abstract int setContent();

	/**
	 * 初始化控
	 *
	 * @return
	 */
	protected abstract void initViews();

	/**
	 * 初始化标题
	 *
	 * @return
	 */
	protected abstract void initTitleBar();

	/**
	 * 设置标题
	 *
	 * @return
	 */
	protected abstract void setTitleBar();

	/**
	 * 此方法可重写
	 */
	protected void getIntentDate() {

	}

}
