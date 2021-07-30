package org.ttweb.taskmanagement.domain.application.commands;

import org.ttweb.taskmanagement.domain.model.board.BoardId;
import org.ttweb.taskmanagement.domain.model.cardlist.CardListPosition;

import java.util.List;

public class ChangeCardListPositionsCommand {
    private BoardId boardId;
    private List<CardListPosition> cardListPositions;

    public ChangeCardListPositionsCommand(BoardId boardId, List<CardListPosition> cardListPositions) {
        this.boardId = boardId;
        this.cardListPositions = cardListPositions;
    }

    public BoardId getBoardId() {
        return boardId;
    }

    public List<CardListPosition> getCardListPositions() {
        return cardListPositions;
    }
}
