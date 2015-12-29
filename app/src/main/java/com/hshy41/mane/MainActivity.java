package com.hshy41.mane;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import com.hshy41.mane.base.BaseActivity;
import com.hshy41.mane.base.BaseFragment;
import com.hshy41.mane.fragment.MyFragment;
import com.hshy41.mane.fragment.CommunityFragment;
import com.hshy41.mane.fragment.IndexFragment;
import com.hshy41.mane.fragment.EZhuanDianFragment;
import com.hshy41.mane.utils.Cons;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.KeyEvent;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class MainActivity extends BaseActivity implements
		OnCheckedChangeListener, View.OnClickListener {
	String str_fragmentTag;
	private RadioGroup radiogroup;
	private RadioButton mybutton;
	/**
	 * 单选按钮
	 */
	private int[] indexRadioIds = { R.id.content_shouye_rb,
			R.id.content_shequ_rb, R.id.content_zhuan_rb, R.id.content_my_rb };
	private FragmentManager fm;
	private IndexFragment shouyefragment;
	private CommunityFragment shequfragment;
	private EZhuanDianFragment zhuanfragment;
	private MyFragment myfragment;
	private List<BaseFragment> fragmentlist = new ArrayList<BaseFragment>();
	private int pos = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		fm = getSupportFragmentManager();
		context = MainActivity.this;
		super.onCreate(savedInstanceState);
	}

	@Override
	protected int setContent() {
		// TODO Auto-generated method stub
		return R.layout.activity_main;
	}

	@Override
	protected void initViews() {
		// TODO Auto-generated method stub
		radiogroup=(RadioGroup) findViewById(R.id.index_radiogroup_index);
		radiogroup.setOnCheckedChangeListener(this);
		mybutton=(RadioButton) findViewById(R.id.content_my_rb);
		radiogroup.check(indexRadioIds[0]);

	}

	@Override
	protected void initTitleBar() {

	}

	@Override
	protected void setTitleBar() {

	}

	@Override
	public void onCheckedChanged(RadioGroup arg0, int arg1) {
		// TODO Auto-generated method stub
		hideAllFragment();
		switch (arg1) {
		case R.id.content_shouye_rb://首页
			str_fragmentTag = Cons.TAG_FRAGMENT_MAIN_INDEX;
			if (shouyefragment == null) {
				shouyefragment = new IndexFragment();
				fragmentlist.add(shouyefragment);
				fm.beginTransaction().add(R.id.container, shouyefragment, Cons.TAG_FRAGMENT_MAIN_INDEX)
						.commit();
			} else {
				fm.beginTransaction().show(shouyefragment).commit();
			}
			break;
		case R.id.content_shequ_rb://社区
			str_fragmentTag = Cons.TAG_FRAGMENT_MAIN_MYCOMMUNITY;
			if (shequfragment == null) {
				shequfragment = new CommunityFragment();
				fragmentlist.add(shequfragment);
				fm.beginTransaction().add(R.id.container, shequfragment, Cons.TAG_FRAGMENT_MAIN_MYCOMMUNITY)
						.commit();
			} else {
				fm.beginTransaction().show(shequfragment).commit();
			}
			break;
		case R.id.content_zhuan_rb://e赚点
			str_fragmentTag = Cons.TAG_FRAGMENT_MAIN_EDIANZHUAN;
			if (zhuanfragment == null) {
				zhuanfragment = new EZhuanDianFragment();
				fragmentlist.add(zhuanfragment);
				fm.beginTransaction().add(R.id.container, zhuanfragment, Cons.TAG_FRAGMENT_MAIN_EDIANZHUAN)
						.commit();
			} else {
				fm.beginTransaction().show(zhuanfragment).commit();
			}
			break;
		case R.id.content_my_rb://我的
			str_fragmentTag = Cons.TAG_FRAGMENT_MAIN_MY;
			if (myfragment == null) {
				myfragment = new MyFragment();
				fragmentlist.add(myfragment);
				fm.beginTransaction().add(R.id.container, myfragment, Cons.TAG_FRAGMENT_MAIN_MY)
						.commit();
			} else {
				fm.beginTransaction().show(myfragment).commit();
			}
			break;
		default:
			break;
		}
	}
	/**
	 * 隐藏所有Fragment布局
	 */
	private void hideAllFragment() {
		for (int i = 0; i < fragmentlist.size(); i++) {
			fm.beginTransaction().hide(fragmentlist.get(i)).commit();
		}
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if(keyCode==KeyEvent.KEYCODE_BACK){
			exitBy2Click();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
	/**
	 * 双击退出函数
	 */
	private static Boolean isExit = false;
	private void exitBy2Click() {
		Timer tExit = null;
		if (isExit == false) {
			isExit = true; // 准备退出
			Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
			tExit = new Timer();
			tExit.schedule(new TimerTask() {
				@Override
				public void run() {
					isExit = false; // 取消退出
				}
			}, 2000); // 如果2秒钟内没有按下返回键，则启动定时器取消掉刚才执行的任务

		} else {
			finish();
			System.exit(0);
		}
	}
	/**
	 * 跳转到我的界面
	 *
	 *            分类ID
	 */
	public void goMyServer() {
		mybutton.setChecked(true);
	}


	/*在fragment的管理类中，我们要实现这部操作，而他的主要作用是，当D这个activity回传数据到
    这里碎片管理器下面的fragnment中时，往往会经过这个管理器中的onActivityResult的方法。*/
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
        /*在这里，我们通过碎片管理器中的Tag，就是每个碎片的名称，来获取对应的fragment*/
		Fragment f = fm.findFragmentByTag(str_fragmentTag);
        /*然后在碎片中调用重写的onActivityResult方法*/
		f.onActivityResult(requestCode, resultCode, data);
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
