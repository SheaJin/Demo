package com.qingxu.demoapp.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;

import com.qingxu.demoapp.R;
import com.qingxu.demoapp.model.contract.MatchContract;
import com.qingxu.demoapp.model.data.match.MatchListBean;
import com.qingxu.demoapp.presenter.match.MatchPresenter;
import com.qingxu.demoapp.ui.base.BaseFragment;
import com.qingxu.demoapp.util.DateTimeUitl;

import butterknife.BindView;

public class MatchFragment extends BaseFragment implements MatchContract.View {

    @BindView(R.id.rv)
    RecyclerView mRv;

    private MatchPresenter presenter;
    private String date;
    private MatchListBean bean;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_match);
    }

    @Override
    protected void initUI() {
        presenter = new MatchPresenter(this);
        date = DateTimeUitl.getCurrentWithFormate("yyyy-MM-dd");

    }

    @Override
    protected void initData() {
        presenter.getMatchList(date);
    }

    @Override
    public void getMatchListOk(MatchListBean matchListBean) {
        bean = matchListBean;

    }

    @Override
    public void getMatchListErr() {

    }

}
