package org.ttweb.taskmanagement.domain.application.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.ttweb.taskmanagement.domain.application.BoardService;
import org.ttweb.taskmanagement.domain.application.commands.CreateBoardCommand;
import org.ttweb.taskmanagement.domain.common.event.DomainEventPublisher;
import org.ttweb.taskmanagement.domain.model.board.*;
import org.ttweb.taskmanagement.domain.model.board.events.BoardCreatedEvent;
import org.ttweb.taskmanagement.domain.model.board.events.BoardMemberAddedEvent;
import org.ttweb.taskmanagement.domain.model.user.User;
import org.ttweb.taskmanagement.domain.model.user.UserFinder;
import org.ttweb.taskmanagement.domain.model.user.UserId;
import org.ttweb.taskmanagement.domain.model.user.UserNotFoundException;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class BoardServiceImpl implements BoardService {
    private BoardRepository boardRepository;
    private BoardManagement boardManagement;
    private BoardMemberRepository boardMemberRepository;
    private UserFinder userFinder;
    private DomainEventPublisher domainEventPublisher;

    @Autowired
    public BoardServiceImpl(
            BoardRepository boardRepository,
            BoardManagement boardManagement,
            BoardMemberRepository boardMemberRepository,
            UserFinder userFinder,
            DomainEventPublisher domainEventPublisher){
        this.boardRepository = boardRepository;
        this.boardManagement = boardManagement;
        this.boardMemberRepository = boardMemberRepository;
        this.userFinder = userFinder;
        this.domainEventPublisher = domainEventPublisher;
    }

    @Override
    public List<Board> findBoardsByMembership(UserId userId) {
        return boardRepository.findBoardsByMembership(userId);
    }

    @Override
    public Board findById(BoardId boardId) {
        return boardRepository.findById(boardId);
    }

    @Override
    public List<User> findMembers(BoardId boardId) {
        return findMembers(boardId);
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

    @Override
    public User addMember(BoardId boardId, String usernameOrEmailAddress) throws UserNotFoundException {
        User user = userFinder.find(usernameOrEmailAddress);
        boardMemberRepository.add(boardId, user.getId());
        domainEventPublisher.publish(new BoardMemberAddedEvent(this, boardId, user));
        return user;
    }
}
