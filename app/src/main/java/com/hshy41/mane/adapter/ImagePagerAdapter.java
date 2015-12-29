/*
 * Copyright 2014 trinea.cn All right reserved. This software is the confidential and proprietary information of
 * trinea.cn ("Confidential Information"). You shall not disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into with trinea.cn.
 */
package com.hshy41.mane.adapter;

import java.io.File;
import java.util.List;


import com.hshy41.mane.entity.ImageEntity;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.utils.StorageUtils;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ImageView.ScaleType;

/**
 * ImagePagerAdapter
 *
 * @author <a href="http://www.trinea.cn" target="_blank">Trinea</a> 2014-2-23
 */
public class ImagePagerAdapter extends RecyclingPagerAdapter {

    private Context context;
    private List<ImageEntity> imageIdList;
    private DisplayImageOptions options;
    private int size;
    private boolean isInfiniteLoop;

    public ImagePagerAdapter(Context context, List<ImageEntity> imageIdList) {
        this.context = context;
        this.imageIdList = imageIdList;
        // this.size = ListUtils.getSize(imageIdList);
        this.size = imageIdList.size();
        isInfiniteLoop = false;



    }

    @Override
    public int getCount() {
        // Infinite loop
        if (size == 0) {
            return 0;
        } else {
            return isInfiniteLoop ? Integer.MAX_VALUE : size;
        }
    }

    /**
     * get really position
     *
     * @param position
     * @return
     */
    private int getPosition(int position) {

        if (size == 0)
            return 0;
        else
            return isInfiniteLoop ? position % size : position;
    }

    @Override
    public View getView(final int position, View view, ViewGroup container) {
        ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            view = holder.imageView = new ImageView(context);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        holder.imageView.setScaleType(ScaleType.CENTER_CROP);
        ImageLoader.getInstance().displayImage(
                imageIdList.get(getPosition(position)).getUrl(),
                holder.imageView, options);

        return view;
    }

    private static class ViewHolder {

        ImageView imageView;
    }

    /**
     * @return the isInfiniteLoop
     */
    public boolean isInfiniteLoop() {
        return isInfiniteLoop;
    }

    /**
     * @param isInfiniteLoop the isInfiniteLoop to set
     */
    public ImagePagerAdapter setInfiniteLoop(boolean isInfiniteLoop) {
        this.isInfiniteLoop = isInfiniteLoop;
        return this;
    }
}
