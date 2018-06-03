package com.xy.doll.address;

import android.os.Bundle;
import android.widget.TextView;

import com.xy.doll.R;

import java.util.List;

import app.ui.base.BaseActivity;

public class PickerActivity extends BaseActivity {
    private PickAddressView fourPicker;
    private List<AddressBean> addressBeanList;
    private TextView mTvAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picker);
    }

    @Override
    protected void initUI() {
        fourPicker = findViewById(R.id.picker);
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

            }
        });
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
