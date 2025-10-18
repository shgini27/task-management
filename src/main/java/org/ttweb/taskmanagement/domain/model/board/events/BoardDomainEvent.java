package org.ttweb.taskmanagement.domain.model.board.events;

import org.ttweb.taskmanagement.domain.common.event.DomainEvent;
import org.ttweb.taskmanagement.domain.common.event.TriggeredBy;
import org.ttweb.taskmanagement.domain.model.board.BoardId;

public class BoardDomainEvent extends DomainEvent {
    private static final long serialVersionUID = 3089973534352751192L;

    private BoardId boardId;

    public BoardDomainEvent(BoardId boardId, TriggeredBy triggeredBy) {
        super(triggeredBy);
        this.boardId = boardId;
    }

    public BoardId getBoardId() {
        return boardId;
    }
}
