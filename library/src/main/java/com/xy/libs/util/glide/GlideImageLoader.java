package com.xy.libs.util.glide;

import android.content.Context;
import android.widget.ImageView;

import com.youth.banner.loader.ImageLoader;

/**
 * Created by jxy on 2018/2/2.
 */

public class GlideImageLoader extends ImageLoader {

    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        GlideUtil.loadImage(context, path, imageView);
    }
}
