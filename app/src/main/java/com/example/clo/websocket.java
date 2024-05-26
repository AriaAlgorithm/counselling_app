package com.example.clo;

import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;
import java.net.InetSocketAddress;

public class websocket extends WebSocketServer {

    public websocket(int port) {
        super(new InetSocketAddress(port));
    }

    @Override
    public void onOpen(WebSocket conn, ClientHandshake handshake) {
        // Handle new WebSocket connection
    }

    @Override
    public void onClose(WebSocket conn, int code, String reason, boolean remote) {
        // Handle WebSocket connection closed
    }

    @Override
    public void onMessage(WebSocket conn, String message) {
        // Handle received message
    }

    @Override
    public void onError(WebSocket conn, Exception ex) {
        // Handle error
    }

    @Override
    public void onStart() {
        // WebSocket server started
    }
}
