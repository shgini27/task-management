package org.ttweb.taskmanagement.domain.common.mail;

import org.springframework.stereotype.Component;

@Component
public class DefaultMailManager implements MailManager {
    @Override
    public void send(String email, String subject, String template, MessageVariable... variables) {
        //TODO: implement mail sender
    }
}
