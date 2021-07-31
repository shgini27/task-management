package org.ttweb.taskmanagement.web.socket;

import io.jsonwebtoken.JwtException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import org.ttweb.taskmanagement.domain.common.security.TokenManager;
import org.ttweb.taskmanagement.domain.model.user.UserId;

@Component
public class WebSocketRequestDispatcher extends TextWebSocketHandler {
    private static final Logger log = LoggerFactory.getLogger(WebSocketRequestDispatcher.class);

    private TokenManager tokenManager;
    private ChannelHandlerResolver channelHandlerResolver;

    @Autowired
    public WebSocketRequestDispatcher(TokenManager tokenManager, ChannelHandlerResolver channelHandlerResolver){
        this.tokenManager = tokenManager;
        this.channelHandlerResolver = channelHandlerResolver;
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession webSocketSession){
        log.debug("Web Socket Connection is Established!");
        RealTimeSession session = new RealTimeSession(webSocketSession);
        String token = session.getToken();

        try{
            UserId userId = tokenManager.verifyJwt(token);
            session.setUserId(userId);
            session.reply("authenticated");
        }catch (JwtException exception){
            log.debug("Invalid jwt token value: " + token);
            session.fail("authentication failed");
        }
    }

    @Override
    protected void handleTextMessage(WebSocketSession webSocketSession, TextMessage message) {

    }

    @Override
    public void afterConnectionClosed(WebSocketSession webSocketSession, CloseStatus status) {
        RealTimeSession session = new RealTimeSession(webSocketSession);
        SubscriptionHub.unsubscribeAll(session);
        log.debug("RealTimeSession[" + session.id() + "] Unsubscribed all channels after disconnecting");
    }
}
