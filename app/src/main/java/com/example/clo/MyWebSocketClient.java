package com.example.clo;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import java.net.URI;
import java.net.URISyntaxException;

public class MyWebSocketClient extends WebSocketClient {

    public MyWebSocketClient(URI serverUri) {
        super(serverUri);
    }

    @Override
    public void onOpen(ServerHandshake handshakedata) {
        // Called when the WebSocket connection is opened
        System.out.println("WebSocket connection opened");
    }

    @Override
    public void onMessage(String message) {
        // Called when a message is received from the server
        System.out.println("Received message: " + message);
        // Update UI or perform actions based on the received message
    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
        // Called when the WebSocket connection is closed
        System.out.println("WebSocket connection closed");
    }

    @Override
    public void onError(Exception ex) {
        // Called when an error occurs
        ex.printStackTrace();
    }
}
