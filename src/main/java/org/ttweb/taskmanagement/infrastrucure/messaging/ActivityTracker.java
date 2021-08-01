package org.ttweb.taskmanagement.infrastrucure.messaging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.ttweb.taskmanagement.domain.application.ActivityService;
import org.ttweb.taskmanagement.domain.common.event.DomainEvent;
import org.ttweb.taskmanagement.domain.model.activity.Activity;
import org.ttweb.taskmanagement.domain.model.activity.DomainEventToActivityConverter;

@Component
public class ActivityTracker {
    private final static Logger log = LoggerFactory.getLogger(ActivityTracker.class);

    private ActivityService activityService;
    private DomainEventToActivityConverter domainEventToActivityConverter;

    @Autowired
    public ActivityTracker(
            ActivityService activityService,
            DomainEventToActivityConverter domainEventToActivityConverter) {
        this.activityService = activityService;
        this.domainEventToActivityConverter = domainEventToActivityConverter;
    }

    @RabbitListener(queues = "#{activityTrackingQueue.name}")
    public void receive(DomainEvent domainEvent) {
        log.debug("Receive domain event: " + domainEvent);

        Activity activity = domainEventToActivityConverter.toActivity(domainEvent);
        // Save the activity only when there is an activity
        // result from the domain event
        if (activity != null) {
            activityService.saveActivity(activity);
        }
    }
}
