package com.qingxu.demoapp.ui.pic;

import android.os.Bundle;
import android.os.Environment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.qingxu.demoapp.R;
import com.qingxu.demoapp.model.api.AppConfig;
import com.qingxu.demoapp.ui.adapter.MyPagerAdapter;
import com.qingxu.demoapp.ui.base.BaseActivity;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import top.zibin.luban.Luban;
import top.zibin.luban.OnCompressListener;

public class UploadImageActivity extends BaseActivity {

    @BindView(R.id.but)
    Button but;
    @BindView(R.id.pager)
    ViewPager pager;
    private String path, url;
    private File file;
    ArrayList<String> photos = new ArrayList<>();
    private List<View> viewList;
    private List<String> list;

    private MyPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ok_http);
        ButterKnife.bind(this);
    }

    @Override
    protected void initUI() {
        photos.add(AppConfig.bigPic);
        list = new ArrayList<>();
        viewList = new ArrayList<>();
    }

    @Override
    protected void initData() {
        list.clear();
        list.add(AppConfig.testUrl1);
        list.add(AppConfig.testUrl2);
        list.add(AppConfig.testUrl3);
        list.add(AppConfig.testUrl4);
        viewList.clear();
        adapter = new MyPagerAdapter(viewList);

        for (int i = 0; i < list.size(); i++) {
            ImageView imageView = new ImageView(this);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            Glide.with(this).load(list.get(i)).into(imageView);
            viewList.add(imageView);
            adapter.notifyDataSetChanged();
        }
        pager.setAdapter(adapter);
        pager.setCurrentItem(1);

        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }

        });
    }

    @Override
    protected int setFragmentContainerResId() {
        return 0;
    }

    @OnClick(R.id.but)
    public void click() {

    }


    /**
     * LuBan压缩
     */
    private void compressWithLs(final List<String> photos) {
        Luban.with(this)
                .load(photos)
                .ignoreBy(100)
                .setTargetDir(getPath())
                .setCompressListener(new OnCompressListener() {
                    @Override
                    public void onStart() {
                    }

                    @Override
                    public void onSuccess(File file) {

                    }

                    @Override
                    public void onError(Throwable e) {
                    }
                }).launch();
    }

    private String getPath() {
        String path = Environment.getExternalStorageDirectory() + "/Luban/image/";
        File file = new File(path);
        if (file.mkdirs()) {
            return path;
        }
        return path;
    }

    public static byte[] loadRawDataFromURL(String u) throws Exception {
        URL url = new URL(u);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        InputStream is = conn.getInputStream();
        BufferedInputStream bis = new BufferedInputStream(is);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        final int BUFFER_SIZE = 2048;
        final int EOF = -1;

        int c;
        byte[] buf = new byte[BUFFER_SIZE];

        while (true) {
            c = bis.read(buf);
            if (c == EOF)
                break;

            baos.write(buf, 0, c);
        }

        conn.disconnect();
        is.close();

        byte[] data = baos.toByteArray();
        baos.flush();

        return data;
    }
}
