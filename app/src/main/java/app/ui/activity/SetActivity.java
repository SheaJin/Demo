package app.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Switch;
import android.widget.TextView;

import com.xy.doll.R;
import com.xy.libs.util.app.JumpUtil;

import org.greenrobot.eventbus.EventBus;

import app.model.constant.Constant;
import app.model.constant.EventConstant;
import app.model.constant.ObjectEvent;
import app.ui.base.BaseActivity;
import butterknife.BindView;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class SetActivity extends BaseActivity {

    @BindView(R.id.tv_person)
    TextView mTvPerson;
    @BindView(R.id.img_avatar)
    CircleImageView imgAvatar;
    @BindView(R.id.tv_nickname)
    TextView mTvNickname;
    @BindView(R.id.switch_back_music)
    Switch swBackMusic;
    @BindView(R.id.switch_back_sound)
    Switch swBackSound;
    @BindView(R.id.switch_notification)
    Switch swNotification;
    @BindView(R.id.logout)
    TextView mTvLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
    }

    @Override
    protected void initUI() {
        initTitle(Constant.SYSTEMSETTING);

    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.view_userinfo, R.id.view_order, R.id.view_recharge_history, R.id.view_address, R.id.view_clean, R.id.view_bgm,
            R.id.view_bgms, R.id.view_notification, R.id.view_invitation, R.id.view_lucky, R.id.view_about, R.id.logout})
    void click(View view) {
        switch (view.getId()) {
            case R.id.view_userinfo:

                break;
            case R.id.view_order:
                JumpUtil.overlay(activity, MyOrderActivity.class);
                break;
            case R.id.view_recharge_history:

                break;
            case R.id.view_address:

                break;
            case R.id.view_clean:

                break;
            case R.id.view_bgm:

                break;
            case R.id.view_bgms:

                break;
            case R.id.view_notification:

                break;
            case R.id.view_invitation:

                break;
            case R.id.view_lucky:

                break;
            case R.id.view_about:

                break;
            case R.id.logout:
                JumpUtil.overlay(activity, LoginActivity.class);
                EventBus.getDefault().post(new ObjectEvent("", EventConstant.LOGOUT));
                finish();
                break;
        }
    }
}
