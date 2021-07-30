package org.ttweb.taskmanagement.domain.application;

import org.ttweb.taskmanagement.domain.application.commands.AddCardListCommand;
import org.ttweb.taskmanagement.domain.application.commands.ChangeCardListPositionsCommand;
import org.ttweb.taskmanagement.domain.model.board.BoardId;
import org.ttweb.taskmanagement.domain.model.cardlist.CardList;

import java.util.List;

public interface CardListService {
    /**
     * Find card lists of a board
     *
     * @param boardId id of the board
     * @return a list of card list instance or an empty list if none found
     */
    List<CardList> findByBoardId(BoardId boardId);

    /**
     * Add card list
     *
     * @param command the command instance
     * @return the newly added card list
     */
    CardList addCardList(AddCardListCommand command);

    /**
     * Change card list positions
     *
     * @param command the command instance
     */
    void changePositions(ChangeCardListPositionsCommand command);
}
