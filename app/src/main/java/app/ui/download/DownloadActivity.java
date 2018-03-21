package app.ui.download;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.xy.doll.R;
import com.xy.libs.util.app.LogUtil;
import com.xy.libs.util.normal.DateUtils;
import com.xy.libs.util.normal.ToastUtil;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.concurrent.TimeUnit;

import app.ui.base.BaseActivity;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class DownloadActivity extends BaseActivity {

    private String url;
    private String fileName = "";
    private static final String DOWNLOAD_URL = "http://yixin.dl.126.net/update/installer/yixin.apk";

    private ProgressBar mProgressBar;

    private OkHttpClient mHttpClient;

    private DownloadState mState = DownloadState.PENDING;

    private int mTotal;

    private static final int DELTA = 256 * 1024;

    private OutputStream mDstOutputStream;

    private int mCurrentDownload;
    

    private enum DownloadState {
        PENDING, DOWNLOADING, PAUSE, DONE
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download);
    }

    @OnClick({R.id.bt_download, R.id.bt_download2})
    void click(View view) {
        switch (view.getId()) {
            case R.id.bt_download:
                DownloadUtil.getInstance().download(DOWNLOAD_URL, fileName, new DownloadListener() {
                    @Override
                    public void onDownload(int progress) {
                        LogUtil.e("onDownload progress = " + progress);
                    }

                    @Override
                    public void onSuccess(String path) {
                        LogUtil.e("onDownload path = " + path);
                        ToastUtil.show(activity, "下载完成");
                    }

                    @Override
                    public void onFail() {
                        LogUtil.e("onDownload onFail");
                    }
                });
                break;
            case R.id.bt_download2:
                startDownloadSelf();
                break;
        }
    }

    @Override
    protected void initUI() {
        url = "http://1254350062.vod2.myqcloud.com/78bb417bvodgzp1254350062/56ebc7647447398155127010294/f0.mp4";
        fileName = DateUtils.getDateToStrings(System.currentTimeMillis() / 1000);
    }

    @Override
    protected void initData() {
    }

    private OkHttpClient getClient() {
        if (null == mHttpClient) {
            mHttpClient = new OkHttpClient.Builder()
                    .connectTimeout(30, TimeUnit.SECONDS)
                    .readTimeout(30, TimeUnit.SECONDS)
                    .writeTimeout(30, TimeUnit.SECONDS).build();
        }
        return mHttpClient;
    }

    //开始下载
    private void startDownloadSelf() {
        getFileLength(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    //获取下载文件长度
                    mTotal = Integer.valueOf(response.header("Content-Length"));
                    mDstOutputStream = new FileOutputStream(getExternalFilesDir(null).getAbsolutePath() + "/yixin.apk");
                    downloadRange(0);
                }
            }
        });
    }

    //获取文件大小
    private void getFileLength(Callback callback) {
        Request request = new Request.Builder()
                .url(DOWNLOAD_URL)
                .method("HEAD", null)
                .build();

        getClient().newCall(request).enqueue(callback);
    }

    private void downloadRange(int start) {
        //分段下载，range参数将文件进行分片
        Request request = new Request.Builder()
                .url(DOWNLOAD_URL)
                .addHeader("range", "bytes=" + start + "-" + Math.min(start + DELTA, mTotal)).build();

        getClient().newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                mProgressBar = new ProgressBar(activity);
//                if (response.isSuccessful() && mState == DownloadState.DOWNLOADING) {
                if (response.isSuccessful()) {
                    final byte[] bytes = response.body().bytes();
                    mDstOutputStream.write(bytes);
                    mCurrentDownload += bytes.length;
                    int progress = (int) (mCurrentDownload * 1.0 / mTotal * 100);
                    LogUtil.e("downloadRange  progress" + progress);
                    if (mCurrentDownload >= mTotal) {
                        mDstOutputStream.flush();
                        mDstOutputStream.close();
                        return;
                    }

                    downloadRange(mCurrentDownload);
                }
            }
        });
    }

}
