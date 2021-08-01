package org.ttweb.taskmanagement.domain.model.board.events;

import org.ttweb.taskmanagement.domain.common.event.TriggeredBy;
import org.ttweb.taskmanagement.domain.model.board.BoardId;
import org.ttweb.taskmanagement.domain.model.user.User;
import org.ttweb.taskmanagement.domain.model.user.UserId;

public class BoardMemberAddedEvent extends BoardDomainEvent {
  private static final long serialVersionUID = -7171133917149870403L;

  private UserId memberUserId;
  private String memberName;

  public BoardMemberAddedEvent(BoardId boardId, User addedUser, TriggeredBy triggeredBy) {
    super(boardId, triggeredBy);
    this.memberUserId = addedUser.getId();
    this.memberName = addedUser.getFirstName() + " " + addedUser.getLastName();
  }

  public UserId getMemberUserId() {
    return memberUserId;
  }

  public String getMemberName() {
    return memberName;
  }

  @Override
  public String toString() {
    return "BoardMemberAddedEvent{" +
            "boardId=" + getBoardId() +
            ", memberUserId=" + memberUserId +
            ", memberName='" + memberName + '\'' +
            '}';
  }
}
