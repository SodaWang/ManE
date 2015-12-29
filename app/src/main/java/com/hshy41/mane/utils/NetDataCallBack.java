package com.hshy41.mane.utils;

import org.apache.http.client.CookieStore;

public interface NetDataCallBack {
	/**
	 * 联网失败
	 */
	public void onNetFailure();

	/**
	 * 错误回调
	 * 
	 * @param errMsg
	 *            错误信息
	 */
	public void onNetError(String errMsg);

	/**
	 * 请求成功
	 * 
	 * @param t
	 *            返回数据
	 * @param cookieStore
	 *            cookie
	 */
	public void onNetSuccess(String t, CookieStore cookieStore);

}
