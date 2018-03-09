package app.ui.adapter;

import android.view.View;
import android.widget.ImageView;

import com.xy.doll.R;
import com.xy.libs.ui.adapter.CommonViewHolder;

import butterknife.BindView;

/**
 * Created by jxy on 2018/3/9.
 */

public class GiftDetailsViewHolder extends CommonViewHolder {
    @BindView(R.id.image_view)
    public ImageView imageView;

    public GiftDetailsViewHolder(View itemView) {
        super(itemView);
    }
}
