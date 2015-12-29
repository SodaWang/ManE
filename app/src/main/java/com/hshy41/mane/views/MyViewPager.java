package com.hshy41.mane.views;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class MyViewPager extends ViewPager {

	private boolean scrollble=false;


	  public MyViewPager(Context context, AttributeSet attrs) {
	    super(context, attrs);
	  }


	  @Override
	  public boolean onTouchEvent(MotionEvent ev) {
	    if (!scrollble) {
	      return false;
	    }
	    return super.onTouchEvent(ev);
	  }


	  public boolean isScrollble() {
	    return scrollble;
	  }

	  public void setScrollble(boolean scrollble) {
	    this.scrollble = scrollble;
	  }
	}