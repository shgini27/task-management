package org.ttweb.taskmanagement.domain.application.commands;

import org.ttweb.taskmanagement.domain.model.cardlist.CardListId;
import org.ttweb.taskmanagement.domain.model.user.UserId;

public class AddCardCommand extends UserCommand{
    private CardListId cardListId;
    private String title;
    private int position;

    public AddCardCommand(CardListId cardListId, String title, int position) {
        this.cardListId = cardListId;
        this.title = title;
        this.position = position;
    }

    public CardListId getCardListId() {
        return cardListId;
    }

    public String getTitle() {
        return title;
    }

    public int getPosition() {
        return position;
    }
}
