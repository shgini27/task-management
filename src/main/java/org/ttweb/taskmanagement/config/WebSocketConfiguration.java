package org.ttweb.taskmanagement.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.ttweb.taskmanagement.web.socket.WebSocketRequestDispatcher;

@Configuration
@EnableWebSocket
public class WebSocketConfiguration implements WebSocketConfigurer {
    private WebSocketRequestDispatcher requestDispatcher;

    @Autowired
    public WebSocketConfiguration(WebSocketRequestDispatcher requestDispatcher){
        this.requestDispatcher = requestDispatcher;
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry webSocketHandlerRegistry) {
        webSocketHandlerRegistry.addHandler(requestDispatcher, "/rt")
                .setAllowedOrigins(
                        "http://localhost:3000",
                        "http://localhost:8000",
                        "http://192.168.4.181:3000",
                        "http://192.168.4.181:8000"
                )
                .withSockJS();
    }
}
