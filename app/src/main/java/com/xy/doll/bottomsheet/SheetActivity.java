package com.xy.doll.bottomsheet;

import android.os.Bundle;
import android.widget.Button;

import com.xy.doll.R;
import com.xy.libs.util.app.LogUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import app.ui.base.BaseActivity;
import butterknife.BindView;
import butterknife.OnClick;

public class SheetActivity extends BaseActivity {
    @BindView(R.id.button)
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sheet);
    }

    @Override
    protected void initUI() {

    }

    @Override
    protected void initData() {

    }

    @OnClick(R.id.button)
    void click() {
//        printPrime();
//        printNarcissus();
//        printType();
//        printSummation();
        printDouble();
    }

    private void printDouble() {
        for (int i = 11; i < 84; i++) {
            double x = isDouble1(i);
            if (isDouble2(x)) {
                LogUtil.err("i = " + i);
            }
        }
    }

    private double isDouble1(int m) {
        double num = Math.pow(m, 2);
        double value = num - 100;
        return value;
    }

    // x + 268 = n平方
    private boolean isDouble2(double x) {
        double pow = x + 268;
        double value = Math.sqrt(pow);
        if (value == x) {
            return true;
        }
        return false;
    }

    private void printSummation() {
        /**
         * 求s=a+aa+aaa+aa...a的值
         */
        int num = 3;
        int sum = 0;
        for (int i = 0; i < 3; i++) {
            sum += num;
            num = sum * 10 + num;
            LogUtil.err("sum = " + sum);
        }
    }


    private void printType() {
        /**
         * 输入一行字符，分别统计出其中英文字母、空格、数字和其它字符的个数。
         */
        int blankSize = 0, numSize = 0, strSize = 0, chineseSize = 0;
        String str = "123SD JWWs大数据ds dj234  238s嘻嘻SJS";
        String E1 = "[\u4e00-\u9fa5]";//汉字
        String E2 = "[a-zA-Z]";
        String E3 = "[0-9]";
        String E4 = "\\s";//空格
        char[] charArray = str.toCharArray();//将字符串转化为字符数组
        String[] stringArray = new String[charArray.length];//汉字只能作为字符串处理
        for (int i = 0; i < stringArray.length; i++) {
            stringArray[i] = String.valueOf(charArray[i]);
        }
        for (String s : stringArray) {
            if (s.matches(E1)) {
                chineseSize++;
            } else if (s.matches(E2)) {
                strSize++;
            } else if (s.matches(E3)) {
                numSize++;
            } else if (s.matches(E4)) {
                blankSize++;
            }
        }
        LogUtil.err("汉字：" + chineseSize + "，字符：" + strSize + "，数字：" + numSize + "，空格：" + blankSize);
    }

    private void printNarcissus() {
        /**
         * 打印出所有的"水仙花数"，所谓"水仙花数"是指一个三位数，其各位数字立方和等于该数本身。
         * 例如：153是一个"水仙花数"，因为153=1的三次方＋5的三次方＋3的三次方。
         */
        int a = 0, b = 0, c = 0;
        List<Integer> list = new ArrayList<>();
        for (int i = 100; i < 1000; i++) {
            a = i % 10;
            b = i / 10 % 10;
            c = i / 100;
            if (i == Math.pow(a, 3) + Math.pow(b, 3) + Math.pow(c, 3)) {
                list.add(i);
            }
        }
        LogUtil.err("水仙花数为" + Arrays.toString(list.toArray()));
    }

    public void printPrime() {
        /**
         * 判断101-200之间有多少个素数，并输出所有素数。
         */
        int count = 0;
        List<Integer> list = new ArrayList<>();
        for (int i = 100; i < 200; i++) {
            boolean isPrime = true;
            for (int j = 2; j < i; j++) {
                if (i % j == 0) {
                    isPrime = false;
                }
            }
            if (isPrime) {
                count++;
                list.add(i);
            }
        }
        LogUtil.err("count = " + count + ",素数为：" + Arrays.toString(list.toArray()));
    }
}
