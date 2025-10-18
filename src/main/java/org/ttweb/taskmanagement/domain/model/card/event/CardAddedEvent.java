package org.ttweb.taskmanagement.domain.model.card.event;

import org.ttweb.taskmanagement.domain.common.event.TriggeredBy;
import org.ttweb.taskmanagement.domain.model.card.Card;

public class CardAddedEvent  extends CardDomainEvent {
    private static final long serialVersionUID = -2494262664928935523L;

    public CardAddedEvent(Card card, TriggeredBy triggeredBy) {
        super(card.getId(), card.getTitle(), card.getBoardId(), triggeredBy);
    }

    @Override
    public String toString() {
        return "CardAddedEvent{" +
                "cardId=" + getCardId() +
                ", cardTitle='" + getCardTitle() + '\'' +
                '}';
    }
}
