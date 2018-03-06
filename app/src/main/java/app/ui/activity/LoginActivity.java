package app.ui.activity;

import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xy.doll.R;
import app.ui.base.BaseActivity;
import com.xy.libs.util.app.JumpUtil;
import com.xy.libs.util.storage.SPs;

import app.model.constant.Constant;
import app.model.contract.LoginContract;
import app.model.data.UserInfo;
import app.presenter.LoginPresenter;
import butterknife.BindView;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity implements LoginContract.View {

    @BindView(R.id.phone)
    EditText mEtPhone;
    @BindView(R.id.auth_code)
    EditText mEtCode;
    @BindView(R.id.login)
    TextView mTvLogin;
    @BindView(R.id.rule)
    TextView mTvRule;
    @BindView(R.id.login_rule)
    LinearLayout viewRule;
    private LoginPresenter presenter;
    private String username,password,rules;
    private UserInfo info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    @OnClick(R.id.login)
    void click(View view) {
        switch (view.getId()){
            case R.id.login:
                username = mEtPhone.getText().toString().trim();
                password = mEtCode.getText().toString().trim();
                presenter.login(username,password);
                break;
        }
    }

    @Override
    protected void initUI() {
        presenter = new LoginPresenter(this);
    }

    @Override
    protected void initData() {
        rules = getString(R.string.login_rule);
        Spannable spannable = new SpannableString(rules);
        spannable.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.hint_theme)),9,18, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        mTvRule.setText(spannable);
    }

    @Override
    public void loginOk(UserInfo userInfo) {
        showMess(Constant.LOGINOK);
        info = userInfo;
        /**
         * 存token
         * */
        SPs.put(activity,Constant.TOKEN,info.getToken());
        JumpUtil.overlay(activity,MainActivity.class);
        finish();
    }

    @Override
    public void loginErr(String errInfo) {
        showMess(errInfo);
    }

}
