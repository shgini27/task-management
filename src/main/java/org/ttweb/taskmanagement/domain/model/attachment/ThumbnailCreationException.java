package org.ttweb.taskmanagement.domain.model.attachment;

public class ThumbnailCreationException extends RuntimeException {
    private static final long serialVersionUID = 3623582942554348852L;

    public ThumbnailCreationException(String message) {
        super(message);
    }

    public ThumbnailCreationException(String message, Throwable cause) {
        super(message, cause);
    }
}
