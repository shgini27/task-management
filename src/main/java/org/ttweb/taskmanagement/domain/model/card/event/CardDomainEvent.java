package org.ttweb.taskmanagement.domain.model.card.event;

import org.ttweb.taskmanagement.domain.common.event.DomainEvent;
import org.ttweb.taskmanagement.domain.common.event.TriggeredBy;
import org.ttweb.taskmanagement.domain.model.board.BoardId;
import org.ttweb.taskmanagement.domain.model.card.CardId;

public abstract class CardDomainEvent extends DomainEvent {
    private static final long serialVersionUID = -2644060971512964269L;

    private CardId cardId;
    private String cardTitle;
    private BoardId boardId;

    public CardDomainEvent(CardId cardId, String cardTitle, BoardId boardId, TriggeredBy triggeredBy) {
        super(triggeredBy);
        this.cardId = cardId;
        this.cardTitle = cardTitle;
        this.boardId = boardId;
    }

    public CardId getCardId() {
        return cardId;
    }

    public String getCardTitle() {
        return cardTitle;
    }

    public BoardId getBoardId() {
        return boardId;
    }
}
