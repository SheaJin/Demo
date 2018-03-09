package app.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.xy.doll.R;
import com.xy.libs.ui.adapter.CommonAdapter;
import com.xy.libs.ui.adapter.RecyclerViewUtil;
import com.xy.libs.util.glide.GlideUtil;

import java.util.ArrayList;
import java.util.List;

import app.model.api.AppConfig;
import app.ui.adapter.GiftDetailsViewHolder;
import app.ui.base.BaseActivity;
import butterknife.BindView;

public class GiftDetailsActivity extends BaseActivity {
    @BindView(R.id.rv)
    RecyclerView mRv;

    private List<String> list;
    private CommonAdapter<String, GiftDetailsViewHolder> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gift_details);
    }

    @Override
    protected void initUI() {
        initTitle("详情");
        RecyclerViewUtil.nestedRecyclerView(mRv, new LinearLayoutManager(this));
    }

    @Override
    protected void initData() {
        list = new ArrayList<>();
        list = getIntent().getStringArrayListExtra("list");
        adapter = new CommonAdapter<String, GiftDetailsViewHolder>(activity, list) {
            @Override
            public int layoutResId(int viewType) {
                return R.layout.item_gift_details;
            }

            @Override
            public GiftDetailsViewHolder holderInstance(View itemView, int viewType) {
                return new GiftDetailsViewHolder(itemView);
            }

            @Override
            public void fillView(GiftDetailsViewHolder holder, String data, int position) {
                String url = list.get(position);
                GlideUtil.loadIntoWidth(activity, AppConfig.BASE_URL_PIC + url, holder.imageView);
            }
        };
        mRv.setAdapter(adapter);
    }


}
