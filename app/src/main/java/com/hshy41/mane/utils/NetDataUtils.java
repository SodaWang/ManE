package com.hshy41.mane.utils;

import java.io.File;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.CookieStore;
import org.apache.http.impl.client.DefaultHttpClient;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.google.gson.Gson;
import com.hshy41.mane.bean.BaseBean;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

public class NetDataUtils {

	private static final String TAG = "NetDataUtils";

	/**
	 * 网络连接是否可用
	 */
	public static boolean isConnnected(Context context) {
		ConnectivityManager connectivityManager = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		if (null != connectivityManager) {
			NetworkInfo networkInfo[] = connectivityManager.getAllNetworkInfo();

			if (null != networkInfo) {
				for (NetworkInfo info : networkInfo) {
					if (info.getState() == NetworkInfo.State.CONNECTED) {
						Log.e(TAG, "the net is ok");
						return true;
					}
				}
			}
		}
		// Toast.makeText(context, "网络连接失败", Toast.LENGTH_SHORT).show();
		return false;
	}

	/**
	 * 联网获取网络数据--post
	 * 
	 * @param context
	 * @param url
	 * @param pairs
	 *            参数列表
	 * @param myCallBack
	 *            回调
	 * @param isShowDialog
	 *            是否显示dialog 默认显示
	 * @param showMessage
	 *            dialog提示信息
	 */
	public static void getNetDataForPost(final Context context, String url,
			List<NameValuePair> pairs, final NetDataCallBack myCallBack,
			boolean isShowDialog, String showMessage, final boolean isCookie,
			CookieStore cookieStore) {
		if (isShowDialog) {
			DialogUtils.showProgressDialog(context, showMessage);
		}
		if (!isConnnected(context)) {
			ToastUtil.showToast(context, "网络错误，请检查网络连接后重试！");
			Log.i(TAG, "onNetFailure");
			DialogUtils.dismissProgressDialog();
			myCallBack.onNetFailure();
			return;
		}

		final HttpUtils utils = new HttpUtils();
		StringBuffer logSb = new StringBuffer();
		String logString = "";
		StringBuffer urlSb = new StringBuffer();
		RequestParams params = new RequestParams();
		if (null != pairs || pairs.size() > 0) {
			params.addBodyParameter(pairs);
			for (NameValuePair pair : pairs) {
				logSb.append(pair.getName() + ":" + pair.getValue() + "\n");
			}
			logString = logSb.toString().trim();
			Log.i(TAG, logString);
		}
		Log.i(TAG, url);

		if (null != cookieStore) {
			utils.configCookieStore(cookieStore);
		}
		utils.send(HttpMethod.POST, url, params, new RequestCallBack<String>() {

			@Override
			public void onSuccess(ResponseInfo<String> arg0) {

				DialogUtils.dismissProgressDialog();
				if (null == arg0 || null == arg0.result
						|| arg0.result.equals("")) {
					Log.i(TAG, "onNetError-->服务器错误");
					myCallBack.onNetError("服务器错误");
					return;
				}
				Log.i(TAG, "onSuccess-->" + arg0.result);

				if (!StringUtils.isJson(arg0.result)) {
					Log.i(TAG, "onNetError-->JSON错误");
					myCallBack.onNetError("服务器错误");
					return;
				}
				Gson g = new Gson();
				BaseBean bean = g.fromJson(arg0.result, BaseBean.class);
				if (bean.Result == 1) {

					Log.i(TAG, "onNetError-->" + bean.Message);
					myCallBack.onNetError(bean.Message);
					return;
				} else {

					if (null == bean.data || bean.data.equals("")) {
						if (isCookie) {
							/**
							 * 获得session
							 */
							DefaultHttpClient dh = (DefaultHttpClient) utils
									.getHttpClient();
							CookieStore cookieStore = dh.getCookieStore();
							myCallBack.onNetSuccess(bean.Message, cookieStore);
						} else {
							myCallBack.onNetSuccess(bean.Message, null);
						}

						Log.i(TAG, "onSuccess-->" + bean.Message);
					} else {

						if (isCookie) {
							/**
							 * 获得session
							 */
							DefaultHttpClient dh = (DefaultHttpClient) utils
									.getHttpClient();
							CookieStore cookieStore = dh.getCookieStore();
							myCallBack.onNetSuccess(arg0.result, cookieStore);
						} else {

							myCallBack.onNetSuccess(arg0.result, null);
						}

						Log.i(TAG, "onSuccess-->" + arg0.result);
					}

				}
			}

			@Override
			public void onFailure(
					com.lidroid.xutils.exception.HttpException arg0, String arg1) {
				// TODO Auto-generated method stub
				ToastUtil.showToast(context, "网络错误，请检查网络连接后重试！");
				Log.i(TAG, "onNetFailure");
				DialogUtils.dismissProgressDialog();
				myCallBack.onNetFailure();
			}
		});

	}

	public static void getNetDataForPost(final Context context, String url,
			List<NameValuePair> pairs, final NetDataCallBack myCallBack,
			boolean isShowDialog, String showMessage) {
		getNetDataForPost(context, url, pairs, myCallBack, isShowDialog,
				showMessage, false, null);
	}

	/**
	 * 普通 post
	 * 
	 * @param context
	 * @param url
	 * @param pairs
	 * @param myCallBack
	 */
	public static void getNetDataForPost(final Context context, String url,
			List<NameValuePair> pairs, final NetDataCallBack myCallBack) {

		getNetDataForPost(context, url, pairs, myCallBack, true, "正在加载中，请稍候。。。");
	}

	/**
	 * 上传 cookie
	 * 
	 * @param context
	 * @param url
	 * @param pairs
	 * @param myCallBack
	 * @param cookieStore
	 */
	public static void getNetDataForPost(final Context context, String url,
			List<NameValuePair> pairs, final NetDataCallBack myCallBack,
			CookieStore cookieStore) {

		getNetDataForPost(context, url, pairs, myCallBack, true,
				"正在加载中，请稍候。。。", false, cookieStore);
	}

	/**
	 * 获取cookie
	 * 
	 * @param context
	 * @param url
	 * @param pairs
	 * @param myCallBack
	 * @param isCookie
	 */
	public static void getNetDataForPost(final Context context, String url,
			List<NameValuePair> pairs, final NetDataCallBack myCallBack,
			boolean isCookie) {

		getNetDataForPost(context, url, pairs, myCallBack, true,
				"正在加载中，请稍候。。。", isCookie, null);
	}

	/**
	 * 联网获取网络数据--get
	 * 
	 * @param context
	 * @param url
	 * @param pairs
	 *            参数列表
	 * @param myCallBack
	 *            回调
	 * @param isShowDialog
	 *            是否显示dialog 默认显示
	 * @param showMessage
	 *            dialog提示信息
	 */
	public static void getNetDataForGet(final Context context, String url,
			List<NameValuePair> pairs, final NetDataCallBack myCallBack,
			final boolean isShowDialog, String showMessage) {

		if (!isConnnected(context)) {
			ToastUtil.showToast(context, "网络错误，请检查网络连接后重试！");
			Log.i(TAG, "onNetFailure");
			myCallBack.onNetFailure();
		}
		if (isShowDialog) {
			DialogUtils.showProgressDialog(context, showMessage);
		}

		HttpUtils utils = new HttpUtils();
		/**
		 * 5秒缓存
		 */
		utils.configCurrentHttpCacheExpiry(5000);
		StringBuffer logSb = new StringBuffer();
		String logString = "";
		StringBuffer urlSb = new StringBuffer();
		urlSb.append(url + "?");
		RequestParams params = new RequestParams();

		if (null != pairs || pairs.size() > 0) {
			params.addBodyParameter(pairs);
			for (NameValuePair pair : pairs) {
				urlSb.append(pair.getName() + "=" + pair.getValue() + "&");
				logSb.append(pair.getName() + ":" + pair.getValue() + "\n");
			}
			logString = logSb.toString().trim();
			Log.i(TAG, logString);
		}
		String newUrl = urlSb.toString().trim();
		newUrl = newUrl.substring(0, newUrl.length() - 1);

		Log.i(TAG, url);
		Log.i(TAG, newUrl);

		utils.send(HttpMethod.GET, newUrl, new RequestCallBack<String>() {

			@Override
			public void onSuccess(ResponseInfo<String> arg0) {
				if (isShowDialog)
					DialogUtils.dismissProgressDialog();

				if (null == arg0 || null == arg0.result
						|| arg0.result.equals("")) {
					myCallBack.onNetError("服务器错误");
					return;
				}
				Log.i(TAG, "onSuccess-->" + arg0.result);
				if (!StringUtils.isJson(arg0.result)) {
					Log.i(TAG, "onNetError-->JSON错误");
					myCallBack.onNetError("服务器错误");
					return;
				}
				Gson g = new Gson();
				BaseBean bean = g.fromJson(arg0.result, BaseBean.class);
				if (bean.Result == 1) {
					myCallBack.onNetError(bean.Message);
					return;
				} else {
					// myCallBack.onNetSuccess(bean, null);

					if (null == bean.data || bean.data.equals("")) {
						myCallBack.onNetSuccess(bean.Message, null);
					} else {
						myCallBack.onNetSuccess(arg0.result, null);
					}
				}
			}

			@Override
			public void onFailure(
					com.lidroid.xutils.exception.HttpException arg0, String arg1) {
				// TODO Auto-generated method stub
				ToastUtil.showToast(context, "网络错误，请检查网络连接后重试！");
				Log.i(TAG, "onNetFailure");
				if (isShowDialog)
					DialogUtils.dismissProgressDialog();

				myCallBack.onNetFailure();
			}
		});

	}

	/**
	 * 普通get请求
	 * 
	 * @param context
	 * @param url
	 * @param pairs
	 * @param myCallBack
	 */
	public static void getNetDataForGet(final Context context, String url,
			List<NameValuePair> pairs, final NetDataCallBack myCallBack) {

		getNetDataForGet(context, url, pairs, myCallBack, true, "正在加载中，请稍候。。。");

	}

	/**
	 * 上传文件 -- 如果成功，回调返回文件网络路径
	 * 
	 * @param context
	 * @param url
	 * @param fileName
	 *            上传时文件的键名
	 * @param filePath
	 *            文件的路径
	 * @param pairs
	 *            除文件外的键值对
	 * @param myCallBack
	 *            回调
	 */
	public static void uploadFile(final Context context, String url,
			String fileName, String filePath,
			// List<NameValuePair> pairs,
			final NetDataCallBack myCallBack) {

		if (!isConnnected(context)) {
			ToastUtil.showToast(context, "网络错误，请检查网络连接后重试！");
			Log.i(TAG, "onNetFailure");
			myCallBack.onNetFailure();
		}

		DialogUtils.showProgressDialog(context, "请稍候。。");

		File file = new File(filePath);
		HttpUtils util = new HttpUtils();
		RequestParams params = new RequestParams();

		StringBuffer logSb = new StringBuffer();
		String logString = "";
		// if (null != pairs && pairs.size() > 0) {
		// params.addBodyParameter(pairs);
		// for (NameValuePair pair : pairs) {
		// logSb.append(pair.getName() + ":" + pair.getValue() + "\n");
		// }
		// }
		params.addBodyParameter(fileName, file);
		logSb.append(fileName + ":" + filePath + "\n");
		logString = logSb.toString().trim();
		Log.i(TAG, logString);
		Log.i(TAG, url);

		util.send(HttpMethod.POST, url, params, new RequestCallBack<String>() {

			@Override
			public void onSuccess(ResponseInfo<String> arg0) {

				DialogUtils.dismissProgressDialog();
				if (null == arg0 || null == arg0.result
						|| arg0.result.equals("")) {
					myCallBack.onNetError("服务器错误");
					return;
				}
				Log.i(TAG, "onSuccess-->" + arg0.result);
				if (!StringUtils.isJson(arg0.result)) {
					Log.i(TAG, "onNetError-->JSON错误");
					myCallBack.onNetError("服务器错误");
					return;
				}
				Gson g = new Gson();
				BaseBean bean = g.fromJson(arg0.result, BaseBean.class);
				if (bean.Result == 1) {
					myCallBack.onNetError(bean.Message);
					return;
				} else {
					myCallBack.onNetSuccess(bean.data.toString(), null);

				}
			}

			@Override
			public void onFailure(
					com.lidroid.xutils.exception.HttpException arg0, String arg1) {
				// TODO Auto-generated method stub
				ToastUtil.showToast(context, "网络错误，请检查网络连接后重试！");
				Log.i(TAG, "onNetFailure");
				DialogUtils.dismissProgressDialog();
				myCallBack.onNetFailure();
			}
		});

	}

	/**
	 * 自己解析
	 * 
	 * @param context
	 * @param url
	 * @param pairs
	 * @param myCallBack
	 */
	public static void getNetDataForPostByOther(final Context context,
			String url, List<NameValuePair> pairs,
			final NetDataCallBack myCallBack) {

		DialogUtils.showProgressDialog(context, "加载中，请稍候!");
		if (!isConnnected(context)) {
			ToastUtil.showToast(context, "网络错误，请检查网络连接后重试！");
			Log.i(TAG, "onNetFailure");
			DialogUtils.dismissProgressDialog();
			myCallBack.onNetFailure();
			return;
		}

		final HttpUtils utils = new HttpUtils();
		StringBuffer logSb = new StringBuffer();
		String logString = "";
		StringBuffer urlSb = new StringBuffer();
		RequestParams params = new RequestParams();
		if (null != pairs || pairs.size() > 0) {
			params.addBodyParameter(pairs);
			for (NameValuePair pair : pairs) {
				logSb.append(pair.getName() + ":" + pair.getValue() + "\n");
			}
			logString = logSb.toString().trim();
			Log.i(TAG, logString);
		}
		Log.i(TAG, url);

		utils.send(HttpMethod.POST, url, params, new RequestCallBack<String>() {

			@Override
			public void onSuccess(ResponseInfo<String> arg0) {

				DialogUtils.dismissProgressDialog();
				if (null == arg0 || null == arg0.result
						|| arg0.result.equals("")) {
					Log.i(TAG, "onNetError-->服务器错误");
					myCallBack.onNetError("服务器错误");
					return;
				}
				Log.i(TAG, "onSuccess-->" + arg0.result);

				if (!StringUtils.isJson(arg0.result)) {
					Log.i(TAG, "onNetError-->JSON错误");
					myCallBack.onNetError("服务器错误");
					return;
				}
				Gson g = new Gson();
				BaseBean bean = g.fromJson(arg0.result, BaseBean.class);
				if (bean.Result == 1) {

					Log.i(TAG, "onNetError-->" + bean.Message);
					myCallBack.onNetError(bean.Message);
					return;
				} else {

					myCallBack.onNetSuccess(arg0.result, null);

				}
			}

			@Override
			public void onFailure(
					com.lidroid.xutils.exception.HttpException arg0, String arg1) {
				// TODO Auto-generated method stub
				ToastUtil.showToast(context, "网络错误，请检查网络连接后重试！");
				Log.i(TAG, "onNetFailure");
				DialogUtils.dismissProgressDialog();
				myCallBack.onNetFailure();
			}
		});

	}

}
