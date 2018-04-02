package app.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.xy.doll.R;
import com.xy.libs.util.app.JumpUtil;
import com.xy.libs.util.app.LogUtil;
import com.xy.libs.util.glide.GlideImageLoader;
import com.youth.banner.Banner;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import app.model.api.ApiService;
import app.model.api.ApiStore;
import app.model.api.AppConfig;
import app.model.api.BaseResp;
import app.model.api.HttpObserver;
import app.model.constant.Constant;
import app.model.constant.EventConstant;
import app.model.constant.ObjectEvent;
import app.model.contract.MainContract;
import app.model.data.FastEntrance;
import app.model.data.IPaddress;
import app.model.service.GameControl;
import app.model.service.WebSocketService;
import app.presenter.MainPresenter;
import app.ui.adapter.FastEntranceAdapter;
import app.ui.base.BaseActivity;
import app.ui.fragment.FastFragment;
import app.ui.widget.UserInfoWindow;
import app.util.SPs;
import com.xy.doll.down.DownActivity;
import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import is.hello.go99.AnimationTools;

public class MainActivity extends BaseActivity implements MainContract.View, OnRefreshListener {
    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.pager)
    ViewPager mPager;
    @BindView(R.id.refresh)
    SmartRefreshLayout mRefresh;
    @BindView(R.id.image_info)
    ImageView mImageInfo;
    @BindView(R.id.image_gift)
    ImageView mImageGift;
    @BindView(R.id.view_background)
    View viewBackground;
    @BindView(R.id.point)
    LinearLayout viewPoint;

    private List<String> imgs;
    private List<FastEntrance.BannerBean> advert;
    private List<Fragment> fragments;
    private List<FastEntrance.FastTrackBean> fastTrack;
    private FastEntranceAdapter pagerAdapter;
    private MainPresenter presenter;
    private int size;
    private UserInfoWindow window;
    private ImageView mIv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EventBus.getDefault().register(this);
    }

    private void initRefresh() {
        mRefresh.setOnRefreshListener(this);
        /**设置内容不满一页的时候能否加载更多*/
        mRefresh.setEnableLoadMoreWhenContentNotFull(true);
        /**设置刷新的头部和尾部*/
        mRefresh.setRefreshHeader(new ClassicsHeader(activity));
        mRefresh.setRefreshFooter(new ClassicsFooter(activity));
        mRefresh.setHeaderHeight(50);
    }

    @Subscribe(threadMode = ThreadMode.MAIN) //在ui线程执行
    public void onEvent(ObjectEvent event) {
        if (event.getCode() == EventConstant.LOGOUT) {
            finish();
        } else if (event.getCode() == EventConstant.RELOGIN) {
            finish();
        }
    }

    @OnClick({R.id.image_info, R.id.image_gift, R.id.view_background})
    void click(View view) {
        switch (view.getId()) {
            case R.id.image_info:
                window.showAtLocation(view, Gravity.CENTER, 0, 0);
                AnimationTools.getInstance().showAlphaAnimation(viewBackground);
                break;
            case R.id.image_gift:
//                JumpUtil.overlay(activity,GiftBoxActivity.class);
//                getSocketUrl();
                JumpUtil.overlay(activity, DownActivity.class);
                break;
            case R.id.view_background:
                AnimationTools.getInstance().hideAlphaAnimation(viewBackground);
                break;
        }
    }

    @Override
    protected void initUI() {
        showLog("token = " + SPs.get(activity, Constant.TOKEN, ""));
        initRefresh();
        window = new UserInfoWindow(activity);
        presenter = new MainPresenter(this);
        presenter.getEntranceInfo();
        window.setOnDismissListener(() -> AnimationTools.getInstance().hideAlphaAnimation(viewBackground));
    }

    @Override
    protected void initData() {
        imgs = new ArrayList<>();
        advert = new ArrayList<>();
        fragments = new ArrayList<>();
        fastTrack = new ArrayList<>();
    }

    private void initBanner() {
        banner.setImages(imgs)
                .setImageLoader(new GlideImageLoader())
                .setDelayTime(Constant.AUTOPLAY)
                .start();
    }

    @Override
    public void getEntranceInfoOk(FastEntrance fastEntrance) {
        if (!isDestroyed()) {
            if (fastEntrance != null && fastEntrance.getBanner().size() != 0 && fastEntrance.getFast_track().size() != 0) {
                /**
                 * Banner
                 * */
                imgs.clear();
                advert.clear();
                advert.addAll(fastEntrance.getBanner());
                for (FastEntrance.BannerBean bannerBean : advert) {
                    imgs.add(AppConfig.BASE_URL_PIC + bannerBean.getPicture());
                }
                initBanner();
                /**
                 * 快速入口,向Fragment传递集合
                 * */
                fastTrack.clear();
                fastTrack.addAll(fastEntrance.getFast_track());
                size = fastTrack.size();

                initFastEntrance(size);
            }
            if (mRefresh.isLoading())
                mRefresh.finishLoadMore(true);
            if (mRefresh.isRefreshing())
                mRefresh.finishRefresh(true);
        }
    }

    private void initFastEntrance(int size) {
        fragments.clear();
        if (size < 8) {                 //集合小于8
            fragments.add(FastFragment.getInstance(fastTrack.subList(0, size)));
        } else if (size % 8 == 0) {     //集合是8的倍数
            for (int i = 0; i < size / 8; i++)
                fragments.add(FastFragment.getInstance(fastTrack.subList(i * 8, (i + 1) * 8)));
        } else {                        //大于8且不是8的倍数
            for (int i = 0; i < size / 8; i++) {
                fragments.add(FastFragment.getInstance(fastTrack.subList(i * 8, (i + 1) * 8)));
            }
            fragments.add(FastFragment.getInstance(fastTrack.subList(size / 8 * 8, size)));
        }
        pagerAdapter = new FastEntranceAdapter(getSupportFragmentManager(), fragments);
        mPager.setAdapter(pagerAdapter);
        mPager.setOffscreenPageLimit(pagerAdapter.getCount());

        /**
         * 指示器
         * */
        runOnUiThread(() -> {
            viewPoint.removeAllViews();
            for (int i = 0; i < fragments.size(); i++) {
                ImageView imageView = new ImageView(activity);
                imageView.setBackground(getResources().getDrawable(R.drawable.drawable_pick_normal));
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(20, 20);
                if (i != 0) {
                    params.leftMargin = 20;
                }
                params.gravity = Gravity.CENTER_VERTICAL;
                imageView.setLayoutParams(params);
                viewPoint.addView(imageView);
            }
            viewPoint.getChildAt(0).setBackground(getResources().getDrawable(R.drawable.drawable_pick_select));
            mPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                }

                @Override
                public void onPageSelected(int position) {
                    if (mIv != null)
                        mIv.setBackground(getResources().getDrawable(R.drawable.drawable_pick_normal));
                    if (position == 1)
                        viewPoint.getChildAt(0).setBackground(getResources().getDrawable(R.drawable.drawable_pick_normal));
                    ImageView currentIv = (ImageView) viewPoint.getChildAt(position);
                    currentIv.setBackground(getResources().getDrawable(R.drawable.drawable_pick_select));
                    mIv = currentIv;
                }

                @Override
                public void onPageScrollStateChanged(int state) {
                }
            });
        });
    }

    private void getSocketUrl() {
        ApiStore.createApi(ApiService.class)
                .getSocketUrl()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new HttpObserver<BaseResp<IPaddress>>() {
                    @Override
                    public void onNext(BaseResp<IPaddress> iPaddressBaseResp) {
                        if (iPaddressBaseResp.getStatus() == Constant.REQUEST_SUCCESS) {
                            GameControl.getInstance().setSocketUrl(iPaddressBaseResp.getData().getControl_url());
                            LogUtil.e("WebSocketService连接地址：" + iPaddressBaseResp.getData().getControl_url());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
    }

    @Override
    public void getEntranceInfoErr(String info) {
        if (mRefresh.isLoading())
            mRefresh.finishLoadMore(false);
        if (mRefresh.isRefreshing())
            mRefresh.finishRefresh(false);
    }

    @Override
    public void onRefresh(RefreshLayout refreshLayout) {
        presenter.getEntranceInfo();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        GameControl.getInstance().close();
        Intent intent = new Intent(activity, WebSocketService.class);
        stopService(intent);
    }
}
