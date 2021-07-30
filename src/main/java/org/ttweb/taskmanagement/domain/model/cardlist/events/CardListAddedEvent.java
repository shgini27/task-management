package org.ttweb.taskmanagement.domain.model.cardlist.events;

import org.ttweb.taskmanagement.domain.common.event.DomainEvent;
import org.ttweb.taskmanagement.domain.model.cardlist.CardList;

public class CardListAddedEvent extends DomainEvent {
  private static final long serialVersionUID = 7251571691840849830L;

  private CardList cardList;

  public CardListAddedEvent(Object source, CardList cardList) {
    super(source);
    this.cardList = cardList;
  }

  public CardList getCardList() {
    return cardList;
  }
}
