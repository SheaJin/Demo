package com.xy.doll.address;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.xy.doll.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liuxuliangcumt on 2017/10/30.
 * 四级联动
 */

public class PickAddressView extends LinearLayout {
    /**
     * 与选择地址相关
     */
    protected ArrayList<String> mProvinceDatas = new ArrayList<>();

    private WheelView mProvincePicker;
    private WheelView mCityPicker;
    private WheelView mCountyPicker;
    private WheelView mStreetPicker;
    protected String mCurrentProviceName;
    protected String mCurrentCityName;
    protected String mCurrentDistrictName;
    protected String mCurrentStreetName;
    private TextView cancel, ok;
    protected boolean isDataLoaded = false;
    private Context context;
    private OnAddressSelectedListener addressSelectedListener;

    public PickAddressView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs, 0);
        LayoutInflater.from(context).inflate(R.layout.address_picker, this);
        this.context = context;
        initData();
    }

    public PickAddressView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }

    private void initData() {
        mProvincePicker = findViewById(R.id.province);
        mCityPicker = findViewById(R.id.city);
        mCountyPicker = findViewById(R.id.county);
        mStreetPicker = findViewById(R.id.street);
        cancel = findViewById(R.id.box_cancel);
        ok = findViewById(R.id.box_ok);

        mProvincePicker.setOnSelectListener(new WheelView.OnSelectListener() {
            @Override
            public void endSelect(int id, String text) {
                String provinceText = mProvinceDatas.get(id);
                if (!mCurrentProviceName.equals(provinceText)) {
                    mCurrentProviceName = provinceText;
                    ArrayList<String> mCityData = new ArrayList<>();
                    cityChildsBeans = addressBeanList.get(id).getChilds();
                    for (int i = 0; i < cityChildsBeans.size(); i++) {
                        mCityData.add(cityChildsBeans.get(i).getName());
                    }
                    mCityPicker.resetData(mCityData);
                    mCityPicker.setDefault(0);
                    mCurrentCityName = mCityData.get(0);

                    ArrayList<String> mCountyData = new ArrayList<>();
                    countyChildsBeans = cityChildsBeans.get(0).getChilds();
                    for (int i = 0; i < countyChildsBeans.size(); i++) {
                        mCountyData.add(countyChildsBeans.get(i).getName());
                    }
                    mCountyPicker.resetData(mCountyData);
                    mCountyPicker.setDefault(0);
                    mCurrentDistrictName = mCountyData.get(0);

                    ArrayList<String> mStreetData = new ArrayList<>();
                    streetChildsBeans = countyChildsBeans.get(0).getChilds();
                    for (int i = 0; i < streetChildsBeans.size(); i++) {
                        mStreetData.add(streetChildsBeans.get(i).getName());
                    }
                    mStreetPicker.resetData(mStreetData);
                    mStreetPicker.setDefault(0);
                    mCurrentStreetName = mStreetData.get(0);
                }
            }

            @Override
            public void selecting(int id, String text) {
            }
        });

        mCityPicker.setOnSelectListener(new WheelView.OnSelectListener() {
            @Override
            public void endSelect(int id, String text) {
                ArrayList<String> mCountyData = new ArrayList<>();
                countyChildsBeans = cityChildsBeans.get(id).getChilds();
                for (int i = 0; i < countyChildsBeans.size(); i++) {
                    mCountyData.add(countyChildsBeans.get(i).getName());
                }
                mCountyPicker.resetData(mCountyData);
                mCountyPicker.setDefault(0);
                mCurrentDistrictName = mCountyData.get(0);

                streetChildsBeans = countyChildsBeans.get(0).getChilds();
                ArrayList<String> mStreetData = new ArrayList<>();
                for (int i = 0; i < streetChildsBeans.size(); i++) {
                    mStreetData.add(streetChildsBeans.get(i).getName());
                }
                mStreetPicker.resetData(mStreetData);
                mStreetPicker.setDefault(0);
                mCurrentStreetName = mStreetData.get(0);

            }

            @Override
            public void selecting(int id, String text) {

            }
        });

        mCountyPicker.setOnSelectListener(new WheelView.OnSelectListener() {
            @Override
            public void endSelect(int id, String text) {
                String currentName = countyChildsBeans.get(id).getName();
                if (!mCurrentDistrictName.equals(currentName)) {
                    mCurrentDistrictName = currentName;

                    streetChildsBeans = countyChildsBeans.get(id).getChilds();
                    ArrayList<String> mStreetData = new ArrayList<>();
                    for (int i = 0; i < streetChildsBeans.size(); i++) {
                        mStreetData.add(streetChildsBeans.get(i).getName());
                    }
                    mStreetPicker.resetData(mStreetData);
                    mStreetPicker.setDefault(0);
                    mCurrentStreetName = mStreetData.get(0);

                }
            }

            @Override
            public void selecting(int id, String text) {

            }
        });


        ok.setOnClickListener(view -> {
            if (addressSelectedListener != null) {
                addressSelectedListener.onEnsureClick(
                        mProvinceDatas.get(mProvincePicker.getSelected())
                        + mCityPicker.getSelectedText() + mCountyPicker.getSelectedText()
                        + mStreetPicker.getSelectedText(), streetChildsBeans);
            }

        });
        cancel.setOnClickListener(view -> {
            if (addressSelectedListener != null) {
                addressSelectedListener.onCancelClick();
            }
        });
    }

    /**
     * 读取地址数据，请使用线程进行调用
     *
     * @return
     */
    List<AddressBean> addressBeanList;
    List<AddressBean.CityChildsBean> cityChildsBeans = new ArrayList<>();
    List<AddressBean.CityChildsBean.CountyChildsBean> countyChildsBeans = new ArrayList<>();
    List<AddressBean.CityChildsBean.CountyChildsBean.StreetChildsBean> streetChildsBeans = new ArrayList<>();


    public void setOnAddressSelectedListener(OnAddressSelectedListener addressSelectedListener) {
        this.addressSelectedListener = addressSelectedListener;
    }

    public void setData(List<AddressBean> beans) {
        addressBeanList = beans;
        cityChildsBeans.clear();

        for (int i = 0; i < addressBeanList.size(); i++) {
            mProvinceDatas.add(addressBeanList.get(i).getName());
        }
        mProvincePicker.setData(mProvinceDatas);
        mProvincePicker.setDefault(0);
        mCurrentProviceName = mProvinceDatas.get(0);

        cityChildsBeans.addAll(addressBeanList.get(0).getChilds());
        ArrayList<String> mCityData = new ArrayList<>();
        for (int i = 0; i < cityChildsBeans.size(); i++) {
            mCityData.add(cityChildsBeans.get(i).getName());
        }
        mCityPicker.setData(mCityData);
        mCityPicker.setDefault(0);
        mCurrentCityName = mCityData.get(0);

        countyChildsBeans = cityChildsBeans.get(0).getChilds();
        ArrayList<String> mDistrictData = new ArrayList<>();
        for (int i = 0; i < countyChildsBeans.size(); i++) {
            mDistrictData.add(countyChildsBeans.get(i).getName());
        }
        mCountyPicker.setData(mDistrictData);
        mCountyPicker.setDefault(0);
        mCurrentDistrictName = mDistrictData.get(0);

        streetChildsBeans = countyChildsBeans.get(0).getChilds();
        ArrayList<String> mStreetData = new ArrayList<>();
        for (int i = 0; i < streetChildsBeans.size(); i++) {
            mStreetData.add(streetChildsBeans.get(i).getName());
        }
        mStreetPicker.setData(mStreetData);
        mStreetPicker.setDefault(0);
        mCurrentStreetName = mStreetData.get(0);
    }

    public interface OnAddressSelectedListener {
        void onEnsureClick(String name, List<AddressBean.CityChildsBean.CountyChildsBean.StreetChildsBean> beans);

        void onCancelClick();
    }

    public void onDistory() {
        addressBeanList.clear();
        cityChildsBeans.clear();
        countyChildsBeans.clear();
        streetChildsBeans.clear();
        mProvincePicker.resetData(new ArrayList<>());
        mCountyPicker.resetData(new ArrayList<>());
        mStreetPicker.resetData(new ArrayList<>());
        mCountyPicker.resetData(new ArrayList<>());
    }
}
