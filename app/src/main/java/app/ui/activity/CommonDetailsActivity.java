package app.ui.activity;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

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
import com.xy.libs.util.normal.TextUtil;
import com.xy.libs.util.normal.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import app.model.api.AppConfig;
import app.model.contract.MachineContract;
import app.model.data.Machine;
import app.presenter.MachinePresenter;
import app.ui.adapter.CommonDetailsViewHolder;
import app.ui.base.BaseActivity;
import butterknife.BindView;

public class CommonDetailsActivity extends BaseActivity implements MachineContract.View, OnRefreshLoadMoreListener {

    @BindView(R.id.rv)
    RecyclerView mRv;
    @BindView(R.id.refresh)
    SmartRefreshLayout mRefresh;
    private List<Machine.DeviceListBean> machineList;
    private CommonAdapter<Machine.DeviceListBean, CommonDetailsViewHolder> adapter;
    private MachineContract.Presenter presenter;
    private int page;
    private Machine.DeviceListBean machines;
    private String title, genre, seriesId = "", deviceId;
    private int[] machineStatus = new int[]{0, R.mipmap.machine_status_empty,
            R.mipmap.machine_status_using,
            R.mipmap.machine_status_fixing};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common_details);
        initTitle(title);
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
        initRefresh();
        GridLayoutManager manager = new GridLayoutManager(this, 2);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        RecyclerViewUtil.nestedRecyclerView(mRv, manager);
        RecyclerView.ItemDecoration itemDecoration = new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, int itemPosition, RecyclerView parent) {
                if (itemPosition % 2 == 1) {
                    outRect.left = TextUtil.getPx(15, TextUtil.Orientation.Width);
                    outRect.right = TextUtil.getPx(30, TextUtil.Orientation.Width);
                } else {
                    outRect.left = TextUtil.getPx(30, TextUtil.Orientation.Width);
                    outRect.right = TextUtil.getPx(15, TextUtil.Orientation.Width);
                }
                outRect.top = TextUtil.getPx(15, TextUtil.Orientation.Width);
                if (itemPosition == machineList.size() - 1) {
                    outRect.bottom = TextUtil.getPx(15, TextUtil.Orientation.Width);
                }
            }
        };
        mRv.addItemDecoration(itemDecoration);
        page = 1;
        genre = getIntent().getExtras().getString("operate_id");
        title = getIntent().getExtras().getString("title");
        presenter = new MachinePresenter(this);
        presenter.getMachineList(page + "", genre, seriesId);
    }

    @Override
    protected void initData() {
        machineList = new ArrayList<>();
        adapter = new CommonAdapter<Machine.DeviceListBean, CommonDetailsViewHolder>(activity, machineList) {

            @Override
            public int layoutResId(int viewType) {
                return R.layout.item_commin_details;
            }

            @Override
            public CommonDetailsViewHolder holderInstance(View itemView, int viewType) {
                return new CommonDetailsViewHolder(itemView){
                    @Override
                    public void OnItemClick(int position) {
                        if (machineList.get(position).getState() == 3) {
                            ToastUtil.show(activity, "娃娃机正在维护，无法进入");
                        } else {
                            Bundle bundle = new Bundle();
                            deviceId = machineList.get(position).getDevice_id();
                            bundle.putString("deviceId", deviceId);
                            bundle.putString("toyId", "0");
                            JumpUtil.overlay(activity, GameActivity.class, bundle);
                        }
                    }
                };
            }

            @Override
            public void fillView(CommonDetailsViewHolder holder, Machine.DeviceListBean data, int position) {
                Machine.DeviceListBean machine = machineList.get(position);
                holder.machineName.setText(machine.getToy_name());
                holder.machinePrice.setText(machine.getBrilliant() + "钻/次");
                holder.viewNewPrice.setVisibility(View.GONE);
                holder.mIvLine.setVisibility(View.GONE);
                if (machine.getState() == 3) {
                    holder.machineFixing.setVisibility(View.VISIBLE);
                } else {
                    holder.machineFixing.setVisibility(View.GONE);
                }
                holder.machineStatus.setVisibility(View.VISIBLE);
                holder.machineStatus.setImageResource(machineStatus[machine.getState()]);
                GlideUtil.loadImage(activity, AppConfig.BASE_URL_PIC + machine.getFront_cover(), holder.machineThumb);
                GlideUtil.loadImage(activity, AppConfig.BASE_URL_PIC + machine.getCharacteristic(), holder.machineUnlimited);
                switch (machine.getInfinite()) {
                    case 0:
                        holder.machineUnlimited.setVisibility(View.GONE);
                        break;
                    case 2:
                        holder.mIvLine.setVisibility(View.VISIBLE);
                        holder.viewNewPrice.setVisibility(View.VISIBLE);
                        holder.machineNewPrice.setText(machine.getL_brilliant() + "钻/次");
                    case 1:
                        holder.machineUnlimited.setVisibility(View.VISIBLE);
                        GlideUtil.loadImage(activity, AppConfig.BASE_URL_PIC + machine.getCharacteristic(), holder.machineUnlimited);
                        break;
                }
            }
        };
        mRv.setAdapter(adapter);
    }

    @Override
    public void getMachineListOk(Machine machine) {
        machineList.addAll(machine.getDevice_list());
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

    @Override
    public void getMachineListErr(String info) {
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
        presenter.getMachineList(page + "", genre, seriesId);
    }

    @Override
    public void onRefresh(RefreshLayout refreshLayout) {
        machineList.clear();
        mRefresh.finishRefresh(2000);
        presenter.getMachineList("1", genre, seriesId);
    }
}
