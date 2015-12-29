package com.hshy41.mane.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * SharepreUtil
 * @author DADI
 *
 */
public class SharepreUtil {
	private static SharedPreferences sp;

	public static SharedPreferences getInstant(Context context) {
		if (sp == null) {
			sp = context
					.getSharedPreferences("manyishequ", context.MODE_PRIVATE);
		}
		return sp;
	}

}
