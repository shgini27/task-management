package org.ttweb.taskmanagement.domain.model.user.events;

import org.ttweb.taskmanagement.domain.common.event.DomainEvent;
import org.ttweb.taskmanagement.domain.common.event.TriggeredFrom;
import org.ttweb.taskmanagement.domain.model.user.User;

public class UserRegisteredEvent extends DomainEvent {
    private static final long serialVersionUID = 6172166568619207332L;

    public UserRegisteredEvent(User user, TriggeredFrom triggeredFrom) {
        super(user.getId(), triggeredFrom);
    }

    @Override
    public String toString() {
        return "UserRegisteredEvent{userId=" + getUserId() + '}';
    }
}
