package org.ttweb.taskmanagement.domain.common.security;

import org.springframework.stereotype.Component;

@Component
public class PasswordEncryptorDelegator implements PasswordEncryptor {
    @Override
    public String encrypt(String rawPassword) {
        //TODO: implement password encryption
        return rawPassword;
    }
}
