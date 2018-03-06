package app.util;

/**
 * Created by Administrator on 2017/4/17.
 */

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build;
import android.telephony.TelephonyManager;

import com.ghnor.flora.common.MD5Util;
import com.xy.doll.DollApplication;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

/**
 * 跟App相关的辅助类
 *
 *
 *
 */
public class AppUtil {

    private static TelephonyManager mTelephonyManager;

    private AppUtil() {
        /* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    /**
     * 获取应用程序名称
     */
    public static String getAppName(Context context) {
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(
                    context.getPackageName(), 0);
            int labelRes = packageInfo.applicationInfo.labelRes;
            return context.getResources().getString(labelRes);
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * [获取应用程序版本名称信息]
     *
     * @param context
     * @return 当前应用的版本名称
     */
    public static String getVersionName() {
        try {
            PackageManager packageManager = DollApplication.getInstance().getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(DollApplication.getInstance().getPackageName(), 0);
            return packageInfo.versionName;
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * [获取应用程序版本名称信息]
     * @return 当前应用的版本号
     */
    public static String getVersionCode() {
        try {
            return DollApplication.getInstance().getPackageManager().getPackageInfo(DollApplication.getInstance().getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return "1.0.0";
        }
    }

    public static int getSdkVersion(){
        return Build.VERSION.SDK_INT;
    }

    public static String getImei(){
        return MD5Util.md5(getMacAddressFromIp());
//        return MD5Util.md5(getMacAddressFromIp(), "MD5slat");
    }

    /**
     * 根据IP地址获取MAC地址
     * @return
     */
    public static String getMacAddressFromIp() {
        String strMacAddr = null;
        try {
            //获得IpD地址
            InetAddress ip = getLocalInetAddress();
            byte[] b = NetworkInterface.getByInetAddress(ip).getHardwareAddress();
            StringBuffer buffer = new StringBuffer();
            for (int i = 0; i < b.length; i++) {
                if (i != 0) {
                    buffer.append(':');
                }
                String str = Integer.toHexString(b[i] & 0xFF);
                buffer.append(str.length() == 1 ? 0 + str : str);
            }
            strMacAddr = buffer.toString().toUpperCase();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
        return strMacAddr;
    }

    /**
     * 获取移动设备本地IP
     *
     * @return
     */
    private static InetAddress getLocalInetAddress() {
        InetAddress ip = null;
        try {
            //列举
            Enumeration<NetworkInterface> en_netInterface = NetworkInterface.getNetworkInterfaces();
            while (en_netInterface.hasMoreElements()) {//是否还有元素
                NetworkInterface ni =  en_netInterface.nextElement();//得到下一个元素
                Enumeration<InetAddress> en_ip = ni.getInetAddresses();//得到一个ip地址的列举
                while (en_ip.hasMoreElements()) {
                    ip = en_ip.nextElement();
                    if (!ip.isLoopbackAddress() && ip.getHostAddress().indexOf(":") == -1)
                        break;
                    else
                        ip = null;
                }

                if (ip != null) {
                    break;
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }
        return ip;
    }
    /**
     *
     * [获取当前手机的IMEI码]
     * */
    @SuppressLint("HardwareIds")
    public static String getTelephonyManager(Context context) {
        // 获取telephony系统服务，用于取得SIM卡和网络相关信息
        if (mTelephonyManager == null) {
            mTelephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        }
        return mTelephonyManager.getDeviceId();
    }

}