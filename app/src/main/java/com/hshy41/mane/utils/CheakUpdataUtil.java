package com.hshy41.mane.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.CookieStore;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.widget.Toast;



public class CheakUpdataUtil {

	private Context context;

	private String newVersion;
	
	private boolean isShowToast = true;

	public CheakUpdataUtil(Context context) {
		// TODO Auto-generated constructor stub
		this.context = context;
		isShowToast = true;

	}

	public CheakUpdataUtil(Context context, boolean isShowToast) {
		// TODO Auto-generated constructor stub
		this.context = context;
		this.isShowToast = isShowToast;
	}

	public void cheak() {
		getVersionData();
	}

	/**
	 * 联网获取最新版本信息
	 */
	private void getVersionData() {
		// TODO Auto-generated method stub
		String url="http://sdh.qweweq.com/bbh.php";
		List<NameValuePair> pairs=new ArrayList<NameValuePair>();
		NetDataUtils.getNetDataForGet(context, url, pairs, myCallBack);
	}
		private NetDataCallBack myCallBack=new NetDataCallBack() {
			
			@Override
			public void onNetSuccess(String t, CookieStore cookieStore) {
				// TODO Auto-generated method stub
				try {
					JSONObject object=new JSONObject(t);
					newVersion=object.getString("data");
					versionCompair(newVersion);
					
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			@Override
			public void onNetFailure() {
				// TODO Auto-generated method stub
			}
			@Override
			public void onNetError(String errMsg) {
				// TODO Auto-generated method stub
				
			}
		};

	/**
	 * 获取当前应用版本
	 * 
	 * @return
	 */
	private String getAppVersion() {
		PackageManager packageManager = context.getPackageManager();
		// getPackageName()是你当前类的包名，0代表是获取版本信息
		PackageInfo packInfo = null;
		try {
			packInfo = packageManager.getPackageInfo(context.getPackageName(),
					0);
		} catch (NameNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String version = packInfo.versionName;
		return version;

	}

	/**
	 * 版本对比
	 * 
	 * @param ver
	 */
	private void versionCompair(String ver) {
		String[] oldString = getAppVersion().replace(".", ",").split(",");
		String[] newString = ver.replace(".", ",").split(",");
		List<String> oldVersion = Arrays.asList(oldString);
		List<String> newVersion = Arrays.asList(newString);
		for (int i = 0; i < newVersion.size(); i++) {
			if (Integer.parseInt(newVersion.get(i)) > Integer
					.parseInt(oldVersion.get(i))) {
				showMessage();
				return;
			}

		}
		if (isShowToast)
			Toast.makeText(context, "当前已经是最新版本！", 0).show();
	}

	/**
	 * 显示最新版本信息
	 */
	private void showMessage() {
		AlertDialog.Builder builder = new Builder(context);
		builder.setTitle("版本更新");
		builder.setCancelable(false);
		StringBuffer message = new StringBuffer();
		message.append("最新版本号：" + newVersion);
		builder.setMessage(message);
		builder.setPositiveButton("确认升级", new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
			}
		});
		builder.setNegativeButton("以后再说", new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				dialog.dismiss();
			}
		});
		builder.create().show();

	}

	

}
