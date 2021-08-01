package org.ttweb.taskmanagement.domain.common.file;

public class FileStorageException extends RuntimeException {

  private static final long serialVersionUID = 7502624141571258022L;

  public FileStorageException(String message) {
    super(message);
  }

  public FileStorageException(String message, Throwable cause) {
    super(message, cause);
  }
}
