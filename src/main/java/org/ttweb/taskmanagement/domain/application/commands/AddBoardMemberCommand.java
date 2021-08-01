package org.ttweb.taskmanagement.domain.application.commands;

import org.ttweb.taskmanagement.domain.model.board.BoardId;

public class AddBoardMemberCommand extends UserCommand {
    private BoardId boardId;
    private String usernameOrEmailAddress;

    public AddBoardMemberCommand(BoardId boardId, String usernameOrEmailAddress) {
        this.boardId = boardId;
        this.usernameOrEmailAddress = usernameOrEmailAddress;
    }

    public BoardId getBoardId() {
        return boardId;
    }

    public String getUsernameOrEmailAddress() {
        return usernameOrEmailAddress;
    }
}
