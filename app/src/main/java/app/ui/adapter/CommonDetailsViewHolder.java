package app.ui.adapter;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xy.doll.R;
import com.xy.libs.ui.adapter.CommonViewHolder;

import butterknife.BindView;

/**
 * Created by jxy on 2018/2/2.
 */

public class CommonDetailsViewHolder extends CommonViewHolder {

    @BindView(R.id.machine_thumb)
    public ImageView machineThumb;
    @BindView(R.id.machine_fixing)
    public ImageView machineFixing;
    @BindView(R.id.machine_unlimited)
    public ImageView machineUnlimited;
    @BindView(R.id.machine_status)
    public ImageView machineStatus;
    @BindView(R.id.tv_free)
    public TextView mTvFree;
    @BindView(R.id.tv_game)
    public TextView mTvGame;
    @BindView(R.id.view_status)
    public CardView viewStatus;
    @BindView(R.id.machine_name)
    public TextView machineName;
    @BindView(R.id.machine_price)
    public TextView machinePrice;
    @BindView(R.id.iv_line)
    public View mIvLine;
    @BindView(R.id.machine_new_price)
    public TextView machineNewPrice;
    @BindView(R.id.view_new_price)
    public LinearLayout viewNewPrice;

    public CommonDetailsViewHolder(View itemView) {
        super(itemView);
    }
}
