package com.qingxu.demoapp.ui.login;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.ArrayMap;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.qingxu.demoapp.R;
import com.qingxu.demoapp.model.api.ApiService;
import com.qingxu.demoapp.model.api.ApiStore;
import com.qingxu.demoapp.model.api.HttpObserver;
import com.qingxu.demoapp.model.data.Register;
import com.qingxu.demoapp.model.event.MessageEvent;
import com.qingxu.demoapp.ui.base.BaseActivity;

import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.SimplePagerTitleView;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by JinXinYi on 2018/1/7.
 */

public class RegisterActivity extends BaseActivity{

    private EditText mEtName, mEtMail, mEtPsd, mEtCode;
    private String name, mail, password, code;
    private RadioGroup group;
    private RadioButton mRbLogin, mRbRegister;
    private View viewMail,viewPsd;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }

    @Override
    protected void initUI() {

    }

    @Override
    protected void initData() {
//        final CommonNavigator commonNavigator = new CommonNavigator(this);
//        commonNavigator.setAdapter(new CommonNavigatorAdapter() {
//            @Override
//            public int getCount() {
//                return titles.size();
//            }
//
//            @Override
//            public IPagerTitleView getTitleView(Context context, final int i) {
//                SimplePagerTitleView simplePagerTitleView = new SimplePagerTitleView(context);
//                simplePagerTitleView.setText(titles.get(i).getName());
//                simplePagerTitleView.setNormalColor(getResources().getColor(R.color.white_50));
//                simplePagerTitleView.setSelectedColor(getResources().getColor(R.color.white_255));
////                simplePagerTitleView.setPadding(TextUtil.getPx(60, TextUtil.Orientation.Width), 0, TextUtil.getPx(60, TextUtil.Orientation.Width), 0);
//                simplePagerTitleView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
//                simplePagerTitleView.setOnClickListener(v -> viewPager.setCurrentItem(i));
//                FrameLayout.LayoutParams marginLayoutParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT);
//                marginLayoutParams.leftMargin = TextUtil.getPx(60, TextUtil.Orientation.Width);
//                marginLayoutParams.rightMargin = TextUtil.getPx(60, TextUtil.Orientation.Width);
//                simplePagerTitleView.setLayoutParams(marginLayoutParams);
//                return simplePagerTitleView;
//            }
//
//            @Override
//            public IPagerIndicator getIndicator(Context context) {
//                LinePagerIndicator linePagerIndicator = new LinePagerIndicator(context);
//                linePagerIndicator.setMode(LinePagerIndicator.MODE_WRAP_CONTENT);
//                linePagerIndicator.setLineHeight(TextUtil.getPx(6, TextUtil.Orientation.Height));
//                linePagerIndicator.setColors(Color.WHITE);
//                return linePagerIndicator;
//            }
//        });
//
//        magicIndicator.setNavigator(commonNavigator);
//        simpleFragmentPagerAdapter = new SimpleFragmentPagerAdapter(getSupportFragmentManager(), fragmentList);
//        viewPager.setAdapter(simpleFragmentPagerAdapter);
//        viewPager.setOffscreenPageLimit(titles.size());
//        ViewPagerHelper.bind(magicIndicator, viewPager);
//        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//            @Override
//            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//            }
//
//            @Override
//            public void onPageSelected(int position) {
//
//            }
//
//            @Override
//            public void onPageScrollStateChanged(int state) {
//
//            }
//        });
//    }
    }

    @Override
    protected int setFragmentContainerResId() {
        return 0;
    }

    private void register() {
        ArrayMap<String,String> params = new ArrayMap<>();
        params.put("name",name);
        params.put("email",mail);
        params.put("password",password);

        ApiStore.createApi(ApiService.class)
                .register(params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new HttpObserver<Register>() {
                    @Override
                    public void onNext(Register register) {
                        Toast.makeText(RegisterActivity.this, "success", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("jxy", "onError: " + e.getMessage());
                    }
                });
    }

}
