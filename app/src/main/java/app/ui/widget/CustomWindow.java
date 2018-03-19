package app.ui.widget;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

import com.xy.doll.R;
import com.xy.libs.ui.adapter.CommonAdapter;
import com.xy.libs.ui.adapter.RecyclerViewUtil;

import java.util.List;

import app.ui.adapter.CustomWindowViewHolder;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by jxy on 2018/3/15.
 */

public class CustomWindow extends PopupWindow {

    private View view;
    @BindView(R.id.rv_window)
    RecyclerView mRv;
    private OnChooseListener listener;
    private CommonAdapter<String, CustomWindowViewHolder> adapter;
    private List<String> list;

    public CustomWindow(Context context, List<String> list) {
        super(context);
        this.list = list;
        init(context);
    }

    @OnClick(R.id.window_cancel)
    void cick(View view) {
        switch (view.getId()) {
            case R.id.window_cancel:
                dismiss();
                break;
        }
    }

    private void init(Context context) {
        view = View.inflate(context, R.layout.layout_custom_window, null);
        ButterKnife.bind(this, view);
        this.setContentView(view);

        this.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        this.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);

        this.setFocusable(true);
        this.setTouchable(true);
        this.setOutsideTouchable(false);
        /**设置动画*/
        this.setAnimationStyle(R.style.showPopupFromBottom);

        /**背景阴影*/
        ColorDrawable dw = new ColorDrawable(Color.parseColor("#00000000"));
        this.setBackgroundDrawable(dw);
        initData(context);
    }

    private void initData(Context context) {
        RecyclerViewUtil.nestedRecyclerView(mRv, new LinearLayoutManager(context));
        adapter = new CommonAdapter<String, CustomWindowViewHolder>(context, list) {
            @Override
            public int layoutResId(int viewType) {
                return R.layout.item_custom_window;
            }

            @Override
            public CustomWindowViewHolder holderInstance(View itemView, int viewType) {
                return new CustomWindowViewHolder(itemView) {
                    @Override
                    public void OnItemClick(int position) {
                        super.OnItemClick(position);
                        listener.chooseItem(list.get(position));
                    }
                };
            }

            @Override
            public void fillView(CustomWindowViewHolder holder, String data, int position) {
                holder.mTvItem.setText(list.get(position));
            }
        };

        mRv.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    public interface OnChooseListener {
        void chooseItem(String msg);
    }

    public void setListener(OnChooseListener listener) {
        this.listener = listener;
    }

}
