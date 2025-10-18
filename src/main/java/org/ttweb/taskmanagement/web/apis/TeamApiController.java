package org.ttweb.taskmanagement.web.apis;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.ttweb.taskmanagement.domain.application.TeamService;
import org.ttweb.taskmanagement.domain.application.commands.CreateTeamCommand;
import org.ttweb.taskmanagement.domain.model.team.Team;
import org.ttweb.taskmanagement.web.payload.CreateTeamPayload;
import org.ttweb.taskmanagement.web.results.ApiResult;
import org.ttweb.taskmanagement.web.results.CreateTeamResult;

import javax.servlet.http.HttpServletRequest;

@Controller
public class TeamApiController extends AbstractBaseController {
    private TeamService teamService;

    public TeamApiController(TeamService teamService) {
        this.teamService = teamService;
    }

    @PostMapping("/api/teams")
    public ResponseEntity<ApiResult> createTeam(@RequestBody CreateTeamPayload payload,
                                                HttpServletRequest request) {
        CreateTeamCommand command = payload.toCommand();
        addTriggeredBy(command, request);

        Team team = teamService.createTeam(command);
        return CreateTeamResult.build(team);
    }
}
