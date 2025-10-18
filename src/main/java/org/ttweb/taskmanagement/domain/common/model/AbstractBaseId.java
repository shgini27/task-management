package org.ttweb.taskmanagement.domain.common.model;

import java.io.Serializable;

public abstract class AbstractBaseId implements Serializable {
    private static final long serialVersionUID = 8485393971178494328L;

    private long id;

    public AbstractBaseId(long id){
        this.id = id;
    }

    public long value() {
        return id;
    }

    public boolean isValid() {
        return id > 0;
    }
}
