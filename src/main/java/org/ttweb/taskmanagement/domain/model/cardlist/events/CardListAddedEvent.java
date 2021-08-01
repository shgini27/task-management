package org.ttweb.taskmanagement.domain.model.cardlist.events;

import org.ttweb.taskmanagement.domain.common.event.DomainEvent;
import org.ttweb.taskmanagement.domain.common.event.TriggeredBy;
import org.ttweb.taskmanagement.domain.model.board.events.BoardDomainEvent;
import org.ttweb.taskmanagement.domain.model.cardlist.CardList;
import org.ttweb.taskmanagement.domain.model.cardlist.CardListId;

public class CardListAddedEvent extends BoardDomainEvent {
  private static final long serialVersionUID = 7251571691840849830L;

  private CardListId cardListId;
  private String cardListName;

  public CardListAddedEvent(CardList cardList, TriggeredBy triggeredBy) {
    super(cardList.getBoardId(), triggeredBy);
    this.cardListId = cardList.getId();
    this.cardListName = cardList.getName();
  }

  public CardListId getCardListId() {
    return cardListId;
  }

  public String getCardListName() {
    return cardListName;
  }

  @Override
  public String toString() {
    return "CardListAddedEvent{" +
            "cardListId=" + cardListId +
            ", cardListName='" + cardListName + '\'' +
            '}';
  }
}
