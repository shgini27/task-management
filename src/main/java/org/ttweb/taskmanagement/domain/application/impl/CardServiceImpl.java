package org.ttweb.taskmanagement.domain.application.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.ttweb.taskmanagement.domain.application.CardService;
import org.ttweb.taskmanagement.domain.application.commands.AddCardCommand;
import org.ttweb.taskmanagement.domain.application.commands.ChangeCardPositionsCommand;
import org.ttweb.taskmanagement.domain.common.event.DomainEventPublisher;
import org.ttweb.taskmanagement.domain.model.board.BoardId;
import org.ttweb.taskmanagement.domain.model.card.Card;
import org.ttweb.taskmanagement.domain.model.card.CardRepository;
import org.ttweb.taskmanagement.domain.model.card.event.CardAddedEvent;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class CardServiceImpl implements CardService {
    private CardRepository cardRepository;
    private DomainEventPublisher domainEventPublisher;

    @Autowired
    public CardServiceImpl(
            CardRepository cardRepository,
            DomainEventPublisher domainEventPublisher){
        this.cardRepository = cardRepository;
        this.domainEventPublisher = domainEventPublisher;
    }

    @Override
    public List<Card> findByBoardId(BoardId boardId) {
        return cardRepository.findByBoardId(boardId);
    }

    @Override
    public Card addCard(AddCardCommand command) {
        Card card = Card.create(
                command.getCardListId(),
                command.getUserId(),
                command.getTitle(),
                command.getPosition()
        );
        cardRepository.save(card);
        domainEventPublisher.publish(new CardAddedEvent(this, card));
        return card;
    }

    @Override
    public void changePositions(ChangeCardPositionsCommand command) {
        cardRepository.changePositions(command.getCardPositions());
    }
}
