package com.xy.doll.address;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.xy.doll.R;

import java.util.List;

import app.ui.base.BaseActivity;
import butterknife.OnClick;
import is.hello.go99.AnimationTools;

public class PickerActivity extends BaseActivity {
    private PickAddressView fourPicker;
    private List<AddressBean> addressBeanList;
    private TextView mTvAddress;
    private View emptyView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picker);
    }

    @Override
    protected void initUI() {
        fourPicker = findViewById(R.id.picker);
        emptyView = findViewById(R.id.view_empty);
        mTvAddress = findViewById(R.id.tv_address);
        addressBeanList = DataHelper.getAddress(this);
        fourPicker.setData(addressBeanList);
    }

    @Override
    protected void initData() {
        fourPicker.setOnAddressSelectedListener(new PickAddressView.OnAddressSelectedListener() {
            @Override
            public void onEnsureClick(String name, List<AddressBean.CityChildsBean.CountyChildsBean.StreetChildsBean> beans) {
                mTvAddress.setText(name);
            }

            @Override
            public void onCancelClick() {
                AnimationTools.getInstance().hideTransAnimation(fourPicker);
                AnimationTools.getInstance().hideAlphaAnimation(emptyView);
            }
        });
    }

    @OnClick({R.id.click, R.id.view_empty})
    void click(View view) {
        switch (view.getId()) {
            case R.id.click:
                AnimationTools.getInstance().showTransAnimation(fourPicker);
                AnimationTools.getInstance().showAlphaAnimation(emptyView);
                break;
            case R.id.view_empty:
                AnimationTools.getInstance().hideTransAnimation(fourPicker);
                AnimationTools.getInstance().hideAlphaAnimation(emptyView);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        fourPicker.onDistory();
        addressBeanList.clear();
        addressBeanList = null;
        fourPicker.removeAllViews();
        fourPicker = null;
        super.onDestroy();
    }
}
