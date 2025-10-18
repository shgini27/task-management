package org.ttweb.taskmanagement.domain.common.mail;

public interface MailManager {
    void send(String email, String subject, String template, MessageVariable... variables);
}
