package app.ui.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.xy.doll.R;
import com.xy.libs.ui.adapter.CommonViewHolder;

import butterknife.BindView;

/**
 * Created by jxy on 2018/2/2.
 */

public class ImageViewHolder extends CommonViewHolder {
    @BindView(R.id.iv_fast)
    public ImageView mIvFast;
    @BindView(R.id.tv_name)
    public TextView mTvName;

    public ImageViewHolder(View itemView) {
        super(itemView);
    }
}
