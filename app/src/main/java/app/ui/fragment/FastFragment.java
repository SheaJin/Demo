package app.ui.fragment;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.xy.doll.R;
import com.xy.libs.ui.adapter.CommonAdapter;
import com.xy.libs.ui.adapter.RecyclerViewUtil;
import com.xy.libs.util.app.JumpUtil;
import com.xy.libs.util.app.LogUtil;
import com.xy.libs.util.glide.GlideUtil;
import com.xy.libs.util.normal.TextUtil;

import java.util.ArrayList;
import java.util.List;

import app.model.api.AppConfig;
import app.model.data.FastEntrance;
import app.ui.activity.CommonDetailsActivity;
import app.ui.activity.RechargeActivity;
import app.ui.adapter.ImageViewHolder;
import app.ui.base.BaseFragment;
import butterknife.BindView;

/**
 * 快速入口
 */
public class FastFragment extends BaseFragment {
    @BindView(R.id.rv)
    RecyclerView mRv;
    public List<FastEntrance.FastTrackBean> list;
    private CommonAdapter<FastEntrance.FastTrackBean, ImageViewHolder> adapter;

    public FastFragment() {
    }

    @Override
    public int getLayoutResID() {
        return R.layout.fragment_fast;
    }

    public static FastFragment getInstance(List<FastEntrance.FastTrackBean> list) {
        Bundle bundle = new Bundle();
        FastFragment fastFragment = new FastFragment();
        ArrayList<FastEntrance.FastTrackBean> data = new ArrayList<>();
        data.addAll(list);
        bundle.putParcelableArrayList("list", data);
        fastFragment.setArguments(bundle);
        return fastFragment;
    }

    @Override
    protected void initUI() {
        GridLayoutManager manager = new GridLayoutManager(activity, 2);
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        RecyclerViewUtil.nestedRecyclerView(mRv, manager);
        RecyclerView.ItemDecoration itemDecoration = new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, int itemPosition, RecyclerView parent) {
                if (itemPosition < adapter.getItemCount() - 2) {
                    outRect.right = TextUtil.getPx(30, TextUtil.Orientation.Width);
                }
                if (itemPosition == 0 || itemPosition == 1) {
                    outRect.left = TextUtil.getPx(30, TextUtil.Orientation.Width);
                }
                outRect.top = TextUtil.getPx(15, TextUtil.Orientation.Width);
            }
        };
        mRv.addItemDecoration(itemDecoration);
    }

    @Override
    protected void initData() {
        list = getArguments().getParcelableArrayList("list");
        adapter = new CommonAdapter<FastEntrance.FastTrackBean, ImageViewHolder>(activity, list) {
            @Override
            public int getItemViewType(int position, FastEntrance.FastTrackBean data) {
                return 0;
            }

            @Override
            public int layoutResId(int viewType) {
                return R.layout.item_fast_entrance;
            }

            @Override
            public ImageViewHolder holderInstance(View itemView, int viewType) {
                return new ImageViewHolder(itemView){
                    @Override
                    public void OnItemClick(int position) {
                        super.OnItemClick(position);
                        LogUtil.e("type = " + list.get(position).getType());
                        switch (list.get(position).getType()) {
                            case 1:     //娃娃系列
                                Bundle bundle = new Bundle();
                                bundle.putString("series", list.get(position).getOperate_id());
                                bundle.putString("title", list.get(position).getTitle());
                                JumpUtil.overlay(activity, CommonDetailsActivity.class, bundle);
                                break;
                            case 2:     //娃娃分类
                                Bundle bundles = new Bundle();
                                bundles.putString("operate_id", list.get(position).getOperate_id());
                                bundles.putString("title", list.get(position).getTitle());
                                JumpUtil.overlay(activity, CommonDetailsActivity.class, bundles);
                                break;
                            case 5:     //充值
                                JumpUtil.overlay(activity, RechargeActivity.class);
                                break;
                        }
                    }
                };
            }

            @Override
            public void fillView(ImageViewHolder holder, FastEntrance.FastTrackBean data, int position) {
                holder.mTvName.setText(data.getTitle());
                GlideUtil.loadImage(activity, AppConfig.BASE_URL_PIC + data.getImg(), holder.mIvFast);
            }
        };
        mRv.setAdapter(adapter);
    }
}
