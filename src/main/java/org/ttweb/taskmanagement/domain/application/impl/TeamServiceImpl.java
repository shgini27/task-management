package org.ttweb.taskmanagement.domain.application.impl;

import org.springframework.stereotype.Service;
import org.ttweb.taskmanagement.domain.application.TeamService;
import org.ttweb.taskmanagement.domain.application.commands.CreateTeamCommand;
import org.ttweb.taskmanagement.domain.common.event.DomainEventPublisher;
import org.ttweb.taskmanagement.domain.model.team.Team;
import org.ttweb.taskmanagement.domain.model.team.TeamId;
import org.ttweb.taskmanagement.domain.model.team.TeamRepository;
import org.ttweb.taskmanagement.domain.model.team.events.TeamCreatedEvent;
import org.ttweb.taskmanagement.domain.model.user.UserId;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class TeamServiceImpl implements TeamService {
    private TeamRepository teamRepository;
    private DomainEventPublisher domainEventPublisher;

    public TeamServiceImpl(TeamRepository teamRepository, DomainEventPublisher domainEventPublisher) {
        this.teamRepository = teamRepository;
        this.domainEventPublisher = domainEventPublisher;
    }

    @Override
    public List<Team> findTeamsByUserId(UserId userId) {
        return teamRepository.findTeamsByUserId(userId);
    }

    @Override
    public Team findById(TeamId teamId) {
        return teamRepository.findById(teamId);
    }

    @Override
    public Team createTeam(CreateTeamCommand command) {
        Team team = Team.create(command.getName(), command.getUserId());
        teamRepository.save(team);
        domainEventPublisher.publish(new TeamCreatedEvent(team, command));
        return team;
    }
}
