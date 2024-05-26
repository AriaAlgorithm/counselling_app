package com.example.clo;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import org.java_websocket.handshake.ServerHandshake;
import java.net.URI;
import java.net.URISyntaxException;

public class chat_activity extends AppCompatActivity {

    private EditText messageEditText;
    private Button sendButton;
    private MyWebSocketClient webSocketClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chats);

        // Initialize messageEditText
        messageEditText = findViewById(R.id.messageEditText);
        if (messageEditText == null) {
            Log.e("chat_activity", "messageEditText is null");
            // Handle the case where messageEditText is not found in the layout
            return;
        }

        // Initialize other UI components
        sendButton = findViewById(R.id.sendButton);

        // Set OnClickListener for sendButton
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessage();
            }
        });

        // Initialize WebSocket client and connect
        try {
            URI uri = new URI("ws://your-websocket-server-url/chat");
            webSocketClient = new MyWebSocketClient(uri);
            webSocketClient.connect();
        } catch (URISyntaxException e) {
            e.printStackTrace();
            // Handle URI syntax exception
        }
    }

    private void sendMessage() {
        String message = messageEditText.getText().toString().trim();
        if (!message.isEmpty()) {
            // Send the message over WebSocket connection
            if (webSocketClient != null && webSocketClient.isOpen()) {
                webSocketClient.send(message);
                // Clear the message EditText after sending
                messageEditText.getText().clear();
            } else {
                Toast.makeText(chat_activity.this, "WebSocket connection not open", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(chat_activity.this, "Please enter a message", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Disconnect WebSocket connection when activity is destroyed
        if (webSocketClient != null) {
            webSocketClient.close();
        }
    }

    // Inner class for WebSocket client implementation
    private class MyWebSocketClient extends org.java_websocket.client.WebSocketClient {

        public MyWebSocketClient(URI serverUri) {
            super(serverUri);
        }

        @Override
        public void onOpen(ServerHandshake handshakedata) {
            // Handle WebSocket connection opened
        }

        @Override
        public void onMessage(String message) {
            // Handle message received from the server
        }

        @Override
        public void onClose(int code, String reason, boolean remote) {
            // Handle WebSocket connection closed
        }

        @Override
        public void onError(Exception ex) {
            // Handle WebSocket error
        }
    }
}
