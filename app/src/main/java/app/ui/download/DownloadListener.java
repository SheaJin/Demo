package app.ui.download;

/**
 * Created by jxy on 2018/3/21.
 */

public interface DownloadListener {

    /**
     * 下载进度
     *
     * @param progress
     */
    void onDownload(int progress);

    /**
     * 下载完成路径
     *
     * @param path
     */
    void onSuccess(String path);

    /**
     * 下载失败
     */
    void onFail();
}
