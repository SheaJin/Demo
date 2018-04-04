package com.xy.doll.audio;

import android.os.Bundle;
import android.view.View;

import com.xy.doll.R;
import com.xy.libs.util.app.LogUtil;

import app.ui.base.BaseActivity;
import butterknife.BindView;

public class AudioActivity extends BaseActivity implements View.OnLongClickListener {

    @BindView(R.id.audio)
    AudioRecorderButton audio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio);
    }


    @Override
    protected void initUI() {
        audio.setAudioFinishRecorderListener((seconds, FilePath) -> {
            LogUtil.e("AudioActivity 语音文件为：" + FilePath + "时长：" + seconds);
            //拿到文件地址和时长后就可以去做发送语音的操作了

        });
    }

    @Override
    protected void initData() {

    }

    @Override
    public boolean onLongClick(View v) {

        return false;
    }
}
