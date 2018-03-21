package app.ui.download;

import android.os.Environment;
import android.text.TextUtils;

import java.io.File;
import java.io.IOException;

/**
 * Created by jxy on 2018/3/21.
 */

public class FileUtil {

    private static String rootDir = Environment.getExternalStorageDirectory() + "/Dolls/";

    public static String getFileDir() throws IOException {
        //文件路径
        String path = getRootDir() + "video/";
        boolean status = checkDir(path);
        if (status) {
            return path;
        } else {
            return "";
        }
    }

    public static String getRootDir() {
        checkDir(rootDir);
        return rootDir;
    }

    //检测文件夹路径是否存在
    public static boolean checkDir(String path) {
        //如果传进来的是null或者是空串
        if (path == null | TextUtils.isEmpty(path)) {
            return false;
        }
        //根据传入的路径创建文件对象
        File file = new File(path);
        //如果此文件不存在
        if (!file.exists()) {
            return file.mkdirs();
        } else {
            return true;
        }
    }
}
