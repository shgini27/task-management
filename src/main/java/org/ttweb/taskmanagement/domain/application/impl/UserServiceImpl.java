package org.ttweb.taskmanagement.domain.application.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.thymeleaf.util.StringUtils;
import org.ttweb.taskmanagement.domain.application.UserService;
import org.ttweb.taskmanagement.domain.application.commands.RegistrationCommand;
import org.ttweb.taskmanagement.domain.common.event.DomainEventPublisher;
import org.ttweb.taskmanagement.domain.common.mail.MailManager;
import org.ttweb.taskmanagement.domain.common.mail.MessageVariable;
import org.ttweb.taskmanagement.domain.model.user.*;
import org.ttweb.taskmanagement.domain.model.user.events.UserRegisteredEvent;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    private RegistrationManagement registrationManagement;
    private DomainEventPublisher domainEventPublisher;
    private MailManager mailManager;
    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(
            RegistrationManagement registrationManagement,
            DomainEventPublisher domainEventPublisher,
            MailManager mailManager,
            UserRepository userRepository) {
        this.registrationManagement = registrationManagement;
        this.domainEventPublisher = domainEventPublisher;
        this.mailManager = mailManager;
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (StringUtils.isEmpty(username)) {
            throw new UsernameNotFoundException("No user found");
        }
        User user;
        if (username.contains("@")) {
            user = userRepository.findByEmailAddress(username);
        } else {
            user = userRepository.findByUsername(username);
        }

        if (user == null) {
            throw new UsernameNotFoundException("No user found by `" + username + "`");
        }
        return new SimpleUser(user);
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
