package org.ttweb.taskmanagement.domain.application.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.ttweb.taskmanagement.domain.application.CardListService;
import org.ttweb.taskmanagement.domain.application.commands.AddCardListCommand;
import org.ttweb.taskmanagement.domain.application.commands.ChangeCardListPositionsCommand;
import org.ttweb.taskmanagement.domain.common.event.DomainEventPublisher;
import org.ttweb.taskmanagement.domain.model.board.BoardId;
import org.ttweb.taskmanagement.domain.model.cardlist.CardList;
import org.ttweb.taskmanagement.domain.model.cardlist.CardListRepository;
import org.ttweb.taskmanagement.domain.model.cardlist.events.CardListAddedEvent;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class CardListServiceImpl implements CardListService {
    private CardListRepository cardListRepository;
    private DomainEventPublisher domainEventPublisher;

    @Autowired
    public CardListServiceImpl(
            CardListRepository cardListRepository,
            DomainEventPublisher domainEventPublisher){
        this.cardListRepository = cardListRepository;
        this.domainEventPublisher = domainEventPublisher;
    }

    @Override
    public List<CardList> findByBoardId(BoardId boardId) {
        return cardListRepository.findByBoardId(boardId);
    }

    @Override
    public CardList addCardList(AddCardListCommand command) {
        CardList cardList = CardList.create(
                command.getBoardId(),
                command.getUserId(),
                command.getName(),
                command.getPosition()
        );
        cardListRepository.save(cardList);
        domainEventPublisher.publish(new CardListAddedEvent(this, cardList));
        return cardList;
    }

    @Override
    public void changePositions(ChangeCardListPositionsCommand command) {
        cardListRepository.changePositions(command.getCardListPositions());
    }
}
