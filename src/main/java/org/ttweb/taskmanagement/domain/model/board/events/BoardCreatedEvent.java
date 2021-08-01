package org.ttweb.taskmanagement.domain.model.board.events;

import org.ttweb.taskmanagement.domain.common.event.TriggeredBy;
import org.ttweb.taskmanagement.domain.model.board.Board;

public class BoardCreatedEvent extends BoardDomainEvent {
  private static final long serialVersionUID = -5529822042812866206L;

  private String boardName;

  public BoardCreatedEvent(Board board, TriggeredBy triggeredBy) {
    super(board.getId(), triggeredBy);
    this.boardName = board.getName();
  }

  public String getBoardName() {
    return boardName;
  }

  @Override
  public String toString() {
    return "BoardCreatedEvent{" +
            "boardId=" + getBoardId() +
            ", boardName='" + boardName + '\'' +
            '}';
  }
}
