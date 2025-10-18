package org.ttweb.taskmanagement.domain.model.attachment.event;

import org.ttweb.taskmanagement.domain.common.event.TriggeredBy;
import org.ttweb.taskmanagement.domain.model.attachment.Attachment;
import org.ttweb.taskmanagement.domain.model.attachment.AttachmentId;
import org.ttweb.taskmanagement.domain.model.card.Card;
import org.ttweb.taskmanagement.domain.model.card.event.CardDomainEvent;

public class CardAttachmentAddedEvent extends CardDomainEvent {
    private static final long serialVersionUID = 477297339352417512L;

    private String cardTitle;
    private AttachmentId attachmentId;
    private String fileName;

    public CardAttachmentAddedEvent(Card card, Attachment attachment, TriggeredBy triggeredBy) {
        super(card.getId(), card.getTitle(), card.getBoardId(), triggeredBy);
        this.cardTitle = card.getTitle();
        this.attachmentId = attachment.getId();
        this.fileName = attachment.getFileName();
    }

    public String getCardTitle() {
        return cardTitle;
    }

    public AttachmentId getAttachmentId() {
        return attachmentId;
    }

    public String getFileName() {
        return fileName;
    }

    @Override
    public String toString() {
        return "CardAttachmentAddedEvent{" +
                "cardId=" + getCardId() +
                ", cardTitle='" + cardTitle + '\'' +
                ", attachmentId=" + attachmentId +
                ", fileName='" + fileName + '\'' +
                '}';
    }
}
