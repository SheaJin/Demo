package app.ui.activity;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import app.DollApplication;
import com.xy.doll.R;
import com.xy.libs.ui.adapter.CommonAdapter;
import com.xy.libs.ui.adapter.RecyclerViewUtil;
import com.xy.libs.util.app.JumpUtil;
import com.xy.libs.util.glide.GlideUtil;
import com.xy.libs.util.normal.DateUtils;
import com.xy.libs.util.normal.TextUtil;

import java.util.ArrayList;
import java.util.List;

import app.model.api.AppConfig;
import app.model.contract.PlayHistoryContract;
import app.model.data.PlayHistory;
import app.presenter.PlayHistoryPresenter;
import app.ui.adapter.PlayHistoryViewHolder;
import app.ui.base.BaseActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

public class PlayHistoryActivity extends BaseActivity implements PlayHistoryContract.View, OnRefreshLoadMoreListener {
    @BindView(R.id.rv)
    RecyclerView mRv;
    @BindView(R.id.refresh)
    SmartRefreshLayout mRefresh;

    private int page = 1;
    private PlayHistoryContract.Presenter presenter;
    private List<PlayHistory.LogListBean.ArrayChildBean> list;
    private CommonAdapter<PlayHistory.LogListBean.ArrayChildBean, PlayHistoryViewHolder> adapter;
    private String[] outcome = {null, "抓取成功", "抓取失败"};
    private String[] at_last = {null, null, null, "寄送中", "已兑换", "申诉中", "申诉成功", "申诉失败"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_history);
        ButterKnife.bind(this);
    }

    private void initRefresh() {
        mRefresh.setOnRefreshLoadMoreListener(this);
        /**设置内容不满一页的时候能否加载更多*/
        mRefresh.setEnableLoadMoreWhenContentNotFull(true);
        /**设置刷新的头部和尾部*/
        mRefresh.setRefreshHeader(new ClassicsHeader(activity));
        mRefresh.setRefreshFooter(new ClassicsFooter(activity));
        mRefresh.setHeaderHeight(50);
    }

    @Override
    protected void initUI() {
        initTitle("抓取记录");
        initRefresh();
        list = new ArrayList<>();
        RecyclerViewUtil.nestedRecyclerView(mRv, new LinearLayoutManager(this));
    }

    @Override
    protected void initData() {
        presenter = new PlayHistoryPresenter(this);
        presenter.getHistoryList(page + "");
    }

    @Override
    public void getHistoryListOk(PlayHistory playHistory) {
        if (playHistory.getLog_list().size() > 0) {
            for (PlayHistory.LogListBean logListBean : playHistory.getLog_list()) {
                list.addAll(logListBean.getArray_child());
            }
            adapter = new CommonAdapter<PlayHistory.LogListBean.ArrayChildBean, PlayHistoryViewHolder>(activity, list) {
                @Override
                public int layoutResId(int viewType) {
                    return R.layout.item_play_history;
                }

                @Override
                public PlayHistoryViewHolder holderInstance(View itemView, int viewType) {
                    return new PlayHistoryViewHolder(itemView){
                        @Override
                        public void OnItemClick(int position) {
                            super.OnItemClick(position);
//                            EventBus.getDefault().post(new ObjectEvent("", EventConstant.PLAYHISTORY));
                            JumpUtil.overlay(activity,CatchFailedDetailsActivity.class);
                        }
                    };
                }

                @Override
                public void fillView(PlayHistoryViewHolder holder, PlayHistory.LogListBean.ArrayChildBean data, int position) {
                    GlideUtil.loadImage(activity, AppConfig.BASE_URL_PIC + data.getFront_cover(), holder.mIvThumb);
                    holder.mTvName.setText(data.getToy_name());
                    String pattern;
                    if (data.getSurplus() > 24 * 60 * 60) {
                        pattern = "申诉时效：还剩 " + data.getSurplus() / 60 / 60 / 24 + " 天 " + data.getSurplus() / 60 / 60 % 24 + " 时";
                    } else if (data.getSurplus() > 60 * 60) {
                        pattern = "申诉时效：还剩 " + data.getSurplus() / 60 / 60 + " 时";
                    } else if (data.getSurplus() > 0) {
                        pattern = "申诉时效：还剩1小时";
                    } else {
                        pattern = "申诉时效：已过期";
                    }
                    holder.mTvSurplus.setText(TextUtil.setNumColor(pattern));
                    switch (data.getAt_last()) {
                        case 3:
                        case 4:
                        case 6:
                            holder.mTvStatus.setTextColor(ContextCompat.getColor(DollApplication.getInstance(), R.color.text_green));
                            holder.mTvStatus.setText("抓取成功  " + at_last[data.getAt_last()]);
                            break;
                        case 5:
                        case 7:
                            holder.mTvStatus.setTextColor(ContextCompat.getColor(DollApplication.getInstance(), R.color.text_green));
                            holder.mTvStatus.setText("抓取失败  " + at_last[data.getAt_last()]);
                            break;
                        default:
                            switch (data.getOutcome()) {
                                case 1:
                                    holder.mTvStatus.setTextColor(ContextCompat.getColor(DollApplication.getInstance(), R.color.text_green));
                                    break;
                                case 2:
                                    holder.mTvStatus.setTextColor(ContextCompat.getColor(DollApplication.getInstance(), R.color.text_black));
                                    break;
                            }
                            holder.mTvStatus.setText(outcome[data.getOutcome()]);
                            break;
                    }
                    holder.mTvTime.setText(DateUtils.getDateToString(data.getCreate_time()));
                }
            };
            mRv.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void getHistoryListErr(String errInfo) {

    }

    @Override
    public void onLoadMore(RefreshLayout refreshLayout) {
        page++;
        presenter.getHistoryList(page + "");
    }

    @Override
    public void onRefresh(RefreshLayout refreshLayout) {
        list.clear();
        mRefresh.finishRefresh(2000);
        presenter.getHistoryList(page + "");
    }
}
