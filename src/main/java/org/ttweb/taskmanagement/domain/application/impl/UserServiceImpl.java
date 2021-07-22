package org.ttweb.taskmanagement.domain.application.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.ttweb.taskmanagement.domain.application.UserService;
import org.ttweb.taskmanagement.domain.application.commands.RegistrationCommand;
import org.ttweb.taskmanagement.domain.common.event.DomainEventPublisher;
import org.ttweb.taskmanagement.domain.common.mail.MailManager;
import org.ttweb.taskmanagement.domain.common.mail.MessageVariable;
import org.ttweb.taskmanagement.domain.model.user.RegistrationException;
import org.ttweb.taskmanagement.domain.model.user.RegistrationManagement;
import org.ttweb.taskmanagement.domain.model.user.User;
import org.ttweb.taskmanagement.domain.model.user.events.UserRegisteredEvent;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    private RegistrationManagement registrationManagement;
    private DomainEventPublisher domainEventPublisher;
    private MailManager mailManager;

    @Autowired
    public UserServiceImpl(
            RegistrationManagement registrationManagement,
            DomainEventPublisher domainEventPublisher,
            MailManager mailManager) {
        this.registrationManagement = registrationManagement;
        this.domainEventPublisher = domainEventPublisher;
        this.mailManager = mailManager;
    }

    @Override
    public void register(RegistrationCommand command) throws RegistrationException {
        Assert.notNull(command, "Parameter `command` must not be null");

        User newUser = registrationManagement.register(
                command.getUsername(),
                command.getEmailAddress(),
                command.getPassword()
        );

        sendWelcomeMessage(newUser);
        domainEventPublisher.publish(new UserRegisteredEvent(newUser));
    }

    private void sendWelcomeMessage(User user) {
        mailManager.send(
                user.getEmailAddress(),
                "Welcome to Task Management",
                "welcome.ftl",
                MessageVariable.from("user", user)
        );
    }
}
