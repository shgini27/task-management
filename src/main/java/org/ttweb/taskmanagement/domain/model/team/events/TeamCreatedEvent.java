package org.ttweb.taskmanagement.domain.model.team.events;

import org.ttweb.taskmanagement.domain.common.event.DomainEvent;
import org.ttweb.taskmanagement.domain.common.event.TriggeredBy;
import org.ttweb.taskmanagement.domain.model.team.Team;
import org.ttweb.taskmanagement.domain.model.team.TeamId;

public class TeamCreatedEvent extends DomainEvent {
    private static final long serialVersionUID = -4827401695284293507L;

    private TeamId teamId;
    private String teamName;

    public TeamCreatedEvent(Team team, TriggeredBy triggeredBy) {
        super(triggeredBy);
        this.teamId = team.getId();
        this.teamName = team.getName();
    }

    public TeamId getTeamId() {
        return teamId;
    }

    public String getTeamName() {
        return teamName;
    }

    @Override
    public String toString() {
        return "TeamCreatedEvent{" +
                "teamId=" + teamId +
                ", teamName='" + teamName + '\'' +
                '}';
    }
}
