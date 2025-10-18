package org.ttweb.taskmanagement.domain.common.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

//@Component
public class DefaultDomainEventPublisher implements DomainEventPublisher {
    private ApplicationEventPublisher actualPublisher;

    //@Autowired
    public DefaultDomainEventPublisher(ApplicationEventPublisher actualPublisher){
        this.actualPublisher = actualPublisher;
    }

    @Override
    public void publish(DomainEvent event) {
        actualPublisher.publishEvent(event);
    }
}
