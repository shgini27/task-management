package org.ttweb.taskmanagement.web.apis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.ttweb.taskmanagement.domain.application.BoardService;
import org.ttweb.taskmanagement.domain.common.security.CurrentUser;
import org.ttweb.taskmanagement.domain.model.board.Board;
import org.ttweb.taskmanagement.domain.model.user.SimpleUser;
import org.ttweb.taskmanagement.web.payload.CreateBoardPayload;
import org.ttweb.taskmanagement.web.results.ApiResult;
import org.ttweb.taskmanagement.web.results.CreateBoardResult;

@Controller
public class BoardApiController {
    private BoardService boardService;

    @Autowired
    public BoardApiController(BoardService boardService){
        this.boardService = boardService;
    }

    @PostMapping("/api/boards")
    public ResponseEntity<ApiResult> createBoard(
            @RequestBody CreateBoardPayload payload,
            @CurrentUser SimpleUser currentUser){
        Board board = boardService.createBoard(payload.toCommand(currentUser.getUserId()));
        return CreateBoardResult.build(board);
    }
}
