package org.ttweb.taskmanagement.infrastructure.mail;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.ttweb.taskmanagement.domain.common.mail.SimpleMessage;
import org.ttweb.taskmanagement.infrastrucure.mail.AsyncMailer;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class AsyncMailerTests {

  private JavaMailSender mailSenderMock;
  private AsyncMailer instance;

  @BeforeEach
  public void setUp() {
    mailSenderMock = mock(JavaMailSender.class);
    instance = new AsyncMailer(mailSenderMock);
  }

  @Test
  public void send_nullMessage_shouldFail() {

    Exception exception = assertThrows(IllegalArgumentException.class, () -> {
      instance.send(null);
    });
    assertTrue(exception.getMessage().contains("Parameter `message` must not be null"));
  }

  @Test
  public void send_validMessage_shouldSucceed() {
    String from = "system@taskagile.com";
    String to = "console.output@taskagile.com";
    String subject = "A test message";
    String body = "Username: test, Email Address: test@taskagile.com";

    SimpleMessage message = new SimpleMessage(to, subject, body, from);
    instance.send(message);

    SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
    simpleMailMessage.setFrom(from);
    simpleMailMessage.setTo(to);
    simpleMailMessage.setSubject(subject);
    simpleMailMessage.setText("Username: test, Email Address: test@taskagile.com");
    verify(mailSenderMock).send(simpleMailMessage);
  }

}
