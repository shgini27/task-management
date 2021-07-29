package org.ttweb.taskmanagement.web.payload;

import org.ttweb.taskmanagement.domain.application.commands.CreateTeamCommand;
import org.ttweb.taskmanagement.domain.model.user.UserId;

public class CreateTeamPayload {

  private String name;

  public CreateTeamCommand toCommand(UserId userId) {
    return new CreateTeamCommand(userId, name);
  }

  public void setName(String name) {
    this.name = name;
  }
}
