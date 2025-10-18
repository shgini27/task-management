package org.ttweb.taskmanagement.domain.application.commands;

import org.ttweb.taskmanagement.domain.model.board.BoardId;
import org.ttweb.taskmanagement.domain.model.user.UserId;

public class AddCardListCommand extends UserCommand {
    private String name;
    private BoardId boardId;
    private int position;

    public AddCardListCommand(BoardId boardId, String name, int position) {
        this.boardId = boardId;
        this.name = name;
        this.position = position;
    }

    public BoardId getBoardId() {
        return boardId;
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }
}
