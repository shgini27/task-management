package org.ttweb.taskmanagement.domain.common.model;

import java.io.Serializable;

public abstract class AbstractBaseEntity implements Serializable {

  private static final long serialVersionUID = -1105857498893477781L;

  public abstract boolean equals(Object obj);

  public abstract int hashCode();

  public abstract String toString();
}
