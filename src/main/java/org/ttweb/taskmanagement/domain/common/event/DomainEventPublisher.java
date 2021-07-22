package org.ttweb.taskmanagement.domain.common.event;

public interface DomainEventPublisher {
    void publish(DomainEvent event);
}
