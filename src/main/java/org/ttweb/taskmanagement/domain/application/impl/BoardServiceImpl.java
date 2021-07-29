package org.ttweb.taskmanagement.domain.application.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.ttweb.taskmanagement.domain.application.BoardService;
import org.ttweb.taskmanagement.domain.application.commands.CreateBoardCommand;
import org.ttweb.taskmanagement.domain.common.event.DomainEventPublisher;
import org.ttweb.taskmanagement.domain.model.board.Board;
import org.ttweb.taskmanagement.domain.model.board.BoardManagement;
import org.ttweb.taskmanagement.domain.model.board.BoardRepository;
import org.ttweb.taskmanagement.domain.model.board.events.BoardCreatedEvent;
import org.ttweb.taskmanagement.domain.model.user.UserId;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class BoardServiceImpl implements BoardService {
    private BoardRepository boardRepository;
    private BoardManagement boardManagement;
    private DomainEventPublisher domainEventPublisher;

    @Autowired
    public BoardServiceImpl(
            BoardRepository boardRepository,
            BoardManagement boardManagement,
            DomainEventPublisher domainEventPublisher){
        this.boardRepository = boardRepository;
        this.boardManagement = boardManagement;
        this.domainEventPublisher = domainEventPublisher;
    }

    @Override
    public List<Board> findBoardsByMembership(UserId userId) {
        return boardRepository.findBoardsByMembership(userId);
    }

    @Override
    public Board createBoard(CreateBoardCommand command) {
        Board board = boardManagement.createBoard(
                command.getUserId(),
                command.getName(),
                command.getDescription(),
                command.getTeamId()
        );
        domainEventPublisher.publish(new BoardCreatedEvent(this, board));
        return board;
    }
}
