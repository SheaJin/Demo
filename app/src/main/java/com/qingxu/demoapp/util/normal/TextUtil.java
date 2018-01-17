package com.qingxu.demoapp.util.normal;

import android.graphics.Paint;
import android.widget.TextView;

/**
 * Created by JinXinyi on 2017/7/28.
 */

public class TextUtil {
    /**
     * 设置下划线
     * */
    public static void setUnderLine(TextView mTv){
        mTv.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
        mTv.getPaint().setAntiAlias(true);//抗锯齿
    }

    /**
     * 设置中划线
     * */
    public static void setStrike(TextView mTv){
        mTv.getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG|Paint.ANTI_ALIAS_FLAG);
    }

    /**
     * 设置字体加粗
     * */
    public static void setBold(TextView mTv){
        mTv.getPaint().setFakeBoldText(true);
    }

    /**
     * 修改TextView中部分文字颜色和添加下划线
     * */
//    SpannableString spanText=new SpannableString(getString(R.string.terms_service));
//        spanText.setSpan(new ClickableSpan() {
//
//        @Override
//        public void updateDrawState(TextPaint ds) {
//            super.updateDrawState(ds);
//            ds.setColor(Color.parseColor("#00AAFF"));       //设置文件颜色
//            ds.setUnderlineText(true);                      //设置下划线
//        }
//
//        @Override
//        public void onClick(View view) {
//            ToastUtil.showShort(getContext(),"点击了");
//        }
//    }, spanText.length() - 9, spanText.length() - 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//
//        mTvTip.setHighlightColor(Color.TRANSPARENT);            //设置点击后的颜色为透明，否则会一直出现高亮
//        mTvTip.setText(spanText);
//        mTvTip.setMovementMethod(LinkMovementMethod.getInstance());//开始响应点击事件
//

    /**
     * 修改TextView部分文字颜色
     * */
//    mTv = (TextView) findViewById(R.id.textview);
//
//    SpannableStringBuilder builder = new SpannableStringBuilder(textView.getText().toString());
//
//    //ForegroundColorSpan 为文字前景色，BackgroundColorSpan为文字背景色
//    ForegroundColorSpan redSpan = new ForegroundColorSpan(Color.RED);
//    builder.setSpan(redSpan,0,1,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//    mTv.setText(builder);
}