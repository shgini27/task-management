package org.ttweb.taskmanagement.web.payload;

import org.ttweb.taskmanagement.domain.application.commands.AddCardListCommand;
import org.ttweb.taskmanagement.domain.model.board.BoardId;
import org.ttweb.taskmanagement.domain.model.user.UserId;

public class AddCardListPayload {
    private long boardId;
    private String name;
    private int position;

    public AddCardListCommand toCommand() {
        return new AddCardListCommand(new BoardId(boardId), name, position);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBoardId(long boardId) {
        this.boardId = boardId;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
