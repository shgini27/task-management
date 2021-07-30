package org.ttweb.taskmanagement.domain.model.board.events;

import org.ttweb.taskmanagement.domain.common.event.DomainEvent;
import org.ttweb.taskmanagement.domain.model.board.BoardId;
import org.ttweb.taskmanagement.domain.model.user.User;

public class BoardMemberAddedEvent extends DomainEvent {
  private static final long serialVersionUID = -7171133917149870403L;

  private BoardId boardId;
  private User user;

  public BoardMemberAddedEvent(Object source, BoardId boardId, User user) {
    super(source);
    this.boardId = boardId;
    this.user = user;
  }

  public BoardId getBoardId() {
    return boardId;
  }

  public User getUser() {
    return user;
  }
}
