package app.ui.adapter;

import android.view.View;
import android.widget.TextView;

import com.xy.doll.R;
import com.xy.libs.ui.adapter.CommonViewHolder;

import butterknife.BindView;

/**
 * Created by jxy on 2018/3/15.
 */

public class CustomWindowViewHolder extends CommonViewHolder {

    @BindView(R.id.tv_item)
    public TextView mTvItem;

    public CustomWindowViewHolder(View itemView) {
        super(itemView);
    }

}
