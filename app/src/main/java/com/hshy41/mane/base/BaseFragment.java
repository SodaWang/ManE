package com.hshy41.mane.base;

import java.util.ArrayList;
import java.util.List;

import com.hshy41.mane.R;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;


/**
 * Fragment基类
 * 
 * @author DADI
 * 
 */
public abstract class BaseFragment extends Fragment {
	protected Activity activity;


	protected Context context;
	/** 左布局，右布局 */
	protected RelativeLayout rl_title_left, rl_title_right;
	/** 定位小图 */
	protected ImageView iv_title_gps;
	/** 标题, 右侧文字 */
	protected TextView tv_title_text;
	/** 左标题键,右标题键键 */
	protected Button bt_title_right, bt_title_left;
	/**
	 * 右侧文字
	 */
	protected TextView tv_title_right;


	/**
	 *
	 * 标题布局
	 */
	protected RelativeLayout rl_title_layout;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		activity = getActivity();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(setLayoutID(), null);
		initTitleBar(view);
		setTitleBar();
		initView(view);
		return view;
	}

	/**
	 * 设置标题
	 */
	protected abstract void setTitleBar();
	/**
	 * 初始化标题栏
	 */
	protected void initTitleBar(View view) {

	}


	/**
	 * 设置布局文件
	 * 
	 * @return
	 */
	protected abstract int setLayoutID();

	/**
	 * 初始化控件
	 * 
	 * @param view
	 */
	protected abstract void initView(View view);
	
	public List<String>	 getPrice() {
		return null;
	}

}
