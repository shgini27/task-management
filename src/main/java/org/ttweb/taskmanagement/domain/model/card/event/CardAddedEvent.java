package org.ttweb.taskmanagement.domain.model.card.event;

import org.ttweb.taskmanagement.domain.common.event.DomainEvent;
import org.ttweb.taskmanagement.domain.model.card.Card;

public class CardAddedEvent  extends DomainEvent {
    private static final long serialVersionUID = -2494262664928935523L;

    private Card card;

    public CardAddedEvent(Object source, Card card) {
        super(source);
        this.card = card;
    }

    public Card getCard() {
        return card;
    }
}
