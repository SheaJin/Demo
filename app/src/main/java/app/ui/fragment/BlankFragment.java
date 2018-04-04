package app.ui.fragment;


import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.Gravity;
import android.view.View;

import com.xy.doll.R;

import app.ui.base.BaseFragment;
import butterknife.BindView;
import butterknife.ButterKnife;
import q.rorbin.badgeview.QBadgeView;

/**
 * A simple {@link Fragment} subclass.
 */
public class BlankFragment extends BaseFragment {

    @BindView(R.id.rl)
    View cardView;
//    private CardView cardView;
    public BlankFragment() {
//        ButterKnife.bind(activity);
    }

    @Override
    public int getLayoutResID() {
        return R.layout.fragment_blank;
    }

    @Override
    protected void initUI() {
        ButterKnife.bind(activity);
        cardView = getView(R.id.rl);
//        new QBadgeView(activity)
//                .setBadgePadding(10,true)
//                .bindTarget(cardView)
//                .setBadgeNumber(5);

    }

    @Override
    protected void initData() {

    }

}
