package com.qingxu.demoapp.ui.pic;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.qingxu.demoapp.R;
import com.qingxu.demoapp.ui.base.BaseActivity;

import java.io.File;
import java.io.IOException;

import butterknife.BindView;
import butterknife.OnClick;
import id.zelory.compressor.Compressor;

/**
 * Created by jxy on 2018/1/10.
 */

public class ImpressPicActivity extends BaseActivity {


    @BindView(R.id.original_image)
    ImageView originalImage;
    @BindView(R.id.compress_image)
    ImageView compressedImage;
    @BindView(R.id.but_press)
    Button butPress;
    private Bitmap bitmap;
    private File file;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_impress);
    }

    @Override
    protected void initUI() {
        originalImage.setImageResource(R.mipmap.csbz);
        Bitmap b = BitmapFactory.decodeResource(getResources(), R.mipmap.niers);
        showLog(this, String.valueOf(b.getByteCount() /1024/1024+ "kb"));

        file = new File(Environment.getExternalStorageDirectory() + "/DollMachine/niers.png/");
    }

    @Override
    protected void initData() {

    }

    @Override
    protected int setFragmentContainerResId() {
        return 0;
    }

    @OnClick( R.id.but_press)
    public void click(View view) {
        switch (view.getId()) {
            case R.id.but_press:
                try {
                    bitmap = new Compressor(this).compressToBitmap(file);
                    showLog(this, String.valueOf(bitmap.getByteCount()/1024/1024 + "kb"));
                    compressedImage.setImageBitmap(bitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
        }
    }
}
