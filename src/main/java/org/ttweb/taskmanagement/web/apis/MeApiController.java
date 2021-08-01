package org.ttweb.taskmanagement.web.apis;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.ttweb.taskmanagement.domain.application.BoardService;
import org.ttweb.taskmanagement.domain.application.TeamService;
import org.ttweb.taskmanagement.domain.application.UserService;
import org.ttweb.taskmanagement.domain.common.security.CurrentUser;
import org.ttweb.taskmanagement.domain.common.security.TokenManager;
import org.ttweb.taskmanagement.domain.model.board.Board;
import org.ttweb.taskmanagement.domain.model.team.Team;
import org.ttweb.taskmanagement.domain.model.user.SimpleUser;
import org.ttweb.taskmanagement.domain.model.user.User;
import org.ttweb.taskmanagement.web.results.ApiResult;
import org.ttweb.taskmanagement.web.results.MyDataResult;

import java.util.List;

@Controller
public class MeApiController {
    private String realTimeServerUrl;
    private TeamService teamService;
    private BoardService boardService;
    private UserService userService;
    private TokenManager tokenManager;

    public MeApiController(@Value("${app.real-time-server-url}") String realTimeServerUrl,
                           TeamService teamService,
                           BoardService boardService,
                           UserService userService,
                           TokenManager tokenManager) {
        this.realTimeServerUrl = realTimeServerUrl;
        this.teamService = teamService;
        this.boardService = boardService;
        this.userService = userService;
        this.tokenManager = tokenManager;
    }

    @GetMapping("/api/me")
    public ResponseEntity<ApiResult> getMyData(@CurrentUser SimpleUser currentUser) {
        User user = userService.findById(currentUser.getUserId());
        List<Team> teams = teamService.findTeamsByUserId(currentUser.getUserId());
        List<Board> boards = boardService.findBoardsByMembership(currentUser.getUserId());
        String realTimeToken = tokenManager.jwt(user.getId());
        return MyDataResult.build(user, teams, boards, realTimeServerUrl, realTimeToken);
    }
}
