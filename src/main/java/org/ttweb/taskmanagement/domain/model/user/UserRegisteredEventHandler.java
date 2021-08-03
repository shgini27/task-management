package org.ttweb.taskmanagement.domain.model.user;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.ttweb.taskmanagement.domain.model.user.events.UserRegisteredEvent;

@Component
public class UserRegisteredEventHandler {
    private final Log logger = LogFactory.getLog(UserRegisteredEventHandler.class);
    @EventListener(UserRegisteredEvent.class)
    public void handleEvent(UserRegisteredEvent event){
        logger.debug("Handling " + event.getUserId() + " registration event from IP: " + event.getIpAddress());
        // This is only a demonstration of the domain event listener
    }
}
