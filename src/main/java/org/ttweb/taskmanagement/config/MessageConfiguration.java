package org.ttweb.taskmanagement.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessageConfiguration {
    @Bean
    public FanoutExchange domainEventsExchange() {
        return new FanoutExchange("tm.domain.events", true, false);
    }

    @Bean
    public Queue activityTrackingQueue() {
        return new Queue("tm.activity.tracking", true);
    }

    @Bean
    public Binding bindingActivityTracking(FanoutExchange exchange, Queue activityTrackingQueue) {
        return BindingBuilder.bind(activityTrackingQueue).to(exchange);
    }
}
