package app.ui.widget;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.xy.doll.DollApplication;
import com.xy.doll.R;
import com.xy.libs.util.app.JumpUtil;
import com.xy.libs.util.glide.GlideUtil;
import com.xy.libs.util.normal.ToastUtil;

import app.model.api.ApiService;
import app.model.api.ApiStore;
import app.model.api.AppConfig;
import app.model.api.BaseResp;
import app.model.api.HttpObserver;
import app.model.constant.Constant;
import app.model.data.UserInfo;
import app.ui.activity.SetActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by jxy on 2018/2/7.
 */

public class UserInfoWindow extends PopupWindow {

    @BindView(R.id.user_head)
    CircleImageView userHead;
    @BindView(R.id.use_sex)
    ImageView useSex;
    @BindView(R.id.user_name)
    TextView userName;
    @BindView(R.id.user_diamond)
    TextView userDiamond;
    @BindView(R.id.user_coin)
    TextView userCoin;
    @BindView(R.id.new_notification)
    ImageView newNotification;
    private View view;
    private UserInfo userInfo;

    public UserInfoWindow(Context context) {
        super(context);
        init(context);
    }

    @OnClick({R.id.catch_history, R.id.notification_msg, R.id.user_set, R.id.user_cancel})
    void click(View view) {
        switch (view.getId()) {
            case R.id.catch_history:

                break;
            case R.id.notification_msg:

                break;
            case R.id.user_set:
                JumpUtil.overlay(DollApplication.getInstance(), SetActivity.class);
                break;
            case R.id.user_cancel:
                dismiss();
                break;
        }
    }

    private void init(Context context) {
        view = View.inflate(context, R.layout.window_userinfo, null);
        ButterKnife.bind(this, view);
        this.setContentView(view);

        this.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        this.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);

        this.setFocusable(true);
        this.setTouchable(true);
        this.setOutsideTouchable(false);
        /**设置动画*/
        this.setAnimationStyle(R.style.showPopupFromBottom);

        /**背景阴影*/
        ColorDrawable dw = new ColorDrawable(Color.parseColor("#00000000"));
        this.setBackgroundDrawable(dw);

        initData(context);
    }

    private void initData(Context context) {
        ApiStore.createApi(ApiService.class)
                .getUserInfo()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new HttpObserver<BaseResp<UserInfo>>() {
                    @Override
                    public void onNext(BaseResp<UserInfo> userInfoBaseResp) {
                        if (userInfoBaseResp.getStatus() == Constant.REQUEST_SUCCESS) {
                            userInfo = userInfoBaseResp.getData();
                            GlideUtil.loadImage(context, AppConfig.BASE_URL_PIC + userInfo.getImg(), userHead);
                            GlideUtil.loadImage(context, (userInfo.getSex() == 1 ? R.mipmap.userinfo_male : R.mipmap.userinfo_female), useSex);
                            userName.setText(userInfo.getNickname());
                            userDiamond.setText(userInfo.getBrilliant());
                            userCoin.setText(userInfo.getGold());
                            newNotification.setVisibility(userInfo.getNew_notify() != 0 ? View.VISIBLE : View.GONE);
                        } else {
                            ToastUtil.show(context, Constant.GETUSERINFO);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
    }

}
