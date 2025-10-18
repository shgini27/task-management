package org.ttweb.taskmanagement.domain.application.commands;

import org.ttweb.taskmanagement.domain.model.board.BoardId;
import org.ttweb.taskmanagement.domain.model.card.CardPosition;

import java.util.List;

public class ChangeCardPositionsCommand extends UserCommand{
    private BoardId boardId;
    private List<CardPosition> cardPositions;

    public ChangeCardPositionsCommand(BoardId boardId, List<CardPosition> cardPositions) {
        this.boardId = boardId;
        this.cardPositions = cardPositions;
    }

    public BoardId getBoardId() {
        return boardId;
    }

    public List<CardPosition> getCardPositions() {
        return cardPositions;
    }
}
