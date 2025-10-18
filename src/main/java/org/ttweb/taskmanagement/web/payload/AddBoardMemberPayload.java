package org.ttweb.taskmanagement.web.payload;

import org.ttweb.taskmanagement.domain.application.commands.AddBoardMemberCommand;
import org.ttweb.taskmanagement.domain.model.board.BoardId;

public class AddBoardMemberPayload {
    private String usernameOrEmailAddress;

    public AddBoardMemberCommand toCommand(BoardId boardId) {
        return new AddBoardMemberCommand(boardId, usernameOrEmailAddress);
    }

    public void setUsernameOrEmailAddress(String usernameOrEmailAddress) {
        this.usernameOrEmailAddress = usernameOrEmailAddress;
    }
}
