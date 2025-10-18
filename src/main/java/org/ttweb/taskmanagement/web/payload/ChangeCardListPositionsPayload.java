package org.ttweb.taskmanagement.web.payload;

import org.ttweb.taskmanagement.domain.application.commands.ChangeCardListPositionsCommand;
import org.ttweb.taskmanagement.domain.model.board.BoardId;
import org.ttweb.taskmanagement.domain.model.cardlist.CardListPosition;

import java.util.List;

public class ChangeCardListPositionsPayload {
    private long boardId;
    private List<CardListPosition> cardListPositions;

    public ChangeCardListPositionsCommand toCommand() {
        return new ChangeCardListPositionsCommand(new BoardId(boardId), cardListPositions);
    }

    public void setBoardId(long boardId) {
        this.boardId = boardId;
    }

    public void setCardListPositions(List<CardListPosition> cardListPositions) {
        this.cardListPositions = cardListPositions;
    }
}
