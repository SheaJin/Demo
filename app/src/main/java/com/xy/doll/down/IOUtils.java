package com.xy.doll.down;

import java.io.Closeable;
import java.io.IOException;

/**
 * Created by jxy on 2018/3/21.
 */

public class IOUtils {
    public static void closeAll(Closeable... closeables) {
        if (closeables == null) {
            return;
        }
        for (Closeable closeable : closeables) {
            if (closeable != null) {
                try {
                    closeable.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
