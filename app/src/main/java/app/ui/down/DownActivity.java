package app.ui.down;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.xy.doll.R;
import com.xy.libs.util.normal.ToastUtil;

import app.model.api.AppConfig;
import app.ui.base.BaseActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DownActivity extends BaseActivity {

    @BindView(R.id.main_progress1)
    ProgressBar mainProgress1;
    @BindView(R.id.main_btn_down1)
    Button mainBtnDown1;
    @BindView(R.id.main_btn_cancel1)
    Button mainBtnCancel1;
    @BindView(R.id.main_progress2)
    ProgressBar mainProgress2;
    @BindView(R.id.main_btn_down2)
    Button mainBtnDown2;
    @BindView(R.id.main_btn_cancel2)
    Button mainBtnCancel2;
    @BindView(R.id.main_progress3)
    ProgressBar mainProgress3;
    @BindView(R.id.main_btn_down3)
    Button mainBtnDown3;
    @BindView(R.id.main_btn_cancel3)
    Button mainBtnCancel3;
    @BindView(R.id.activity_main)
    LinearLayout activityMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_down);
        ButterKnife.bind(this);
    }


    @Override
    protected void initUI() {

    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.main_btn_down1, R.id.main_btn_cancel1, R.id.main_btn_down2, R.id.main_btn_cancel2, R.id.main_btn_down3, R.id.main_btn_cancel3})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.main_btn_down1:
                DownloadManager.getInstance().download(AppConfig.DOWNLOAD_URL, new DownLoadObserver() {
                    @Override
                    public void onNext(DownloadInfo downloadInfo) {
                        mainProgress1.setMax((int) downloadInfo.getTotal());
                        mainProgress1.setProgress((int) downloadInfo.getProgress());
                    }

                    @Override
                    public void onComplete() {
                        if (downloadInfo != null) {
                            ToastUtil.show(activity, downloadInfo.getFileName() + "-DownloadComplete");
                        }
                    }
                });

                break;
            case R.id.main_btn_cancel1:
                DownloadManager.getInstance().cancel(AppConfig.DOWNLOAD_URL);
                break;
            case R.id.main_btn_down2:
                DownloadManager.getInstance().download(AppConfig.bigPic, new DownLoadObserver() {
                    @Override
                    public void onNext(DownloadInfo downloadInfo) {
                        mainProgress2.setMax((int) downloadInfo.getTotal());
                        mainProgress2.setProgress((int) downloadInfo.getProgress());
                    }

                    @Override
                    public void onComplete() {
                        if (downloadInfo != null) {
                            ToastUtil.show(activity, downloadInfo.getFileName() + "-DownloadComplete");
                        }
                    }
                });
                break;
            case R.id.main_btn_cancel2:

                break;
            case R.id.main_btn_down3:

                break;
            case R.id.main_btn_cancel3:

                break;
        }
    }
}
