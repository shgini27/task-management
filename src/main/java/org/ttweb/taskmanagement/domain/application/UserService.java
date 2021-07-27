package org.ttweb.taskmanagement.domain.application;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.ttweb.taskmanagement.domain.application.commands.RegistrationCommand;
import org.ttweb.taskmanagement.domain.model.user.RegistrationException;

public interface UserService extends UserDetailsService {
    /**
     * Register a new user with username, email address, and password.
     *
     * @param command instance of <code>RegistrationCommand</code>
     * @throws RegistrationException when registration failed. Possible reasons are:
     *                               1) Username already exists
     *                               2) Email address already exists.
     */
    void register(RegistrationCommand command) throws RegistrationException;
}
