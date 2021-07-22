package org.ttweb.taskmanagement.domain.model.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.ttweb.taskmanagement.domain.common.security.PasswordEncryptor;

/**
 * User registration domain service
 */
@Component
public class RegistrationManagement {
    private UserRepository repository;
    private PasswordEncryptor passwordEncryptor;

    @Autowired
    public RegistrationManagement(
            UserRepository repository,
            PasswordEncryptor passwordEncryptor){
        this.repository = repository;
        this.passwordEncryptor = passwordEncryptor;
    }

    public User register(String username, String email, String password) throws RegistrationException{
        User existingUser = repository.findByUsername(username);
        if(existingUser != null){
            throw new UsernameExistsException();
        }

        existingUser = repository.findByEmailAddress(email.toUpperCase());
        if(existingUser != null){
            throw new EmailAddressExistsException();
        }

        String encryptedPassword = passwordEncryptor.encrypt(password);
        User newUser = User.create(username, email, encryptedPassword);
        repository.save(newUser);

        return newUser;
    }
}
