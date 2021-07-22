package org.ttweb.taskmanagement.domain.application.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.ttweb.taskmanagement.domain.application.commands.RegistrationCommand;
import org.ttweb.taskmanagement.domain.common.event.DomainEventPublisher;
import org.ttweb.taskmanagement.domain.common.mail.MailManager;
import org.ttweb.taskmanagement.domain.common.mail.MessageVariable;
import org.ttweb.taskmanagement.domain.model.user.*;
import org.ttweb.taskmanagement.domain.model.user.events.UserRegisteredEvent;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class UserServiceImplTest {
    private RegistrationManagement registrationManagementMock;
    private DomainEventPublisher eventPublisherMock;
    private MailManager mailManagerMock;
    private UserServiceImpl instance;

    @BeforeEach
    public void setUp(){
        registrationManagementMock = mock(RegistrationManagement.class);
        eventPublisherMock = mock(DomainEventPublisher.class);
        mailManagerMock = mock(MailManager.class);
        instance = new UserServiceImpl(
                registrationManagementMock,
                eventPublisherMock,
                mailManagerMock
        );
    }

    @Test
    public void register_nullCommand_shouldFail() throws RegistrationException {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            instance.register(null);
        });

        String message = exception.getMessage();

        assertTrue(message.contains("exception"));
    }

    @Test
    public void register_existingUsername_shouldFail() throws RegistrationException {
        String username = "existing";
        String emailAddress = "sunny@taskagile.com";
        String password = "MyPassword!";
        doThrow(UsernameExistsException.class).when(registrationManagementMock)
                .register(username, emailAddress, password);

        RegistrationCommand command = new RegistrationCommand(username, emailAddress, password);

        Exception exception = assertThrows(RegistrationException.class, () -> {
            instance.register(command);
        });

        assertTrue(exception.getMessage().contains("exception"));
    }

    @Test
    public void register_existingEmailAddress_shouldFail() throws RegistrationException {
        String username = "sunny";
        String emailAddress = "existing@taskagile.com";
        String password = "MyPassword!";
        doThrow(EmailAddressExistsException.class).when(registrationManagementMock)
                .register(username, emailAddress, password);

        RegistrationCommand command = new RegistrationCommand(username, emailAddress, password);

        Exception exception = assertThrows(RegistrationException.class, () -> {
            instance.register(command);
        });

        assertTrue(exception.getMessage().contains("exception"));
    }

    @Test
    public void register_validCommand_shouldSucceed() throws RegistrationException {
        String username = "sunny";
        String emailAddress = "sunny@taskagile.com";
        String password = "MyPassword!";

        User newUser = User.create(username, emailAddress, password);
        when(registrationManagementMock.register(username, emailAddress, password))
                .thenReturn(newUser);
        RegistrationCommand command = new RegistrationCommand(username, emailAddress, password);

        instance.register(command);

        verify(mailManagerMock).send(
                emailAddress,
                "Welcome to Task Management Application",
                "welcome.ftl",
                MessageVariable.from("user", newUser)
        );
        verify(eventPublisherMock).publish(new UserRegisteredEvent(newUser));
    }
}
