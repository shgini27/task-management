package org.ttweb.taskmanagement.web.apis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.ttweb.taskmanagement.domain.application.TeamService;
import org.ttweb.taskmanagement.domain.common.security.CurrentUser;
import org.ttweb.taskmanagement.domain.model.team.Team;
import org.ttweb.taskmanagement.domain.model.user.SimpleUser;
import org.ttweb.taskmanagement.web.payload.CreateTeamPayload;
import org.ttweb.taskmanagement.web.results.ApiResult;
import org.ttweb.taskmanagement.web.results.CreateTeamResult;

@Controller
public class TeamApiController {
    private TeamService teamService;

    @Autowired
    public TeamApiController(TeamService teamService){
        this.teamService = teamService;
    }

    @PostMapping("/api/teams")
    public ResponseEntity<ApiResult> creatTeam(
            @RequestBody CreateTeamPayload payload,
            @CurrentUser SimpleUser currentUser){
        Team team = teamService.createTeam(payload.toCommand(currentUser.getUserId()));
        return CreateTeamResult.build(team);
    }
}
