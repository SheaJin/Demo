package com.xy.doll.hy;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.xy.doll.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jxy on 2018/4/4.
 */

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ChatViewHolder> {
    private List<ChatBean> chatList = new ArrayList<>();
    private Activity activity;
    public static final int TYPE_SELF = 0, TYPE_OTHER = 1;

    public ChatAdapter(Activity activity) {
        this.activity = activity;
    }

    public void setChatBean(ChatBean chatBean) {
        chatList.add(chatBean);
    }

    @Override
    public int getItemViewType(int position) {
        if (position % 2 == 0) {
            return TYPE_SELF;
        } else {
            return TYPE_OTHER;
        }
    }

    @Override
    public ChatViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_SELF) {
            View view = View.inflate(activity, R.layout.item_chat_right, null);
            return new ChatViewHolder(view);
        } else {
            View view = View.inflate(activity, R.layout.item_chat_left, null);
            return new ChatViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(ChatViewHolder holder, int position) {
        ChatBean bean = chatList.get(position);
        /**
         * 解析表情
         */
        SpannableString message = EmojiConversionUtils.INSTANCE.getExpressionString(activity, bean.message);
        holder.mTvSendMsg.setText(message);
    }

    @Override
    public int getItemCount() {
        return chatList.size();
    }

    public class ChatViewHolder extends RecyclerView.ViewHolder {
        private ImageView mIvSendHead;
        private TextView mTvSendMsg;

        public ChatViewHolder(View itemView) {
            super(itemView);
            mIvSendHead = itemView.findViewById(R.id.head_send);
            mTvSendMsg = itemView.findViewById(R.id.tv_send_msg);
        }
    }

}
