package org.ttweb.taskmanagement.web.socket.handlers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.ttweb.taskmanagement.web.socket.*;

@ChannelHandler("/board/*")
public class BoardChannelHandler {
    private final Logger logger = LoggerFactory.getLogger(BoardChannelHandler.class);

    @Action("subscribe")
    public void subscribe(RealTimeSession session, @ChannelValue String channel){
        logger.debug("RealTimeSession[{}] Subscribe to channel `{}`", session.id(), channel);
        SubscriptionHub.subscribe(session, channel);
    }

    @Action("unsubscribe")
    public void unsubscribe(RealTimeSession session, @ChannelValue String channel){
        logger.debug("RealTimeSession[{}] Unsubscribe from channel `{}`",
                session.id(), channel);
        SubscriptionHub.unsubscribe(session, channel);
    }
}
