package app.ui.download;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by jxy on 2018/3/21.
 */

public class DownloadUtil {

    public static OkHttpClient okHttpClient;
    public DownloadListener downloadListener;
    public static DownloadUtil downloadUtil;
    public static final int ONDOWNLOAD = 1;
    public static final int ONSUCCESS = 2;
    public static final int ONFAIL = 3;

    public DownloadUtil() {
        okHttpClient = new OkHttpClient();
    }

    public static DownloadUtil getInstance() {
        if (downloadUtil == null) {
            downloadUtil = new DownloadUtil();
        }
        return downloadUtil;
    }

    public void download(String url, String fileName, DownloadListener downloadListener) {
        this.downloadListener = downloadListener;
        Request request = new Request.Builder().url(url).build();
        if (okHttpClient != null)
            okHttpClient.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    Message message = Message.obtain();
                    message.what = ONFAIL;
                    handler.sendMessage(message);
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    InputStream is = null;
                    FileOutputStream fos = null;
                    byte[] butter = new byte[4096];
                    int len = 0;
                    try {
                        is = response.body().byteStream();
                        long total = response.body().contentLength();
                        File file = new File(FileUtil.getFileDir(), fileName +".mp4");
                        if (file.exists()) {
                            file.delete();
                        }
                        fos = new FileOutputStream(file);
                        long sum = 0;
                        while ((len = is.read(butter)) != -1) {
                            fos.write(butter, 0, len);
                            sum += len;
                            int progress = (int) (sum * 1.0f / total * 100);
                            /**
                             * 下载中
                             */
                            Message message = Message.obtain();
                            message.what = ONDOWNLOAD;
                            message.obj = progress;
                            handler.sendMessage(message);
                        }
                        fos.flush();
                        /**
                         * 下载完成
                         */
                        Message message = Message.obtain();
                        message.what = ONSUCCESS;
                        message.obj = file.getAbsolutePath();
                        handler.sendMessage(message);
                    } catch (Exception e) {

                    } finally {
                        if (is != null) {
                            is.close();
                        }
                        if (fos != null) {
                            fos.close();
                        }
                    }
                }
            });
    }

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case ONDOWNLOAD:
                    downloadListener.onDownload((int) msg.obj);
                    break;
                case ONSUCCESS:
                    downloadListener.onSuccess((String) msg.obj);
                    break;
                case ONFAIL:
                    downloadListener.onFail();
                    break;
            }
        }
    };
}
