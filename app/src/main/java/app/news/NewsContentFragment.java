package app.news;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.qingxu.demoapp.R;
import com.qingxu.demoapp.ui.base.BaseFragment;

public class NewsContentFragment extends BaseFragment {

    private View view;
    private TextView newsTitle,newsContent;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_news_content,container,false);
        return view;
    }

    @Override
    protected void initUI() {
        newsTitle = view.findViewById(R.id.news_title);
        newsContent = view.findViewById(R.id.news_content);
    }

    @Override
    protected void initData() {

    }

    public void refresh(String title,String content){
        View visibleView = view.findViewById(R.id.view);
        visibleView.setVisibility(View.VISIBLE);
        newsTitle.setText(title);
        newsContent.setText(content);
    }

}
