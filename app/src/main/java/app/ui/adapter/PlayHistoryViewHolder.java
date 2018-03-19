package app.ui.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.xy.doll.R;
import com.xy.libs.ui.adapter.CommonViewHolder;

import butterknife.BindView;

/**
 * Created by jxy on 2018/3/15.
 */

public class PlayHistoryViewHolder extends CommonViewHolder {

    @BindView(R.id.doll_thumb)
    public ImageView mIvThumb;
    @BindView(R.id.doll_play)
    public ImageView mIvPlay;
    @BindView(R.id.doll_name)
    public TextView mTvName;
    @BindView(R.id.doll_status)
    public TextView mTvStatus;
    @BindView(R.id.doll_surplus)
    public TextView mTvSurplus;
    @BindView(R.id.doll_time)
    public TextView mTvTime;
    @BindView(R.id.item_line)
    public View mIvLine;

    public PlayHistoryViewHolder(View itemView) {
        super(itemView);
    }
}
