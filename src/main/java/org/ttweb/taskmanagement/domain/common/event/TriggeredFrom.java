package org.ttweb.taskmanagement.domain.common.event;

import org.ttweb.taskmanagement.utils.IpAddress;

public interface TriggeredFrom {
    /**
     * Get the IP address where the request originated from
     *
     * @return an IP address
     */
    IpAddress getIpAddress();
}
