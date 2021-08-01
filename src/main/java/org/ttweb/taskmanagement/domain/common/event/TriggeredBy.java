package org.ttweb.taskmanagement.domain.common.event;

import org.ttweb.taskmanagement.domain.model.user.UserId;
import org.ttweb.taskmanagement.utils.IpAddress;

public interface TriggeredBy {
    /**
     * Get the id of the user who triggered this command
     *
     * @return a user's id
     */
    UserId getUserId();

    /**
     * Get the IP address where the request originated from
     *
     * @return an IP address
     */
    IpAddress getIpAddress();
}
