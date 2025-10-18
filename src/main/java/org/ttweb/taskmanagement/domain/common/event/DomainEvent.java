package org.ttweb.taskmanagement.domain.common.event;

import org.ttweb.taskmanagement.domain.model.user.UserId;
import org.ttweb.taskmanagement.utils.IpAddress;

import java.io.Serializable;
import java.util.Date;

public abstract class DomainEvent implements Serializable {
    private static final long serialVersionUID = -4808076699549595470L;

    private UserId userId;
    private IpAddress ipAddress;
    private Date occurredAt;

    public DomainEvent(TriggeredBy triggeredBy) {
        this.userId = triggeredBy.getUserId();
        this.ipAddress = triggeredBy.getIpAddress();
        this.occurredAt = new Date();
    }

    public DomainEvent(UserId userId, TriggeredFrom triggeredFrom) {
        this.userId = userId;
        this.ipAddress = triggeredFrom.getIpAddress();
        this.occurredAt = new Date();
    }

    public UserId getUserId() {
        return userId;
    }

    public IpAddress getIpAddress() {
        return ipAddress;
    }

    public Date occurredAt(){
        return occurredAt;
    }
}
