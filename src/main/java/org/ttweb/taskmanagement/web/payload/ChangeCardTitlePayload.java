package org.ttweb.taskmanagement.web.payload;

import org.ttweb.taskmanagement.domain.application.commands.ChangeCardTitleCommand;
import org.ttweb.taskmanagement.domain.model.card.CardId;

public class ChangeCardTitlePayload {
    private String title;

    public ChangeCardTitleCommand toCommand(long cardId) {
        return new ChangeCardTitleCommand(new CardId(cardId), title);
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
