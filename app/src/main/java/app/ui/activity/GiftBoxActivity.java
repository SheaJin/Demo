package app.ui.activity;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
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
import app.model.contract.GiftBoxContract;
import app.model.data.Gift;
import app.presenter.GiftBoxPresenter;
import app.ui.adapter.GiftBoxViewHolder;
import app.ui.base.BaseActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GiftBoxActivity extends BaseActivity implements GiftBoxContract.View, OnRefreshLoadMoreListener {
    @BindView(R.id.iv_check_all)
    ImageView mIvCheckAll;
    @BindView(R.id.tv_check_all)
    TextView mTvCheckAll;
    @BindView(R.id.tv_exchange)
    TextView mTvExchange;
    @BindView(R.id.tv_send)
    TextView mTvSend;
    @BindView(R.id.rv)
    RecyclerView mRv;
    @BindView(R.id.refresh)
    SmartRefreshLayout mRefresh;

    private boolean isSelectAll;
    private GiftBoxPresenter presenter;
    private List<Gift> list;
    private int page = 1, count = 0;
    private Gift mGift;
    private CommonAdapter<Gift, GiftBoxViewHolder> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giftbox);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.view_check_all, R.id.tv_exchange, R.id.tv_send})
    void click(View view) {
        switch (view.getId()) {
            case R.id.view_check_all:
                isSelectAll = !isSelectAll;
                if (isSelectAll) {
                    mIvCheckAll.setSelected(true);
                    mTvExchange.setEnabled(true);
                    mTvCheckAll.setText("已选" + list.size() + "个");
                } else {
                    mIvCheckAll.setSelected(false);
                    mTvExchange.setEnabled(false);
                    mTvCheckAll.setText(count == 0 ? "全选" : "已选" + count + "个");
                }
                break;
            case R.id.tv_exchange:
                JumpUtil.overlay(activity, ExchangeActivity.class);
                break;
            case R.id.tv_send:
                JumpUtil.overlay(activity, SendActivity.class);
                break;
        }
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
        initTitle("娃娃盒");
        initRefresh();
        RecyclerViewUtil.nestedRecyclerView(mRv, new LinearLayoutManager(this));
        RecyclerView.ItemDecoration itemDecoration = new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, int itemPosition, RecyclerView parent) {
                outRect.top = TextUtil.getPx(60, TextUtil.Orientation.Height);
                if (itemPosition == adapter.getItemCount() - 1) {
                    outRect.bottom = TextUtil.getPx(60, TextUtil.Orientation.Height);
                }
            }
        };
        mRv.addItemDecoration(itemDecoration);
    }

    @Override
    protected void initData() {
        list = new ArrayList<>();
        presenter = new GiftBoxPresenter(this);
        presenter.getGiftList(page + "");

        adapter = new CommonAdapter<Gift, GiftBoxViewHolder>(activity, list) {
            @Override
            public int layoutResId(int viewType) {
                return R.layout.item_gift_box;
            }

            @Override
            public GiftBoxViewHolder holderInstance(View itemView, int viewType) {
                return new GiftBoxViewHolder(itemView) {
                    @Override
                    public void OnItemClick(int position) {
                        super.OnItemClick(position);
                        Gift gift = list.get(position);
                        if (gift.isSelect()) {  //没选中
                            count--;
                            gift.setSelect(false);
                            mTvExchange.setEnabled(false);
                            mTvCheckAll.setText(count == 0 ? "全选" : "已选" + count + "个");
                            mTvSend.setEnabled(false);
                        } else {                //选中
                            count++;
                            gift.setSelect(true);
                            mTvExchange.setEnabled(true);
                            mTvSend.setEnabled(mGift.getSurplus() > 0 ? true : false);
                            mTvCheckAll.setText("已选" + count + "个");
                            mTvSend.setEnabled(gift.getSurplus() > 0 ? true : false);
                        }
                        notifyDataSetChanged();
                    }
                };
            }

            @Override
            public void fillView(GiftBoxViewHolder holder, Gift data, int position) {
                mGift = data;
                StringBuffer sb = new StringBuffer();
                sb.append("抓取时间：").append(DateUtils.getDateToString(data.getCreate_time()));
                GlideUtil.loadImage(activity, AppConfig.BASE_URL_PIC + data.getFront_cover(), holder.mIvDoll);
                holder.mTvName.setText(data.getToy_name());
                holder.mTvPrice.setText(data.getExchange());
                holder.mTvCatchTime.setText(sb);
                String pattern;
                if (data.getSurplus() > 24 * 60 * 60) {
                    pattern = "配送时效：还剩 " + data.getSurplus() / 60 / 60 / 24 + " 天 " + data.getSurplus() / 60 / 60 % 24 + " 小时";
                } else if (data.getSurplus() > 60 * 60) {
                    pattern = "配送时效：还剩 " + data.getSurplus() / 60 / 60 + " 小时";
                } else if (data.getSurplus() > 0) {
                    pattern = "配送时效：还剩 1 小时";
                } else {
                    pattern = "配送时效：已过期";
                }
                holder.mTvEndTime.setText(TextUtil.setNumColor(pattern));
                holder.mIvStatus.setSelected(data.isSelect() ? true : false);
            }
        };
        mRv.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }

    @Override
    public void getGiftListOk(List<Gift> gifts) {
        if (gifts != null) {
            list.addAll(gifts);
            adapter.notifyDataSetChanged();
            if (mRefresh.isLoading()) {
                mRefresh.finishLoadMore(true);
                page++;
            }
            if (mRefresh.isRefreshing()) {
                page = 1;
                mRefresh.finishRefresh(true);
            }
        }
    }

    @Override
    public void getGiftListErr(String info) {
        if (mRefresh.isLoading()) {
            mRefresh.finishLoadMore(false);
        }
        if (mRefresh.isRefreshing()) {
            mRefresh.finishRefresh(false);
        }
    }

    @Override
    public void onLoadMore(RefreshLayout refreshLayout) {
        page++;
        presenter.getGiftList(page + "");
        reset();
    }

    @Override
    public void onRefresh(RefreshLayout refreshLayout) {
        list.clear();
        mRefresh.finishRefresh(2000);
        presenter.getGiftList(page + "");
        reset();
    }

    private void reset() {
        count = 0;
        mTvExchange.setEnabled(false);
        mTvSend.setEnabled(false);
        mTvCheckAll.setText("全选");
    }

}
