package org.ttweb.taskmanagement.domain.application;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.ttweb.taskmanagement.domain.application.commands.RegistrationCommand;
import org.ttweb.taskmanagement.domain.model.user.RegistrationException;
import org.ttweb.taskmanagement.domain.model.user.User;
import org.ttweb.taskmanagement.domain.model.user.UserId;

public interface UserService extends UserDetailsService {
    /**
     * Find user by id
     *
     * @param userId the id of the user
     * @return a user instance or null if not found
     */
    User findById(UserId userId);

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
