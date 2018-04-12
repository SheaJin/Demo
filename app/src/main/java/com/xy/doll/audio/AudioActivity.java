package com.xy.doll.audio;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;


import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.easeui.EaseConstant;
import com.hyphenate.easeui.widget.EaseVoiceRecorderView;
import com.xy.doll.R;

import app.ui.base.BaseActivity;
import butterknife.BindView;
import butterknife.OnClick;

public class AudioActivity extends BaseActivity {

    @BindView(R.id.voice)
    EaseVoiceRecorderView voiceRecorderView;
    @BindView(R.id.button)
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio);
    }

    @OnClick(R.id.button)
    void click(View v, MotionEvent event){
        voiceRecorderView.onPressToSpeakBtnTouch(v, event, new EaseVoiceRecorderView.EaseVoiceRecorderCallback() {
            @Override
            public void onVoiceRecordComplete(String voiceFilePath, int voiceTimeLength) {
                sendVoiceMessage(voiceFilePath,voiceTimeLength);
            }
        });
    }

    private void sendVoiceMessage(String filePath, int length){
        EMMessage message = EMMessage.createVoiceSendMessage(filePath, length, "jxy2");
        sendMessage(message);
    }

    protected void sendMessage(EMMessage message){
        if (message == null) {
            return;
        }

//        if (chatType == EaseConstant.CHATTYPE_GROUP){
//            message.setChatType(EMMessage.ChatType.GroupChat);
//        }else if(chatType == EaseConstant.CHATTYPE_CHATROOM){
            message.setChatType(EMMessage.ChatType.ChatRoom);
//        }
        //Add to conversation
        EMClient.getInstance().chatManager().saveMessage(message);
        //refresh ui
//        if(isMessageListInited) {
//            messageList.refreshSelectLast();
//        }
    }

    @Override
    protected void initUI() {

    }

    @Override
    protected void initData() {

    }

}
