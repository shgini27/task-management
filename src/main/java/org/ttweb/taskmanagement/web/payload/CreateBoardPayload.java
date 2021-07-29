package org.ttweb.taskmanagement.web.payload;

import org.ttweb.taskmanagement.domain.application.commands.CreateBoardCommand;
import org.ttweb.taskmanagement.domain.model.team.TeamId;
import org.ttweb.taskmanagement.domain.model.user.UserId;

public class CreateBoardPayload {

  private String name;
  private String description;
  private long teamId;

  public CreateBoardCommand toCommand(UserId userId) {
    return new CreateBoardCommand(userId, name, description, new TeamId(teamId));
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public void setTeamId(long teamId) {
    this.teamId = teamId;
  }

}
