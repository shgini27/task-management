package org.ttweb.taskmanagement.domain.application.commands;

import java.util.Objects;

public class RegistrationCommand {
    private String username;
    private String emailAddress;
    private String password;

    public RegistrationCommand(String username, String emailAddress, String password) {
        this.username = username;
        this.emailAddress = emailAddress;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RegistrationCommand that = (RegistrationCommand) o;
        if (!Objects.equals(username, that.username)) return false;
        if (!Objects.equals(emailAddress, that.emailAddress)) return false;
        return Objects.equals(password, that.password);
    }

    @Override
    public int hashCode() {
        int result = username != null ? username.hashCode() : 0;
        result = 31 * result + (emailAddress != null ? emailAddress.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "RegistrationCommand{" +
                "username='" + username + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
