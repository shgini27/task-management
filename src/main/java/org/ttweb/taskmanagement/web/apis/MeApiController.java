package org.ttweb.taskmanagement.web.apis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.ttweb.taskmanagement.domain.application.BoardService;
import org.ttweb.taskmanagement.domain.application.TeamService;
import org.ttweb.taskmanagement.domain.common.security.CurrentUser;
import org.ttweb.taskmanagement.domain.model.board.Board;
import org.ttweb.taskmanagement.domain.model.team.Team;
import org.ttweb.taskmanagement.domain.model.user.SimpleUser;
import org.ttweb.taskmanagement.web.results.ApiResult;
import org.ttweb.taskmanagement.web.results.MyDataResult;

import java.util.List;

@Controller
public class MeApiController {
    private BoardService boardService;
    private TeamService teamService;

    @Autowired
    public MeApiController(BoardService boardService, TeamService teamService){
        this.boardService = boardService;
        this.teamService = teamService;
    }

    @GetMapping("/api/me")
    public ResponseEntity<ApiResult> getMyData(@CurrentUser SimpleUser currentUser){
        List<Team> teams = teamService.findTeamsByUserId(currentUser.getUserId());
        List<Board> boards = boardService.findBoardsByMembership(currentUser.getUserId());
        return MyDataResult.build(currentUser, teams, boards);
    }
}
