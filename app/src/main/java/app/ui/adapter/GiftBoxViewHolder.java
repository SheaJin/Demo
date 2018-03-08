package app.ui.adapter;

import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.xy.doll.R;
import com.xy.libs.ui.adapter.CommonViewHolder;

import butterknife.BindView;

/**
 * Created by jxy on 2018/3/7.
 */

public class GiftBoxViewHolder extends CommonViewHolder {

    @BindView(R.id.iv_status)
    public ImageView mIvStatus;
    @BindView(R.id.iv_doll)
    public ImageView mIvDoll;
    @BindView(R.id.view_status)
    public CardView viewStatus;
    @BindView(R.id.tv_name)
    public TextView mTvName;
    @BindView(R.id.tv_price)
    public TextView mTvPrice;
    @BindView(R.id.tv_catch_time)
    public TextView mTvCatchTime;
    @BindView(R.id.tv_end_time)
    public TextView mTvEndTime;

    public GiftBoxViewHolder(View itemView) {
        super(itemView);
    }
}
