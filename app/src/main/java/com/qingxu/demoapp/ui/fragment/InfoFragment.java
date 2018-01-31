package com.qingxu.demoapp.ui.fragment;

import android.os.Bundle;
import android.widget.Button;

import com.qingxu.demoapp.R;
import com.qingxu.demoapp.model.contract.MineContract;
import com.qingxu.demoapp.presenter.info.InfoPresenter;
import com.qingxu.demoapp.presenter.mine.MinePresenter;
import com.qingxu.demoapp.ui.base.BaseFragment;

import butterknife.BindView;

public class InfoFragment extends BaseFragment {

    @BindView(R.id.but)
    Button but;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_info);

        MinePresenter presenter = new MinePresenter();
//        presenter.getRankList("1", 10, "0", "2016");
//        presenter.getTeamRank();
        presenter.login("name","123456");
    }

    @Override
    protected void initUI() {

    }

    @Override
    protected void initData() {

    }
}
