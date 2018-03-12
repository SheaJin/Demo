package app.ui.adapter;

import android.view.View;
import android.widget.ImageView;

import com.xy.doll.R;
import com.xy.libs.ui.adapter.CommonViewHolder;

import butterknife.BindView;

/**
 * Created by jxy on 2018/3/12.
 */

public class FastSelectViewHolder extends CommonViewHolder{
    @BindView(R.id.iv_thumb)
    public ImageView mIvThumb;
    @BindView(R.id.iv_infinite)
    public ImageView mIvInfinite;
    @BindView(R.id.iv_state)
    public ImageView mIvState;

    public FastSelectViewHolder(View itemView) {
        super(itemView);
    }
}
