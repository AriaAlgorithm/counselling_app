package com.example.clo;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;



public class ChatAdapter extends ArrayAdapter<message> {

    private Context mContext;
    private List<message> mMessages;

    public ChatAdapter(Context context, List<message> messages) {
        super(context, 0, messages);
        mContext = context;
        mMessages = messages;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        message message = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_message, parent, false);
        }

        TextView messageTextView = convertView.findViewById(R.id.messageTextView);
        messageTextView.setText(message.getText());

        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) messageTextView.getLayoutParams();

        if (message.isSentByMe()) {
            params.gravity = Gravity.END;
            messageTextView.setBackgroundResource(R.drawable.outgoing_bubble);
        } else {
            params.gravity = Gravity.START;
            messageTextView.setBackgroundResource(R.drawable.incoming_buble);
        }

        messageTextView.setLayoutParams(params);

        return convertView;
    }
}
