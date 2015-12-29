package com.hshy41.mane;

import java.io.File;


import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.hshy41.mane.bean.UserBean;
import com.hshy41.mane.utils.SharepreUtil;
import com.hshy41.mane.utils.ToastUtil;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.SimpleBitmapDisplayer;
import com.nostra13.universalimageloader.utils.StorageUtils;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Bitmap;
import android.os.Handler;

public class MyApplication extends Application {
    //Gson
    public static Gson gson;
    //Volley请求队列
    public static RequestQueue mQueue;
    //用户信息bean
    public static UserBean user;
    //图片显示设置
    public static DisplayImageOptions options;

    @Override
    public void onCreate() {
        // TODO Auto-generated method stub
        super.onCreate();
        initImageLoader();
        readUserInfo();

        //初始化Gson
        gson = new Gson();
        //初始化请求队列
        mQueue = Volley.newRequestQueue(this);
    }

    private void readUserInfo() {
        // TODO Auto-generated method stub
        user = new UserBean();
        SharedPreferences sp = SharepreUtil.getInstant(getApplicationContext());
        user.setId(sp.getString("id", "0"));
        user.setFace(sp.getString("face", ""));
        user.setJifen(sp.getInt("jifen", 0));
        user.setMoney(sp.getFloat("money", 0));
        user.setNickname(sp.getString("nickname", ""));
        user.setPhone(sp.getString("phone", ""));
        user.setUsername(sp.getString("username", ""));
    }

    /**
     * 更新用户信息
     */
    public static void updataUserInfo(Context context) {

        Editor edit = SharepreUtil.getInstant(context).edit();
        edit.putString("id", user.getId());
        edit.putString("nickname", user.getNickname());
        edit.putString("username", user.getUsername());
        edit.putFloat("money", user.getMoney());
        edit.putString("face", user.getFace());
        edit.putInt("jifen", user.getJifen());
        edit.putString("phone", user.getPhone());
        edit.commit();

    }

    /**
     * 判断是否登录
     */
    public static boolean checkIsLogin(Context context) {
        SharedPreferences sp = SharepreUtil.getInstant(context);
        if (sp.getString("id", "0").equals("0")) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 退出登陆--清空个人信息
     *
     * @param context
     */
    public static void clearUserInfo(Context context) {
        user.setNickname("");
        user.setJifen(0);
        user.setMoney(0);
        user.setPhone("");
        user.setId("0");
        user.setFace("");
        user.setUsername("");
        updataUserInfo(context);
    }

    private void initImageLoader() {
        // TODO Auto-generated method stub
        options = new DisplayImageOptions.Builder()
//                .showImageOnLoading(R.drawable.ic_stub) // 设置图片下载期间显示的图片
//                .showImageForEmptyUri(R.drawable.ic_empty) // 设置图片Uri为空或是错误的时候显示的图片
//                .showImageOnFail(R.drawable.ic_error) // 设置图片加载或解码过程中发生错误显示的图片
                .resetViewBeforeLoading(false)  // default 设置图片在加载前是否重置、复位
//                .delayBeforeLoading(1000)  // 下载前的延迟时间
                .cacheInMemory(true) // default  设置下载的图片是否缓存在内存中
                .cacheOnDisk(true) // default  设置下载的图片是否缓存在SD卡中
//                .preProcessor(...)
//        .postProcessor(...)
//        .extraForDownloader(...)
                .considerExifParams(false) // default
                .imageScaleType(ImageScaleType.IN_SAMPLE_POWER_OF_2) // default 设置图片以如何的编码方式显示
                .bitmapConfig(Bitmap.Config.ARGB_8888) // default 设置图片的解码类型
//                .decodingOptions(...)  // 图片的解码设置
                .displayer(new SimpleBitmapDisplayer()) // default  还可以设置圆角图片new RoundedBitmapDisplayer(20)
                .handler(new Handler()) // default
                .build();

        File cacheDir = StorageUtils.getOwnCacheDirectory(
                getApplicationContext(), "ManE/Cache");
        ImageLoaderConfiguration configuration = new ImageLoaderConfiguration.Builder(
                getApplicationContext()).memoryCacheExtraOptions(480, 800)
                // 即保存的每个内存缓存文件的最大长宽
                .threadPoolSize(5)
                        // 线程池内加载的数量
                .diskCacheFileCount(100)
                        // 设置硬盘缓存的文件的最多个数
                .threadPriority(Thread.NORM_PRIORITY - 2)
                        // 配置线程优先级
                .denyCacheImageMultipleSizesInMemory()//.内存缓存

                .diskCache(new UnlimitedDiskCache(cacheDir)).build();//硬盘缓存路径

        ImageLoader.getInstance().init(configuration);
    }

    /**
     * 清除图片缓存
     */
    public void clearImageLoaderCachek() {
        ImageLoader.getInstance().clearMemoryCache();  // 清除内存缓存
        ImageLoader.getInstance().clearDiskCache();  // 清除本地缓存
        ToastUtil.showLongToast(this, "清除本地缓存成功");
    }

}
