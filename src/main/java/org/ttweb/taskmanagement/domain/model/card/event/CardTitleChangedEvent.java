package org.ttweb.taskmanagement.domain.model.card.event;

import org.ttweb.taskmanagement.domain.common.event.TriggeredBy;
import org.ttweb.taskmanagement.domain.model.card.Card;

public class CardTitleChangedEvent extends CardDomainEvent {
    private static final long serialVersionUID = 5113050226054739763L;

    private String newTitle;
    private String oldTitle;

    public CardTitleChangedEvent(Card card, String oldTitle, TriggeredBy triggeredBy) {
        super(card.getId(), card.getTitle(), card.getBoardId(), triggeredBy);
        this.newTitle = card.getTitle();
        this.oldTitle = oldTitle;
    }

    public String getNewTitle() {
        return newTitle;
    }

    public String getOldTitle() {
        return oldTitle;
    }

    @Override
    public String toString() {
        return "CardTitleChangedEvent{" +
                "cardId=" + getCardId() +
                ", newTitle='" + newTitle + '\'' +
                ", oldTitle='" + oldTitle + '\'' +
                '}';
    }
}
