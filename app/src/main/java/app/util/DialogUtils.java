package app.util;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

/**
 * Created by jxy on 2018/3/21.
 */

public class DialogUtils {

    public static void showDialog(Context context, String title, String content, boolean cancelable,
                                  String positive, DialogInterface.OnClickListener listener, String negative) {
        AlertDialog dialog = new AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(content)
                .setCancelable(cancelable)
                .setPositiveButton(positive, listener)
                .setNegativeButton(negative, (dialog1, which) -> dialog1.dismiss())
                .create();
        dialog.show();

    }
}
