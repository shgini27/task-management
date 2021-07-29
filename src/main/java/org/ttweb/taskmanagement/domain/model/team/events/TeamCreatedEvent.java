package org.ttweb.taskmanagement.domain.model.team.events;

import org.ttweb.taskmanagement.domain.common.event.DomainEvent;
import org.ttweb.taskmanagement.domain.model.team.Team;

public class TeamCreatedEvent extends DomainEvent {
    private static final long serialVersionUID = -4827401695284293507L;

    private Team team;

    public TeamCreatedEvent(Object source, Team team) {
        super(source);
        this.team = team;
    }

    public Team getTeam(){
        return team;
    }
}
