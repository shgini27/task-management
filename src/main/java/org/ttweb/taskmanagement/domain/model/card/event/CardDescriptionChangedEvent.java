package org.ttweb.taskmanagement.domain.model.card.event;

import org.ttweb.taskmanagement.domain.common.event.TriggeredBy;
import org.ttweb.taskmanagement.domain.model.card.Card;

public class CardDescriptionChangedEvent extends CardDomainEvent {
    private static final long serialVersionUID = -3807373736211260958L;

    private String newDescription;
    private String oldDescription;

    public CardDescriptionChangedEvent(Card card, String oldDescription, TriggeredBy triggeredBy) {
        super(card.getId(), card.getTitle(), card.getBoardId(), triggeredBy);
        this.newDescription = card.getDescription();
        this.oldDescription = oldDescription;
    }

    public String getNewDescription() {
        return newDescription;
    }

    public String getOldDescription() {
        return oldDescription;
    }

    @Override
    public String toString() {
        return "CardDescriptionChangedEvent{" +
                "cardId=" + getCardId() +
                ", newDescription='" + newDescription + '\'' +
                ", oldDescription='" + oldDescription + '\'' +
                '}';
    }
}
