package org.ttweb.taskmanagement.domain.application.commands;

import io.jsonwebtoken.lang.Assert;
import org.ttweb.taskmanagement.domain.common.event.TriggeredBy;
import org.ttweb.taskmanagement.domain.model.user.UserId;
import org.ttweb.taskmanagement.utils.IpAddress;

import java.util.Objects;

public abstract class UserCommand implements TriggeredBy {
    private UserId userId;
    private IpAddress ipAddress;

    public void triggeredBy(UserId userId, IpAddress ipAddress) {
        Assert.notNull(userId, "Parameter `userId` must not be null");
        Assert.notNull(ipAddress, "Parameter `ipAddress` must not be null");

        this.userId = userId;
        this.ipAddress = ipAddress;
    }

    @Override
    public UserId getUserId() {
        return userId;
    }

    @Override
    public IpAddress getIpAddress() {
        return ipAddress;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserCommand)) return false;
        UserCommand that = (UserCommand) o;
        return Objects.equals(userId, that.userId) &&
                Objects.equals(ipAddress, that.ipAddress);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, ipAddress);
    }
}
