package com.example.clo;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class chat_activity extends AppCompatActivity {
    ListView chatListView;
    EditText messageInput;
    Button sendButton;
    String userId, counselorId;
    ArrayList<String> messages = new ArrayList<>();
    ChatAdapter chatAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat);

        chatListView = findViewById(R.id.chatListView);
        messageInput = findViewById(R.id.messageInput);
        sendButton = findViewById(R.id.sendButton);

        // Assume userId and counselorId are retrieved from intent or session
        userId = "1"; // This should be dynamic
        counselorId = "2"; // This should be dynamic

        chatAdapter = new ChatAdapter(this, messages);
        chatListView.setAdapter(chatAdapter);

        loadMessages();

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = messageInput.getText().toString();
                if (!message.isEmpty()) {
                    sendMessage(message);
                }
            }
        });
    }

    private void loadMessages() {
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://lamp.ms.wits.ac.za/home/s2689889/Load_messages.php";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Parse response and update messages list
                        // Update chatAdapter
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // Handle error
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("userId", userId);
                params.put("counselorId", counselorId);
                return params;
            }
        };
        queue.add(stringRequest);
    }

    private void sendMessage(final String message) {
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://lamp.ms.wits.ac.za/home/s2689889/Send_message.php";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.equals("success")) {
                            messages.add(message);
                            chatAdapter.notifyDataSetChanged();
                            messageInput.setText("");
                        } else {
                            Toast.makeText(chat_activity.this, "Message sending failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(chat_activity.this, "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("senderId", userId);
                params.put("receiverId", counselorId);
                params.put("message", message);
                return params;
            }
        };
        queue.add(stringRequest);
    }
}
