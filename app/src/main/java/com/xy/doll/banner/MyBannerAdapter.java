package com.xy.doll.banner;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jxy on 2018/4/26.
 */

public class MyBannerAdapter extends PagerAdapter {
    private Context context;
    /**
     * 图片集合
     */
    private List<BannerInfo> dataList;
    /**
     * 图片的缓存集合
     */
    private final ArrayList<Object> mViewCaches = new ArrayList<>();

    public MyBannerAdapter(Context context, List<BannerInfo> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    @Override
    public int getCount() {
        if (!dataList.isEmpty() && dataList != null) {
            if (dataList.size() == 1) {
                return 1;
            } else {
                return Integer.MAX_VALUE;
            }
        } else {
            return 0;
        }
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        if (!dataList.isEmpty() && dataList != null) {
            final ImageView imageView;
            if (mViewCaches.isEmpty()) {
                imageView = new ImageView(context);
                imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            } else {
                imageView = (ImageView) mViewCaches.remove(0);
            }
            Glide.with(context).load(dataList.get(1).imageUrl).into(imageView);
            container.addView(imageView);
            return imageView;
        } else {
            return null;
        }
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        ImageView mGlideView = (ImageView) object;
        container.removeView(mGlideView);
        mViewCaches.add(mGlideView);
    }
}
