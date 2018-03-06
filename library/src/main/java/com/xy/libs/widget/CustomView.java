package com.xy.libs.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by jxy on 2018/1/24.
 */

public class CustomView extends View {

    private int measureSpec;

    public CustomView(Context context) {
        super(context);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public CustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }


    private void init() {
        /**
         * MeasureSpec
         **/
        int specSize = MeasureSpec.getSize(measureSpec);
        int specMode = MeasureSpec.getMode(measureSpec);
        measureSpec = MeasureSpec.makeMeasureSpec(specSize,specMode);
        /**
         * 三种SpecMode
         * MeasureSpec.EXACTLY : 父容器已经检测出子View所需要的精确大小
         * MeasureSpec.AT_MOST : 父容器未能检测出子View所需要的精确大小，但是指定了一个可用大小即specSize
                                 在该模式下，View的测量大小不能超过SpecSize
         * MeasureSpec.UNSPECIFIED : 父容器不对子View的大小做限制
         * */


    }
}
