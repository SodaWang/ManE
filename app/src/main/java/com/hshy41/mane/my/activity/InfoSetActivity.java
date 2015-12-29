package com.hshy41.mane.my.activity;

import android.view.View;
import android.view.View.OnClickListener;

import com.hshy41.mane.R;
import com.hshy41.mane.base.BaseActivity;

public class InfoSetActivity extends BaseActivity implements OnClickListener {

	@Override
	protected int setContent() {
		// TODO Auto-generated method stub
		return R.layout.activity_info;
	}

	@Override
	protected void initViews() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void initTitleBar() {

	}

	@Override
	protected void setTitleBar() {

	}


	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
		case R.id.rl_title_left:
			finish();
			break;

		default:
			break;
		}
	}

}
