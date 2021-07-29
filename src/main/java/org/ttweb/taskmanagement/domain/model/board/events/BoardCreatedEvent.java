package org.ttweb.taskmanagement.domain.model.board.events;

import org.ttweb.taskmanagement.domain.common.event.DomainEvent;
import org.ttweb.taskmanagement.domain.model.board.Board;

public class BoardCreatedEvent extends DomainEvent {
  private static final long serialVersionUID = -5529822042812866206L;

  private Board board;

  public BoardCreatedEvent(Object source, Board board) {
    super(source);
    this.board = board;
  }

  public Board getBoard() {
    return board;
  }
}
